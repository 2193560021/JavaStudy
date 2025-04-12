package com.lyy.allbadminton.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.lyy.allbadminton.entity.BookingOrder;
import com.lyy.allbadminton.entity.Court;
import com.lyy.allbadminton.mapper.BookingOrderMapper;
import com.lyy.allbadminton.result.Result;
import com.lyy.allbadminton.service.IBookingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyy.allbadminton.service.ICourtService;
import com.lyy.allbadminton.util.RedisIdWorker;
import com.lyy.allbadminton.util.UserHolder;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 预约订单 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@Service
public class BookingOrderServiceImpl extends ServiceImpl<BookingOrderMapper, BookingOrder> implements IBookingOrderService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ICourtService courtService;

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();


    @PostConstruct
    private void init() {
        SECKILL_ORDER_EXECUTOR.submit(new BookingOrderHandler());
    }

    private class BookingOrderHandler implements Runnable {

        String queueName = "stream.orders";

        @Override
        public void run() {
            while (true) {
                try {
                    //1.获取队列中的订单信息
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(queueName, ReadOffset.lastConsumed())
                    );
                    if (list == null || list.isEmpty()) {
                        continue;
                    }
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> values = record.getValue();
                    BookingOrder bookingOrder = BeanUtil.fillBeanWithMap(values, new BookingOrder(), true);
                    handleBookingOrder(bookingOrder);
                    //确认
                    stringRedisTemplate.opsForStream().acknowledge("s1", "g1", record.getId());
                } catch (Exception e) {
                    log.error("处理订单异常", e);
                    handlePendingList();
                }
            }
        }

        private void handlePendingList(){
            while (true) {
                try {
                    //1.获取队列中的订单信息
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1),
                            StreamOffset.create(queueName, ReadOffset.from("0"))
                    );
                    if (list == null || list.isEmpty()) {
                        break;
                    }
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> values = record.getValue();
                    BookingOrder bookingOrder = BeanUtil.fillBeanWithMap(values, new BookingOrder(), true);
                    handleBookingOrder(bookingOrder);
                    //确认
                    stringRedisTemplate.opsForStream().acknowledge("s1", "g1", record.getId());
                } catch (Exception e) {
                    log.error("处理Pending-List异常", e);
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
                }
            }
        }
    }

    private IBookingOrderService proxy;

    @Override
    public Result seckillCourt(Long courtId) {
        Long userId = UserHolder.getUser().getId();
        // 5.1.订单id
        long orderId = redisIdWorker.nextId("order");
        //1.执行lua脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                courtId.toString(), userId.toString(), String.valueOf(orderId)
        );

        //2.判断结果是否为0
        int r = result.intValue();
        if (r != 0) {
            return Result.error(r == 1?"场地被预约":"不允许重复下单");
        }
//
//        // 5.创建订单
//        VoucherOrder voucherOrder = new VoucherOrder();
//        voucherOrder.setId(orderId);
//        // 5.2.用户id
//        voucherOrder.setUserId(userId);
//        // 5.3.代金券id
//        voucherOrder.setVoucherId(voucherId);
//        //保存阻塞队列
//        orderTasks.add(voucherOrder);
//
        //获取代理对象（事务）
        proxy =(IBookingOrderService) AopContext.currentProxy();


        return Result.success(orderId);
    }



    public void handleBookingOrder(BookingOrder bookingOrder){
        Long courtId = bookingOrder.getCourtId();
        Long userId = UserHolder.getUser().getId();

        RLock lock = redissonClient.getLock("lock:order:" + courtId);
        boolean isLock = lock.tryLock();
        if(!isLock){
            log.error("该场地已被预约");
            return ;
        }
        try {
            //获取锁对象
//            proxy.createBookingOrder(courtId, userId);

            int count = query().eq("court_id", courtId).eq("all_status", 1).count();
            if(count > 0){
                log.error("该场地已被预约!");
                return ;
            }

            boolean success = courtService.update()
                    .setSql("status = 0")
                    .eq("id", courtId).eq("status", 1)
                    .update();
            if(!success){
                log.error("该场地刚刚已被预约!");
                return ;
            }


            BookingOrder order = new BookingOrder();
            Court court = courtService.getById(courtId);

            order.setOrderNo(UUID.randomUUID().toString(true));
            order.setUserId(userId); //UserHolder.getUser().getId()
            order.setCourtId(courtId);
            order.setDate(new Date());
            order.setStartTime(court.getOpenTime());
            order.setEndTime(court.getCloseTime());
            order.setAmount(court.getPrice());
//            order.setStatus(0);
            order.setCreateTime(LocalDateTime.now());

            save(order);

        } finally {
            lock.unlock();
        }
    }


    public void createBookingOrder(Long courtId, Long userId) {
//        String lockKey = "lock:court:" + courtId;
//        RLock lock = redissonClient.getLock(lockKey);
//        boolean isLock = lock.tryLock();
//        if(!isLock){
//            log.error("该场地已被预约!");
//            return Result.error("此场地该时间段已被预约!");
//        }
//        try {
//            int count = query().eq("court_id", courtId).eq("all_status", 1).count();
//            if(count > 0){
//                log.error("该场地已被预约!");
//                return Result.error("此场地该时间段已被预约!");
//            }
//
//            boolean success = courtService.update()
//                    .setSql("status = 0")
//                    .eq("id", courtId).eq("status", 1)
//                    .update();
//            if(!success){
//                log.error("该场地刚刚已被预约!");
//                return Result.error("此场地该时间段刚刚已被预约!");
//            }
//
//
//            BookingOrder order = new BookingOrder();
//            Court court = courtService.getById(courtId);
//
//            order.setOrderNo(UUID.randomUUID().toString(true));
//            order.setUserId(UserHolder.getUser().getId());
//            order.setCourtId(courtId);
//            order.setDate(new Date());
//            order.setStartTime(court.getOpenTime());
//            order.setEndTime(court.getCloseTime());
//            order.setAmount(court.getPrice());
//            order.setStatus(0);
//            order.setCreateTime(LocalDateTime.now());
//
//            save(order);
//
//
//        } finally {
//            lock.unlock();
//        }
        return;
    }
}

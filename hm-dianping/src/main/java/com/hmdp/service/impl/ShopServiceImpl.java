package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheClient;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RedisData;
import netscape.javascript.JSObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

     @Resource
     private StringRedisTemplate stringRedisTemplate;

     @Resource
     private CacheClient cacheClient;

    @Override
    public Result queryById(Long id) {
        //缓存穿透
        Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY,id, Shop.class, this::getById,CACHE_SHOP_TTL,TimeUnit.MINUTES);

        //用互斥锁解决缓存击穿
        // shop = queryWithMutex(id);

        //逻辑过期解决缓存击穿
//        Shop shop = cacheClient.queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById,20L,TimeUnit.SECONDS);
        if (shop == null) {
            return Result.fail("店铺不存在");
        }

        //7.返回
        return Result.ok(shop);
    }

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

//
//    public Shop queryWithLogicalExpire(Long id){
//
//        String key = CACHE_SHOP_KEY + id;
//
//        //1.从redis查询商铺缓存
//        String shopJson = stringRedisTemplate.opsForValue().get(key);
//        //2.判断是否存在
//        if (StrUtil.isBlank(shopJson)) {
//            //3.存在，直接返回
//            return null;
//        }
//
//        //4.命中，反序列化为Json对象
//        RedisData redisData = JSONUtil.toBean(shopJson, RedisData.class);
//        JSONObject data = (JSONObject) redisData.getData();
//        Shop shop = JSONUtil.toBean(data, Shop.class);
//        LocalDateTime expireTime = redisData.getExpireTime();
//
//        if (expireTime.isAfter(LocalDateTime.now())) {
//            //未过期
//            return shop;
//        }
//
//        //过期
//        String lockKey = LOCK_SHOP_KEY + id;
//        boolean isLock = tryLock(lockKey);
//        if (isLock) {
//            CACHE_REBUILD_EXECUTOR.submit(() -> {
//                try {
//                    this.saveShop2Redis(id,20L);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }finally {
//                    unLock(lockKey);
//                }
//            });
//        }
//
//        //7.返回
//        return shop;
//    }
//
//    public Shop queryWithMutex(Long id){
//
//        //1.从redis查询商铺缓存
//        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
//        //2.判断是否存在
//        if (StrUtil.isNotBlank(shopJson)) {
//            //3.存在，直接返回
//            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
//            return shop;
//        }
//
//        //判断是否为空值
//        if(shopJson != null){
//            return null;
//        }
//
//        //4.开始实现缓存重建
//        //4.1获取互斥锁
//        String lockKey = LOCK_SHOP_KEY + id;
//        Shop shop = null;
//        try {
//            boolean isLock = tryLock(lockKey);
//            //4.2判断是否获取成功
//            if (!isLock) {
//                //4.3失败，休眠，重试
//                Thread.sleep(50);
//                return queryWithMutex(id);
//            }
//
//            //4.4成功，根据id查询数据库
//            //4.不存在，根据id查询数据库
//            shop = getById(id);
//            //模拟重建延迟
//            Thread.sleep(200);
//
//            //5.不存在，返回错误
//            if (shop == null) {
//                //将空值写入redis
//                stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
//                //返回错误信息
//                return null;
//            }
//
//            //6.存在，写入redis
//            stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        //7.释放互斥锁
//        unLock(lockKey);
//        return shop;
//    }

//
//
//    private boolean tryLock(String key){
//        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", LOCK_SHOP_TTL, TimeUnit.SECONDS);
//
//        return BooleanUtil.isTrue(flag);
//    }
//
//    private void unLock(String key){
//        stringRedisTemplate.delete(key);
//    }

//    public Shop queryWithPassThrough(Long id){
//
//        //1.从redis查询商铺缓存
//        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
//        //2.判断是否存在
//        if (StrUtil.isNotBlank(shopJson)) {
//            //3.存在，直接返回
//            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
//            return shop;
//        }
//
//        //判断是否为空值
//        if(shopJson != null){
//            return null;
//        }
//
//        //4.不存在，根据id查询数据库
//        Shop shop = getById(id);
//
//        //5.不存在，返回错误
//        if (shop == null) {
//            //将空值写入redis
//            stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
//            //返回错误信息
//            return null;
//        }
//
//        //6.存在，写入redis
//        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
//
//        //7.返回
//        return shop;
//    }


    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            return Result.fail("店铺ID不能为空");
        }
        //1.更新数据库
        updateById(shop);
        System.out.println(CACHE_SHOP_KEY + id);
        //2.删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        return Result.ok();
    }
}

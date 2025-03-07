package com.sky.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.*;
import com.sky.exception.AddressBookBusinessException;
import com.sky.exception.OrderBusinessException;
import com.sky.exception.ShoppingCartBusinessException;
import com.sky.mapper.*;
import com.sky.result.PageResult;
import com.sky.service.AddressBookService;
import com.sky.service.OrderService;
import com.sky.utils.WeChatPayUtil;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import com.sky.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatPayUtil weChatPayUtil;

    @Autowired
    private WebSocketServer webSocketServer;




    @Override
    @Transactional
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {

        //处理各种异常情况（如地址簿为空，购物车为空）
        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        if (addressBook == null) {
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }
        //查询当前用户是否有购物车数据
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(userId)
                .build();
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
        if (shoppingCartList == null || shoppingCartList.size() == 0) {
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }
        //向订单表插入数据，一条
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO,orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(Orders.UN_PAID);
        orders.setStatus(Orders.PENDING_PAYMENT);
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setUserId(userId);

        orderMapper.insert(orders);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        //向订单明细表插入数据，多条
        for (ShoppingCart cart : shoppingCartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.insertBatch(orderDetailList);

        //清空购物车数据
        shoppingCartMapper.clean(userId);

        //封装返回结果
        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .id(orders.getId())
                .orderTime(orders.getOrderTime())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .build();
        return orderSubmitVO;
    }


    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    public OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        // 当前登录用户id
        Long userId = BaseContext.getCurrentId();
        User user = userMapper.getById(userId);

        //调用微信支付接口，生成预支付交易单
//        JSONObject jsonObject = weChatPayUtil.pay(
//                ordersPaymentDTO.getOrderNumber(), //商户订单号
//                new BigDecimal(0.01), //支付金额，单位 元
//                "苍穹外卖订单", //商品描述
//                user.getOpenid() //微信用户的openid
//        );
//
//        if (jsonObject.getString("code") != null && jsonObject.getString("code").equals("ORDERPAID")) {
//            throw new OrderBusinessException("该订单已支付");
//        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timeStamp", System.currentTimeMillis());
        jsonObject.put("nonceStr", "111");
        jsonObject.put("package", "prepay_id=" + 111);
        jsonObject.put("signType", "RSA");
        jsonObject.put("paySign", "111");


        // 根据订单号查询订单
        Orders ordersDB = orderMapper.getByNumber(ordersPaymentDTO.getOrderNumber());

        // 根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();

        orderMapper.update(orders);

        OrderPaymentVO vo = jsonObject.toJavaObject(OrderPaymentVO.class);
        vo.setPackageStr(jsonObject.getString("package"));

        //通过websocket向管理端浏览器推送消息
        Map map =new HashMap<>();
        map.put("type",1);
        map.put("orderId",orders.getId());
        map.put("content","订单号：" + ordersPaymentDTO.getOrderNumber() + "  待接单");

        String json = JSONUtils.toJSONString(map);
        webSocketServer.sendToAllClient(json);

        return vo;
    }
    public void paySuccess(String outTradeNo) {

        //TODO 废
        // 根据订单号查询订单
        Orders ordersDB = orderMapper.getByNumber(outTradeNo);

        // 根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();

        orderMapper.update(orders);
    }

    /**
     * 用户端订单分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    public PageResult pageQuery4User(int pageNum, int pageSize, Integer status) {
        // 设置分页
        PageHelper.startPage(pageNum, pageSize);

        OrdersPageQueryDTO ordersPageQueryDTO = new OrdersPageQueryDTO();
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        ordersPageQueryDTO.setStatus(status);

        // 分页条件查询
        Page<Orders> page = orderMapper.pageQuery(ordersPageQueryDTO);

        List<OrderVO> list = new ArrayList();

        // 查询出订单明细，并封装入OrderVO进行响应
        if (page != null && page.getTotal() > 0) {
            for (Orders orders : page) {
                Long orderId = orders.getId();// 订单id

                // 查询订单明细
                List<OrderDetail> orderDetails = orderDetailMapper.getById(orderId);

                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);
                orderVO.setOrderDetailList(orderDetails);

                list.add(orderVO);
            }
        }
        return new PageResult(page.getTotal(), list);
    }

    @Override
    public OrderVO details(Long id) {
        // 根据id查询订单
        Orders orders = orderMapper.getById(id);

        // 查询该订单对应的菜品/套餐明细
        List<OrderDetail> orderDetailList = orderDetailMapper.getById(orders.getId());

        // 将该订单及其详情封装到OrderVO并返回
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);

        return orderVO;
    }

    //商家查询所有订单，里面多了一个字符串OrderDishes
    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());

        Page<Orders> page = orderMapper.pageQuery(ordersPageQueryDTO);
        List<OrderVO> orderVOList = getOrderVOList(page);

        PageResult pageResult = new PageResult(page.getTotal(), orderVOList);
        return pageResult;
    }

    private List<OrderVO> getOrderVOList(Page<Orders> page) {
        // 需要返回订单菜品信息，自定义OrderVO响应结果
        List<OrderVO> orderVOList = new ArrayList<>();

        List<Orders> ordersList = page.getResult();
        if (!CollectionUtils.isEmpty(ordersList)) {
            for (Orders orders : ordersList) {
                // 将共同字段复制到OrderVO
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);
                String orderDishes = getOrderDishesStr(orders);

                // 将订单菜品信息封装到orderVO中，并添加到orderVOList
                orderVO.setOrderDishes(orderDishes);
                orderVOList.add(orderVO);
            }
        }
        return orderVOList;
    }

    private String getOrderDishesStr(Orders orders) {
        List<OrderDetail> orderDetailList = orderDetailMapper.getById(orders.getId());
        String orderDishes = "";
        for (OrderDetail orderDetail : orderDetailList) {
            orderDishes += orderDetail.getName() + "*" + orderDetail.getNumber() + ";";
        };
        return orderDishes;
    }

    //Over 商家查询所有订单，里面多了一个字符串OrderDishes


    @Override
    public OrderVO orderDetail(Long id) {
        OrderVO orderVO = new OrderVO();
        List<OrderDetail> orderDetailList = orderDetailMapper.getById(id);
        String orderDishes = "";
        for (OrderDetail orderDetail : orderDetailList) {
            orderDishes += orderDetail.getName() + "*" + orderDetail.getNumber() + ";";
        };
        orderVO.setOrderDishes(orderDishes);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

    @Override
    public Integer confirm(Long id) {
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.CONFIRMED)
                .build();
//        Integer confirmed = Orders.CONFIRMED;
        Integer update = orderMapper.update(orders);
        return update;
    }

    @Override
    public Integer rejection(Long id, String rejectionReason) {
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.CANCELLED)
                .rejectionReason(rejectionReason)
                .build();
        Integer update = orderMapper.update(orders);
        return update;
    }

    @Override
    public Integer delivery(Long id) {
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.DELIVERY_IN_PROGRESS)
                .build();
        Integer update = orderMapper.update(orders);
        return update;
    }

    @Override
    public Integer complete(Long id) {
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.COMPLETED)
                .build();
        Integer update = orderMapper.update(orders);
        return update;
    }

    @Override
    public OrderStatisticsVO statistics() {
        OrderStatisticsVO vo = new OrderStatisticsVO();
        vo.setToBeConfirmed(orderMapper.countByStatus(Orders.TO_BE_CONFIRMED));
        vo.setConfirmed(orderMapper.countByStatus(Orders.CONFIRMED));
        vo.setDeliveryInProgress(orderMapper.countByStatus(Orders.DELIVERY_IN_PROGRESS));
        return vo;
    }

    @Override
    public Integer cancel(Long id) {
        Orders orders = Orders.builder()
                .id(id)
                .status(Orders.CANCELLED)
                .build();
        Integer update = orderMapper.update(orders);
        return update;
    }

    /**
     * 再来一单
     *
     * @param id
     */
    public void repetition(Long id) {
        // 查询当前用户id
        Long userId = BaseContext.getCurrentId();

        // 根据订单id查询当前订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.getById(id);

        // 将订单详情对象转换为购物车对象
        List<ShoppingCart> shoppingCartList = orderDetailList.stream().map(x -> {
            ShoppingCart shoppingCart = new ShoppingCart();

            // 将原订单详情里面的菜品信息重新复制到购物车对象中
            BeanUtils.copyProperties(x, shoppingCart, "id");
            shoppingCart.setUserId(userId);
            shoppingCart.setCreateTime(LocalDateTime.now());

            return shoppingCart;
        }).collect(Collectors.toList());

        // 将购物车对象批量添加到数据库
        shoppingCartMapper.insertBatch(shoppingCartList);
    }

    @Override
    public void reminder(Long id) {
        Orders orders = orderMapper.getById(id);

        if (orders == null) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }
        //通过websocket向管理端浏览器推送消息
        Map map =new HashMap<>();
        map.put("type",2);
        map.put("orderId", id);
        map.put("content","订单号：" + orders.getNumber() + "  催单");

        String json = JSONUtils.toJSONString(map);
        webSocketServer.sendToAllClient(json);
    }

}

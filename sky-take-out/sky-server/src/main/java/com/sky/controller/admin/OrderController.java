package com.sky.controller.admin;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Api(tags = "C端订单接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("查询订单：{}", ordersPageQueryDTO);
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }


    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> statistics(){
        log.info("查询订单状态数量：{}" );
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }



    @PutMapping("/confirm")
    public Result confirm(@RequestBody Orders orders){
        log.info("接单：{}", orders);
        Integer confirm = orderService.confirm(orders.getId());
        return Result.success(confirm);
    }


    @PutMapping("/rejection")
    public Result rejection(@RequestBody Orders orders){
        log.info("拒单：{}", orders);
        Integer rejection = orderService.rejection(orders.getId(), orders.getRejectionReason());
        return Result.success(rejection);
    }

    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable Long id){
        log.info("派送：{}", id);
        Integer delivery = orderService.delivery(id);
        return Result.success(delivery);
    }


    @PutMapping("/cancel")
    public Result cancel(@RequestBody Orders orders){
        log.info("商家取消：{}", orders);
        Integer delivery = orderService.cancel(orders.getId());
        return Result.success(delivery);
    }


    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable Long id){
        log.info("完成：{}", id);
        Integer complete = orderService.complete(id);
        return Result.success();
    }

    @GetMapping("/details/{id}")
    public Result<OrderVO> detail(@PathVariable Long id){
        log.info("订单详情：{}", id);
        OrderVO orderVO = orderService.orderDetail(id);
        return Result.success(orderVO);
    }
}

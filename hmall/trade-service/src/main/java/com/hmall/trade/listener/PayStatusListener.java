package com.hmall.trade.listener;

import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayStatusListener {

    private final IOrderService orderService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "trade.pay.success", durable = "true"),
            exchange = @Exchange(name = "pay.direct"),
            key = "pay.success"
    ))
    public void listenPayStatus(Long orderId){
        //查询订单
        Order order = orderService.getById(orderId);

        if(order == null ||order.getStatus() != 1){
            return;
        }

        //3.标记订单为已支付
        orderService.markOrderPaySuccess(orderId);
    }
}

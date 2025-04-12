package com.lyy.allbadminton.service;

import com.lyy.allbadminton.entity.BookingOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyy.allbadminton.result.Result;

/**
 * <p>
 * 预约订单 服务类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
public interface IBookingOrderService extends IService<BookingOrder> {

//    void createBookingOrder(Long courtId, Long userId);
//
//    void handleBookingOrder(Long courtId);

    Result seckillCourt(Long courtId);
}

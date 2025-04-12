package com.lyy.allbadminton.controller;


import com.lyy.allbadminton.entity.Court;
import com.lyy.allbadminton.result.Result;
import com.lyy.allbadminton.service.IBookingOrderService;
import com.lyy.allbadminton.service.ICourtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 预约订单 前端控制器
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@RestController
@RequestMapping("/booking-order")
@Slf4j
@Api("预约接口")
@RequiredArgsConstructor
public class BookingOrderController {

    private final IBookingOrderService bookingOrderService;

    private final ICourtService courtService;
    /**
     * 查看可预约场地
     */
    @GetMapping("/available")
    @ApiOperation("查看可预约场地")
    public Result<List<Court>> availableCourt(){
        return courtService.getAvailableCourt();

    }

    /**
     * 预约场地
     */
    @PostMapping("/create")
    public Result createBookingOrder(@RequestParam("courtId") Long courtId,@RequestParam("userId") Long userId){
        return bookingOrderService.seckillCourt(courtId);
    }






}

package com.hmall.api.client;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface UserClient {

    @PutMapping("/users/money/deduct")
    void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);

}

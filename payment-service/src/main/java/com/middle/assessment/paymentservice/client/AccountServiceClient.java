package com.middle.assessment.paymentservice.client;

import com.middle.assessment.paymentservice.dto.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service", url = "http://account-service:8089")
public interface AccountServiceClient {

    @PutMapping("/userAccount/update/{id}")
    void update(@PathVariable Long id, @RequestBody UserAccount userAccount);


    @GetMapping("/userAccount/findByUserId/{userId}")
    UserAccount findByUserId(@PathVariable Long userId);
}

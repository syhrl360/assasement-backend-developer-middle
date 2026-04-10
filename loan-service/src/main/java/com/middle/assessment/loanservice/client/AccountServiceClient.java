package com.middle.assessment.loanservice.client;

import com.middle.assessment.loanservice.dto.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "http://account-service:8089")
public interface AccountServiceClient {

    @GetMapping("/userAccount/findByUserId/{userId}")
    UserAccount findByUserId(@PathVariable Long userId);
}

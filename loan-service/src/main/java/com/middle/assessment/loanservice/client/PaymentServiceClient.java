package com.middle.assessment.loanservice.client;

import com.middle.assessment.loanservice.dto.PaymentRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://payment-service:8088")
public interface PaymentServiceClient {
    @PostMapping("/paymentRecord/insert")
    void insert(@RequestBody PaymentRecord loanRecord);
}

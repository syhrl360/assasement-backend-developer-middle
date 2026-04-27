package com.practice.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authority", url = "http://authority:8002")
public interface AuthorityClient {
    @GetMapping("/auth/validate")
    String validateToken(@RequestParam("token") String token);
}

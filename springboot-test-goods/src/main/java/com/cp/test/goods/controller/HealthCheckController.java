package com.cp.test.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/service/goods/healthcheck")
    public String healthCheck(){
        return "health check from goods ms";
    }
}

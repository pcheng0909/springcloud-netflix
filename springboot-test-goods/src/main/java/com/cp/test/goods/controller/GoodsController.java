package com.cp.test.goods.controller;

import com.cp.test.goods.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    private TestService testService;

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    public GoodsController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/service/goods/{id}")
    public String getGoodsById(@PathVariable("id") String id){
        System.out.println("goods port : " + serverPort);
        return testService.getGoodsById(id).toString();

    }

}

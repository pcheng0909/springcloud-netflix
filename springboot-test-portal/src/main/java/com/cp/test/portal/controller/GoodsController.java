package com.cp.test.portal.controller;

import com.cp.test.common.model.client.GoodsApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class GoodsController {

    // 直连
    private static final String GOODS_SERVICE_URL = "http://localhost:8090/service/goods/1";
    //注册中心
    private static final String GOODS_SERVICE_URL_RIBBON = "http://SPRINGBOOT-TEST-GOODS/service/goods/1";
    //Feign

    private GoodsApi goodsApi;

    private RestTemplate restTemplate;

    public GoodsController(GoodsApi goodsApi, RestTemplate restTemplate) {
        this.goodsApi = goodsApi;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/service/goods/{id}")
    public String getGoodsById(@PathVariable("id") String id){

        ResponseEntity<String> entity = restTemplate.getForEntity(GOODS_SERVICE_URL_RIBBON,String.class);
        return entity.getBody();
    }

    @GetMapping("/cloud/feign/goods/{id}")
    public String getGoodsByIdFromFeign(@PathVariable("id") String id){

        return goodsApi.getGoodsById(id);

    }
    @GetMapping("/cloud/hystrix/goods/{id}")
    @HystrixCommand(fallbackMethod = "fallback",
            ignoreExceptions = Exception.class) // 相当于服务不降级
    public String getGoodsByIdWithHystrix(@PathVariable("id") String id) throws InterruptedException {
        // 1.超时降级
        // 2.异常降级
        //Thread.sleep(2000);
        return goodsApi.getGoodsById(id);

    }

    /**
     * 服务降级的方法
     * @return
     */
    public String fallback(String id, Throwable throwable){
        System.out.println(throwable.getMessage());
        return " this is a fallback";
    }

    /**
     * Hystrix 限流
     * @return
     */
    @GetMapping("/cloud/hystrix/limit/goods/{id}")
    @HystrixCommand(fallbackMethod = "fallback",
            threadPoolKey = "goods",
            threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "2"),
            @HystrixProperty(name = "maxQueueSize",value = "1")}
    )
    public String getGoodsByIdWithHystrixLimit(@PathVariable("id") String id) throws InterruptedException {
        // 1.超时降级
        // 2.异常降级
        //Thread.sleep(2000);
        return goodsApi.getGoodsById(id);

    }
}

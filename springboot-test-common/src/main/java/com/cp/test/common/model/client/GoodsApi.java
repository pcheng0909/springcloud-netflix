package com.cp.test.common.model.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("SPRINGBOOT-TEST-GOODS")
public interface GoodsApi {

    /**
     * 声明一个feign的接口
     * @param id
     * @return
     */
    @GetMapping("/service/goods/{id}")
    String getGoodsById(@PathVariable("id") String id);
}

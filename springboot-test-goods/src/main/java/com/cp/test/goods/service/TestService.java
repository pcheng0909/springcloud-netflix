package com.cp.test.goods.service;

import com.cp.test.common.model.Goods;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public Goods getGoodsById(String id){
        if(id.equals("1"))
            return new Goods("1","1","1");
        return new Goods("other","other","other");
    }
}

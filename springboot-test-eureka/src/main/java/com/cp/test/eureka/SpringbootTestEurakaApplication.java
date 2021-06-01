package com.cp.test.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootTestEurakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestEurakaApplication.class, args);
    }

}

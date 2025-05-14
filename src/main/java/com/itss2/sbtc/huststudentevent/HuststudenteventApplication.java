package com.itss2.sbtc.huststudentevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HuststudenteventApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuststudenteventApplication.class, args);
    }

}

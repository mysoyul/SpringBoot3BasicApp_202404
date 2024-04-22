package com.basic.myspringboot.config;

import com.basic.myspringboot.vo.CustomerVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public CustomerVO customerVO() {
        return CustomerVO.builder() //CustomerVOBuilder
                .id(200L)
                .mode("운영환경")
                .build();
    }
}

package com.basic.myspringboot.config;

import com.basic.myspringboot.vo.CustomerVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Bean
    public CustomerVO customerVO() {
        return CustomerVO.builder() //CustomerVOBuilder
                .id(100L)
                .mode("테스트환경")
                .build();
    }
}

package com.basic.myspringboot.runner;

import com.basic.myspringboot.vo.CustomerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Value("${myboot.name}")
    private String name;
    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerVO customer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("Logger 구현객체 클래스명 = {}", logger.getClass().getName());
        logger.info("2. MyRunner  class");
        logger.info("현재 활성화된 Profile = " + customer);
        logger.info("환경변수 myboot.name = " + name);
        logger.info("환경변수 myboot.age = " + age);

        //System.out.println("Environment 구현객체 클래스 이름 = " + environment.getClass().getName());
        logger.info("환경변수 myboot.fullName = " + environment.getProperty("myboot.fullName"));

        logger.debug("VM arguments -Dfoo " + args.containsOption("foo"));
        logger.debug("Program arguments --bar " + args.containsOption("bar"));
    }
}

package com.basic.myspringboot.runner;

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
    @Value("${myboot.name}")
    private String name;
    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("2. MyRunner  class");
        System.out.println("환경변수 myboot.name = " + name);
        System.out.println("환경변수 myboot.age = " + age);

        //System.out.println("Environment 구현객체 클래스 이름 = " + environment.getClass().getName());
        System.out.println("환경변수 myboot.fullName = " + environment.getProperty("myboot.fullName"));

        System.out.println("VM arguments -Dfoo " + args.containsOption("foo"));
        System.out.println("Program arguments --bar " + args.containsOption("bar"));
    }
}

package com.basic.myspringboot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
@Order(1)
@Slf4j
public class DatabaseRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("DataSource 구현객체 클래스명 " + dataSource.getClass().getName());
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("Database Product Name = {}", metaData.getDatabaseProductName());
            log.info("Database URL = {}", metaData.getURL());
            log.info("Database Username = {}", metaData.getUserName());

        }
    }
}
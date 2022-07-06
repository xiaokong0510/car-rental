package com.xiao.carrental;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author kongx
 * @date 2022/6/26 13:11
 * @description
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = {"com.xiao.carrental.mapper"})
@ServletComponentScan("com.xiao.carrental.filter")
public class CarRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class);
        log.info("--------------car rental is running!--------------" );
    }
}

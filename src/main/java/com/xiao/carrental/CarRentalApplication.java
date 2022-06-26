package com.xiao.carrental;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kongx
 * @date 2022/6/26 13:11
 * @description
 */
@SpringBootApplication
@Slf4j
public class CarRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class);
        log.info("--------------car rental is running!--------------" );
    }
}

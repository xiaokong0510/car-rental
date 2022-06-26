package com.xiao.carrental.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Getter
@AllArgsConstructor
public enum CarModelEnum {

    TOYOTA_CAMRY("Toyota Camry"),
    BMW_650("BMW 650");

    private final String carModel;
}

package com.xiao.carrental.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {

    SUCCESS(200, "success"),
    VALIDATE_FAILED(40001, "validate failed"),
    CAR_NOT_EXISTS(40002, "this car not exists"),
    STOCK_IS_INSUFFICIENT(40003, "the stock of this car is insufficient"),
    RENT_INFO_NOT_EXISTS(40004, "this rent not exists"),
    CAR_HAS_BEEN_RETURN(40005, "car has been return"),
    UNKNOWN_ERROR(500, "unknown error");

    private int code;
    private String msg;
}

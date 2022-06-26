package com.xiao.carrental.common.enums;

import lombok.Getter;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Getter
public enum RentStateEnum {

    /**
     * 出租中
     */
    IN_RENTING(1, "出租中"),

    /**
     * 已归还
     */
    RETURNED(2, "已归还");

    private Integer code;

    private String desc;

    RentStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

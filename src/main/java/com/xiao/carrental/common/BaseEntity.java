package com.xiao.carrental.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Getter
@Setter
public class BaseEntity {

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

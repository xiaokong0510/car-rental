package com.xiao.carrental.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
public class ReturnCarReqDTO {

    @NotNull(message = "rentId can not be null")
    private Integer rentInfoId;
}

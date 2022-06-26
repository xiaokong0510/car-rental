package com.xiao.carrental.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
public class RentCarRespDTO {

    private Integer carId;

    private String carModel;

    private Integer num;

    private Integer rentInfoId;

    private LocalDate startTime;

    private LocalDate endTime;
}

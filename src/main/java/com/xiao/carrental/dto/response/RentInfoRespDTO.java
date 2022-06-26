package com.xiao.carrental.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentInfoRespDTO {

    private Integer rentInfoId;

    private Integer carId;

    private String carModel;

    private Integer num;

    private Integer state;

    private LocalDate startTime;

    private LocalDate endTime;

    private LocalDate actualReturnTime;
}

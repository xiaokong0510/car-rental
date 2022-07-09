package com.xiao.carrental.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
@NoArgsConstructor
public class RentCarReqDTO {

    @NotNull(message = "carId can not be null")
    private Integer carId;

    @NotNull(message = "rentNum can not be null")
    private Integer rentNum;

    @NotNull(message = "startTime can not be null")
    private LocalDate startTime;

    @NotNull(message = "rentDay can not be null")
    Integer rentDay;
}

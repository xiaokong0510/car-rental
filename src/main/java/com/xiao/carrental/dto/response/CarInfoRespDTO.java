package com.xiao.carrental.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
public class CarInfoRespDTO {

    private Integer carId;

    private String carModel;

    private Integer inStock;
}

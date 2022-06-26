package com.xiao.carrental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiao.carrental.common.BaseEntity;
import com.xiao.carrental.common.enums.RentStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RentInfo extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * userName
     */
    private String userName;

    /**
     * car id
     */
    private Integer carId;

    /**
     * car Model
     */
    private String carModel;

    /**
     * 出租数量
     */
    private Integer num;

    /**
     * 订单状态
     *
     * @see RentStateEnum
     */
    private Integer state;

    /**
     * 起始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 实际归还时间
     */
    private LocalDate actualReturnTime;
}

package com.xiao.carrental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiao.carrental.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Car extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * carModel
     *
     * @see com.xiao.carrental.common.enums.CarModelEnum
     */
    private String carModel;

    /**
     * inStock
     */
    private Integer inStock;
}

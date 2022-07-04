package com.xiao.carrental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiao.carrental.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;
}

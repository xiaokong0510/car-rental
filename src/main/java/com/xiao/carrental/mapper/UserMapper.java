package com.xiao.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.carrental.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

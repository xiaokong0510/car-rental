package com.xiao.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.carrental.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {
    /**
     * 减少库存
     *
     * @param carId
     * @param rentNum
     * @return
     */
    int decreaseStock(@Param("carId") int carId, @Param("rentNum") int rentNum);

    /**
     * 增加库存
     *
     * @param carId
     * @param returnNum
     * @return
     */
    int increaseStock(@Param("carId") int carId, @Param("returnNum") int returnNum);
}

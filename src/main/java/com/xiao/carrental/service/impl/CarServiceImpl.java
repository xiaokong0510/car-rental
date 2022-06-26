package com.xiao.carrental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.carrental.common.ReturnCode;
import com.xiao.carrental.common.enums.RentStateEnum;
import com.xiao.carrental.entity.Car;
import com.xiao.carrental.entity.RentInfo;
import com.xiao.carrental.exception.BusinessException;
import com.xiao.carrental.mapper.CarMapper;
import com.xiao.carrental.mapper.RentInfoMapper;
import com.xiao.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RentInfoMapper rentInfoMapper;

    @Override
    public List<Car> findAllCars() {
        return carMapper.selectList(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RentInfo rentCar(RentInfo rentInfo) {
        Integer carId = rentInfo.getCarId();
        Car currentCar = carMapper.selectById(carId);
        if (Objects.isNull(currentCar)) {
            throw new BusinessException(ReturnCode.CAR_NOT_EXISTS);
        }
        if (rentInfo.getNum() > currentCar.getInStock()) {
            throw new BusinessException(ReturnCode.STOCK_IS_INSUFFICIENT);
        }
        carMapper.decreaseStock(rentInfo.getCarId(), rentInfo.getNum());
        rentInfo.setCarModel(currentCar.getCarModel());
        rentInfo.setState(RentStateEnum.IN_RENTING.getCode());
        rentInfoMapper.insert(rentInfo);
        return rentInfoMapper.selectById(rentInfo.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RentInfo returnCar(int rentId, String userName) {
        QueryWrapper<RentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RentInfo::getId, rentId)
                .eq(RentInfo::getUserName, userName);
        RentInfo rentInfo = rentInfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(rentInfo)) {
            throw new BusinessException(ReturnCode.RENT_INFO_NOT_EXISTS);
        }
        if (Objects.equals(RentStateEnum.RETURNED.getCode(), rentInfo.getState())) {
            throw new BusinessException(ReturnCode.CAR_HAS_BEEN_RETURN);
        }
        rentInfo.setState(RentStateEnum.RETURNED.getCode());
        rentInfo.setActualReturnTime(LocalDate.now());
        rentInfoMapper.updateById(rentInfo);
        //归还库存
        carMapper.increaseStock(rentInfo.getCarId(), rentInfo.getNum());
        return rentInfoMapper.selectById(rentId);
    }
}

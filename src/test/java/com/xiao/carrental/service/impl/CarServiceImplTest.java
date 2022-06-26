package com.xiao.carrental.service.impl;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.xiao.carrental.common.ReturnCode;
import com.xiao.carrental.common.enums.RentStateEnum;
import com.xiao.carrental.entity.Car;
import com.xiao.carrental.entity.RentInfo;
import com.xiao.carrental.exception.BusinessException;
import com.xiao.carrental.mapper.CarMapper;
import com.xiao.carrental.mapper.RentInfoMapper;
import com.xiao.carrental.service.CarService;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    private final CarService carService = new CarServiceImpl();

    @Mock
    private CarMapper carMapper;

    @Mock
    private RentInfoMapper rentInfoMapper;

    private RentInfo rentInfo;

    @BeforeEach
    void setUp() {
        this.rentInfo = mockRentInfo();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), Car.class);
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), RentInfo.class);
    }

    @Test
    void rentCar_whenCarNotExists_thenThrowCarNotExists() {
        when(carMapper.selectById(any())).thenReturn(null);
        BusinessException exception = assertThrows(BusinessException.class, () -> carService.rentCar(rentInfo));
        assertEquals(ReturnCode.CAR_NOT_EXISTS, exception.getReturnCode());
    }

    @Test
    void rentCar_whenStockIsInsufficient_thenThrowStockIsInsufficient() {
        Car car = new Car();
        car.setId(1001);
        car.setCarModel("BMW 650");
        car.setInStock(0);
        when(carMapper.selectById(any())).thenReturn(car);
        BusinessException exception = assertThrows(BusinessException.class, () -> carService.rentCar(rentInfo));
        assertEquals(ReturnCode.STOCK_IS_INSUFFICIENT, exception.getReturnCode());
    }

    @Test
    void rentCar_ThenReturnSuccess() {
        Car car = new Car();
        car.setId(1001);
        car.setCarModel("BMW 650");
        car.setInStock(2);
        when(carMapper.selectById(any())).thenReturn(car);
        when(rentInfoMapper.selectById(any())).thenReturn(rentInfo);
        RentInfo result = carService.rentCar(rentInfo);
        assertEquals(1001, result.getCarId());
        assertEquals(1, result.getNum());
        assertEquals(LocalDate.parse("2022-06-26"), result.getStartTime());
        assertEquals(RentStateEnum.IN_RENTING.getCode(), result.getState());
    }

    @Test
    void returnCar_whenRentInfoNotExists_thenThrowRentInfoNotExists() {
        when(rentInfoMapper.selectOne(any())).thenReturn(null);
        BusinessException exception = assertThrows(BusinessException.class, () -> carService.returnCar(1001, "test"));
        assertEquals(ReturnCode.RENT_INFO_NOT_EXISTS, exception.getReturnCode());
    }

    @Test
    void returnCar_whenCarHasBeenReturn_thenThrowCarHasBeenReturn() {
        RentInfo rentInfo = new RentInfo();
        rentInfo.setState(RentStateEnum.RETURNED.getCode());
        when(rentInfoMapper.selectOne(any())).thenReturn(rentInfo);
        BusinessException exception = assertThrows(BusinessException.class, () -> carService.returnCar(1001, "test"));
        assertEquals(ReturnCode.CAR_HAS_BEEN_RETURN, exception.getReturnCode());
    }

    @Test
    void returnCar_ThenReturnSuccess() {
        RentInfo rentInfo = new RentInfo();
        rentInfo.setState(RentStateEnum.IN_RENTING.getCode());
        rentInfo.setCarId(1001);
        rentInfo.setNum(1);
        when(rentInfoMapper.selectOne(any())).thenReturn(rentInfo);
        when(rentInfoMapper.selectById(1001)).thenAnswer(i -> {
            RentInfo currentRentInfo = this.rentInfo;
            currentRentInfo.setState(RentStateEnum.RETURNED.getCode());
            return currentRentInfo;
        });
        RentInfo result = carService.returnCar(1001, "test");
        assertEquals(1001, result.getCarId());
        assertEquals(1, result.getNum());
        assertEquals(LocalDate.parse("2022-06-26"), result.getStartTime());
        assertEquals(RentStateEnum.RETURNED.getCode(), result.getState());
    }

    private RentInfo mockRentInfo() {
        RentInfo rentInfo = new RentInfo();
        rentInfo.setUserName("test");
        rentInfo.setCarId(1001);
        rentInfo.setNum(1);
        rentInfo.setStartTime(LocalDate.parse("2022-06-26"));
        rentInfo.setEndTime(LocalDate.parse("2022-06-27"));
        return rentInfo;
    }
}
package com.xiao.carrental.service;

import com.xiao.carrental.entity.Car;
import com.xiao.carrental.entity.RentInfo;

import java.util.List;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
public interface CarService {

    List<Car> findAllCars();

    RentInfo rentCar(RentInfo rentInfo);

    RentInfo returnCar(int rentId, String userName);
}

package com.xiao.carrental.controller;

import com.xiao.carrental.dto.request.RentCarReqDTO;
import com.xiao.carrental.dto.request.ReturnCarReqDTO;
import com.xiao.carrental.dto.response.*;
import com.xiao.carrental.entity.Car;
import com.xiao.carrental.entity.RentInfo;
import com.xiao.carrental.service.CarService;
import com.xiao.carrental.service.RentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@RestController
@RequestMapping("/v1/car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private RentInfoService rentInfoService;

    /**
     * 查询所有可用车辆信息
     *
     * @return
     */
    @GetMapping("/car-info")
    public CommonResult<List<CarInfoRespDTO>> getCarInfo() {
        List<Car> allCars = carService.findAllCars();
        List<CarInfoRespDTO> carInfoRespList = new ArrayList<>();
        allCars.forEach(x -> {
            CarInfoRespDTO carInfo = new CarInfoRespDTO();
            BeanUtils.copyProperties(x, carInfo);
            carInfo.setCarId(x.getId());
            carInfoRespList.add(carInfo);
        });
        return CommonResult.success(carInfoRespList);
    }

    /**
     * 查询用户的租车信息
     *
     * @param userName
     * @return
     */
    @GetMapping("/rent-info")
    public CommonResult<List<RentInfoRespDTO>> getRentInfo(@RequestParam("userName") String userName) {
        List<RentInfo> rentInfos = rentInfoService.findByUserName(userName);
        List<RentInfoRespDTO> rentInfoRespDTOList = new ArrayList<>();
        rentInfos.forEach(x -> {
            RentInfoRespDTO rentInfoRespDTO = new RentInfoRespDTO();
            BeanUtils.copyProperties(x, rentInfoRespDTO);
            rentInfoRespDTO.setRentInfoId(x.getId());
            rentInfoRespDTOList.add(rentInfoRespDTO);
        });
        return CommonResult.success(rentInfoRespDTOList);
    }

    /**
     * 租车
     *
     * @param rentCarReq
     * @return
     */
    @PostMapping("/rent")
    public CommonResult<RentCarRespDTO> rentCar(@RequestBody @Valid RentCarReqDTO rentCarReq) {
        RentInfo rentInfo = new RentInfo();
        BeanUtils.copyProperties(rentCarReq, rentInfo);
        rentInfo.setNum(rentCarReq.getRentNum());
        rentInfo.setEndTime(rentCarReq.getStartTime().plusDays(rentCarReq.getRentDay()));
        RentInfo result = carService.rentCar(rentInfo);
        RentCarRespDTO rentCarResp = new RentCarRespDTO();
        BeanUtils.copyProperties(result, rentCarResp);
        rentCarResp.setRentInfoId(result.getId());
        return CommonResult.success(rentCarResp);
    }

    @PostMapping("/return")
    public CommonResult<RentInfoRespDTO> returnCar(@RequestBody @Valid ReturnCarReqDTO rentCarReq) {
        RentInfo rentInfo = carService.returnCar(rentCarReq.getRentId(), rentCarReq.getUserName());
        RentInfoRespDTO rentInfoResp = new RentInfoRespDTO();
        BeanUtils.copyProperties(rentInfo, rentInfoResp);
        return CommonResult.success(rentInfoResp);
    }
}

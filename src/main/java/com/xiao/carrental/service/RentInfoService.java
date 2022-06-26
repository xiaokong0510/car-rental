package com.xiao.carrental.service;

import com.xiao.carrental.entity.RentInfo;

import java.util.List;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
public interface RentInfoService {

    List<RentInfo> findByUserName(String userName);
}

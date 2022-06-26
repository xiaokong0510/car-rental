package com.xiao.carrental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.carrental.entity.RentInfo;
import com.xiao.carrental.mapper.RentInfoMapper;
import com.xiao.carrental.service.RentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Service
public class RentInfoServiceImpl implements RentInfoService {

    @Autowired
    private RentInfoMapper rentInfoMapper;

    @Override
    public List<RentInfo> findByUserName(String userName) {
        QueryWrapper<RentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RentInfo::getUserName, userName);
        return rentInfoMapper.selectList(queryWrapper);
    }
}

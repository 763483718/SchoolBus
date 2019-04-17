package com.example.answer.service.impl;

import com.example.answer.bean.Driver;
import com.example.answer.dto.DriverDTO;
import com.example.answer.mapper.DriverMapper;
import com.example.answer.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DriverServiceimpl implements DriverService {
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public DriverDTO getDriverInfo(String tel) {
        DriverDTO driverDTO = driverMapper.getDriverInfo(tel);
        if (driverDTO.getIsDepart() == 0) {
            driverDTO.setIsDepartName("未发车");
        } else {
            driverDTO.setIsDepartName("已发车");
        }
        return driverDTO;
    }

    @Override
    public int setStopIndex(int busID, int routeID, int currentIndex, int direction) {
        int isDepart = 1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
//        System.out.println();// new Date()为获取当前系统时间
        return driverMapper.setStopIndex(busID, routeID, isDepart, currentIndex, time, direction);
    }

    @Override
    public int setBusStart(int busID, int routeID, String driverAccount, int direction) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());

        return driverMapper.setBusStart(busID, routeID, time, direction) + driverMapper.setBusIDOfDriver(busID, driverAccount);
    }


    @Override
    public int setTerminalIndex(int busID, int currentIndex) {
        int isDepart = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        return driverMapper.setTerminalIndex(busID, isDepart, currentIndex, time);
    }

    @Override
    public int setBusPosition(int busID, String position) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        return driverMapper.setBusPosition(busID, position, time);
    }

}

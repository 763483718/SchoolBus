package com.example.answer.service;

import com.example.answer.dto.BusDTO;
import com.example.answer.dto.BusWorkingDTO;

import java.util.List;

public interface PassengerService {

    // 获取运行中司机的信息
    public List<BusWorkingDTO> getWorkingList(String lat,String lng);


    //获取校车位置
    public BusDTO getBusPosition(int busID);

    public List<BusDTO> getBusPositionList();
}

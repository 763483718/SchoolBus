package com.example.answer.service;

import com.example.answer.dto.BusWorkingDTO;
import com.example.answer.dto.CodeDTO;

import java.util.List;

public interface PassengerService {

    // 获取运行中司机的信息
    public List<BusWorkingDTO> getWorkingList(String lat,String lng);
}

package com.example.answer.service;

import com.example.answer.dto.StopDTO;
import com.example.answer.dto.StopDetailDTO;
import com.example.answer.dto.StopInfoDTO;
import com.example.answer.dto.StopNameDTO;

import java.util.List;

public interface StopService {

    // 获取所有站点坐标
    public List<StopDTO> getStopPosition();

    // 根据站点ID获取站点信息
    public StopInfoDTO getStopInfo(int stopCode);

    // 获取所有站点坐标与站点名称
    public List<StopNameDTO> getStopName();

    // 根据站点名与校车ID获取站点详情信息
    public StopDetailDTO getStopDetail(String stopName,int busCode,int stopIndex);

    //根据站点ID获取站点名字
    public StopInfoDTO getStopNameByID(int stopCode);


    //根据站点名字获取站点ID
    public StopDTO getStopIDByName(String stopName);
}

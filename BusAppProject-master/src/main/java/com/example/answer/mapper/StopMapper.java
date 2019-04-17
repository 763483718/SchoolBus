package com.example.answer.mapper;

import com.example.answer.bean.Driver;
import com.example.answer.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface StopMapper {
    // 获取所有站点坐标
    @Select("SELECT stopID,lng,lat FROM stop")
    public List<StopDTO> getStopPosition();

    // 根据StopID获取站点信息
    @Select("SELECT stopName,lng,lat,relateRouteID FROM stop where stopID = #{stopCode}")
    public StopInfoDTO getStopInfo(@Param("stopCode") int stopCode);

    // 获取所有站点坐标与站点名称
    @Select("SELECT stopID,stopName,lng,lat FROM stop")
    public List<StopNameDTO> getStopName();


    // 根据StopID获取relateRouteID
    @Select("SELECT relateRouteID FROM stop Where stopID = #{stopID}")
    public String getRelateRouteByID(@Param("stopID") int stopID);

    // 根据StopName获取站点经纬度
    @Select("SELECT stopID,lng,lat FROM stop where stopID = (SELECT stopID FROM stop where stopName = #{stopName})")
    public StopDTO getStopByName(@Param("stopName") String stopName);

    @Select("SELECT lat,lng,stopID,stopName FROM stop")
    public List<StopPositionDTO> getStopPositionAll();


}

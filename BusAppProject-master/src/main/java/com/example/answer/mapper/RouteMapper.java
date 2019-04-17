package com.example.answer.mapper;

import com.example.answer.bean.Route;
import com.example.answer.dto.BusDetailDTO;
import com.example.answer.dto.RouteInfoDTO;
import com.example.answer.dto.RouteStopDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteMapper {
    // 获取 Route
    @Select("SELECT * FROM route")
    public List<Route> getRouteList();

    // 获取 Route 大致信息
    @Select("SELECT routeID,routeName,byName,startStop,endStop,startTime,endTime,price,direction FROM route")
    public List<RouteInfoDTO> getRouteInfoList();

    // 根据 RouteID 获取Route 信息
    @Select("SELECT routeID,routeName,byName,startStop,endStop,startTime,endTime,price,direction FROM route WHERE routeID = #{routeID}")
    public RouteInfoDTO getRouteInfoByID(@Param("routeID") int routeID);

    // 获取 线路站点信息
    @Select("SELECT routeID,routeName,stopList FROM route WHERE routeID = #{routeID} ")
    public RouteStopDTO getRouteStopList(@Param("routeID") int routeID);

    // 根据 RouteID 获取 RouteName
    @Select("SELECT routeName FROM route WHERE routeID = #{routeID} ")
    public String getRouteNameById(@Param("routeID") int routeID);

    // 获取校车详细信息
    @Select("SELECT route.`startStop`,route.`endStop`,route.`startTime`,route.`endTime`,route.`price`,route.`stopList`,bus.`currentStopIndex` from route,bus where route.`routeID` = #{routeID} AND bus.`busID` = #{busID}")
    public BusDetailDTO getBusDetail(@Param("routeID") int routeID, @Param("busID") int busID);

    @Select("SELECT position from stop where stopname=#{stopName}")
    public String getPosition(@Param("stopName") String stopName);

}

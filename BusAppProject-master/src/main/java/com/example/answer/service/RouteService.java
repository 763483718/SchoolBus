package com.example.answer.service;

import com.example.answer.bean.Collect;
import com.example.answer.bean.Route;
import com.example.answer.dto.BusDetailDTO;
import com.example.answer.dto.RouteInfoDTO;
import com.example.answer.dto.RouteStopDTO;

import java.util.List;

public interface RouteService {
    // 获取 route 列表
    public List<Route> getRouteList();

    // 获取 route 大致信息列表
    public List<RouteInfoDTO> getRouteInfoList(int stopID);

    // 获取 route 站牌线路
    public RouteStopDTO getRouteStopList(int routeID, int direction);

    // 获取收藏的route 列表
    public List<RouteInfoDTO> getCollectRouteList(String tel);

    // 获取收藏的routeID 数组
    public List<Collect> getCollectList(String tel);

    // 新增线路收藏
    public int createCollect(int userID,int routeID,int direction);

    // 取消线路收藏
    public int deleteCollect(int userID,int routeID,int direction);

    // 获取校车详情
    public BusDetailDTO getBusDetail(int routeID, int busID, String nearStopName,int direction);

    public String getPosition(String stopName);
}

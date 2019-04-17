package com.example.answer.service.impl;

import com.example.answer.bean.Admin;
import com.example.answer.bean.Collect;
import com.example.answer.bean.Route;
import com.example.answer.dto.BusDetailDTO;
import com.example.answer.dto.RouteInfoDTO;
import com.example.answer.dto.RouteStopDTO;
import com.example.answer.mapper.CollectMapper;
import com.example.answer.mapper.RouteMapper;
import com.example.answer.mapper.StopMapper;
import com.example.answer.service.RouteService;
import com.example.answer.util.CommonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceimpl implements RouteService{
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private StopMapper stopMapper;
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<Route> getRouteList() { return routeMapper.getRouteList(); }

    @Override
    public List<RouteInfoDTO> getRouteInfoList(int stopID) {
        if(stopID == -1) {
            System.out.println("getRouteInfoList() -没有传参数！");
            return routeMapper.getRouteInfoList();
        } else {
            List<RouteInfoDTO> ridList = new ArrayList<>();
            String relateRoute = stopMapper.getRelateRouteByID(stopID);
            String[] routeIdArray = relateRoute.substring(1,relateRoute.length()-1).split(",");
            for (int i = 0;i<routeIdArray.length;i++) {
                ridList.add(routeMapper.getRouteInfoByID(Integer.parseInt(routeIdArray[i])));
            }
            return ridList;
        }
    }

    @Override
    public RouteStopDTO getRouteStopList(int routeID, int direction) {
        RouteStopDTO stopList = routeMapper.getRouteStopList(routeID);
        if(stopList == null) {
            System.out.println("stopList 为null ");
        } else {
            if(direction == 0){
                if(stopList.getStopList() == null) {

                } else {
                    JSONObject jsonObjectStopList=JSONObject.fromObject(stopList.getStopList());
                    stopList.setStopList(jsonObjectStopList.getString("forward").replaceAll("\"","\'"));
                }
            } else {
                if(stopList.getStopList() == null) {

                } else {
                    JSONObject jsonObjectStopList=JSONObject.fromObject(stopList.getStopList());
                    stopList.setStopList(jsonObjectStopList.getString("reverse").replaceAll("\"","\'"));
                }
            }
        }
        return stopList;
    }
    @Override
    public List<RouteInfoDTO> getCollectRouteList(String tel) {
        List<Collect> collectList = collectMapper.getCollectList(tel);
        List<RouteInfoDTO> routeInfoList = new ArrayList<>();
        for(int i = 0; i < collectList.size();i++) {
            routeInfoList.add(routeMapper.getRouteInfoByID(collectList.get(i).getRouteID()));
        }
        return routeInfoList;
    }
    @Override
    public List<Collect> getCollectList(String tel) {
        return collectMapper.getCollectList(tel);
    }
    @Override
    public int createCollect(int userID,int routeID,int direction) {
        return collectMapper.createCollect(userID,routeID,direction);
    }

    @Override
    public int deleteCollect(int userID,int routeID,int direction) {
        return collectMapper.deleteCollect(userID,routeID,direction);
    }

    @Override
    public BusDetailDTO getBusDetail(int routeID, int busID, String nearStopName, int direction){
        BusDetailDTO bdd = routeMapper.getBusDetail(routeID,busID);

        if(direction == 0){
            JSONObject jsonObjectStopList = JSONObject.fromObject(bdd.getStopList());
            String stopList = jsonObjectStopList.getString("forward").replaceAll("\"","\'");
            JSONObject jsonObjectStartStop=JSONObject.fromObject(bdd.getStartStop());
            String startStop = jsonObjectStartStop.getString("forward").replaceAll("\"","\'");
            JSONObject jsonObjectEndStop=JSONObject.fromObject(bdd.getEndStop());
            String endStop = jsonObjectEndStop.getString("forward").replaceAll("\"","\'");
            bdd.setStopList(stopList);
            bdd.setStartStop(startStop);
            bdd.setEndStop(endStop);
            int hoverIndex = CommonUtils.getNearIndex(stopList,nearStopName);
            if(hoverIndex == -1) {
                // 等于车辆当前到站加1
                hoverIndex = bdd.getCurrentStopIndex() + 1 ;
            }
            bdd.setStopHoverIndex(hoverIndex);
//            System.out.println("index:"+CommonUtils.getNearIndex(stopList,nearStopName));
        } else {
            JSONObject jsonObjectStopList = JSONObject.fromObject(bdd.getStopList());
            String stopList = jsonObjectStopList.getString("reverse").replaceAll("\"","\'");
            JSONObject jsonObjectStartStop=JSONObject.fromObject(bdd.getStartStop());
            String startStop = jsonObjectStartStop.getString("reverse").replaceAll("\"","\'");
            JSONObject jsonObjectEndStop=JSONObject.fromObject(bdd.getEndStop());
            String endStop = jsonObjectEndStop.getString("reverse").replaceAll("\"","\'");
            bdd.setStopList(stopList);
            bdd.setStartStop(startStop);
            bdd.setEndStop(endStop);
            int hoverIndex = CommonUtils.getNearIndex(stopList,nearStopName);
            if(hoverIndex == -1) {
                // 等于车辆当前到站加1
                hoverIndex = bdd.getCurrentStopIndex() + 1 ;
            }
            bdd.setStopHoverIndex(hoverIndex);
//            System.out.println("index:"+CommonUtils.getNearIndex(stopList,nearStopName));

        }
        return bdd;
    }

    @Override
    public String getPosition(String stopName){
        return routeMapper.getPosition(stopName);
    }

}

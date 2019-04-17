package com.example.answer.service.impl;

import com.example.answer.dto.*;
import com.example.answer.mapper.PassengerMapper;
import com.example.answer.mapper.RouteMapper;
import com.example.answer.mapper.StopMapper;
import com.example.answer.service.StopService;
import com.example.answer.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StopServiceimpl implements StopService {
    @Autowired
    private StopMapper stopMapper;
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public List<StopDTO> getStopPosition() {
        return stopMapper.getStopPosition();
    }

    @Override
    public StopInfoDTO getStopInfo(int stopCode) {
        StopInfoDTO stopInfoDT0 = stopMapper.getStopInfo(stopCode);
        if (stopInfoDT0 == null) {
            return stopInfoDT0;
        }
        String relateRouteID = stopInfoDT0.getRelateRouteID();
        String[] routeIdArray = relateRouteID.substring(1, relateRouteID.length() - 1).split(",");
//        ArrayList<String> routeNameArray = new ArrayList<String> ();
        List<RouteNameDTO> routeNameList = new ArrayList<>();
        for (int i = 0; i < routeIdArray.length; i++) {
            RouteNameDTO routeName = new RouteNameDTO();
            routeName.setRouteID(Integer.parseInt(routeIdArray[i]));
            routeName.setRouteName(routeMapper.getRouteNameById(Integer.parseInt(routeIdArray[i])));
            routeNameList.add(routeName);
        }
        stopInfoDT0.setRouteNameList(routeNameList);
//        stopInfoDT0.setRelateRouteID(routeNameArray.toString());
//        System.out.println(routeNameArray);
//        System.out.println(relateRouteID.substring(1,relateRouteID.length()-1));
//        System.out.println(array[0]);
//        int RouteID = Integer.parseInt(array[0]);

        return stopInfoDT0;
    }

    @Override
    public List<StopNameDTO> getStopName() {
        return stopMapper.getStopName();
    }

    @Override
    public StopDetailDTO getStopDetail(String stopName, int busCode, int stopIndex) {
        BusDTO bd = passengerMapper.getBusPositionById(busCode);
        System.out.println("stopName:" + stopName);
        StopDTO sd = stopMapper.getStopByName(stopName);
        List<StopPositionDTO> stopList = stopMapper.getStopPositionAll();
        String stopLat = "";
        String stopLng = "";
        for (int i = 0; i < stopList.size(); i++) {
            if (stopList.get(i).getStopName().equals(stopName)) {
                stopLat = stopList.get(i).getLat();
                stopLng = stopList.get(i).getLng();
                break;
            }
        }
        System.out.println("stopLat:" + stopLat);
        System.out.println("stopLng:" + stopLng);
//        List<StopDTO> sdList = stopMapper.getStopPosition();
        StopDetailDTO sdd = new StopDetailDTO();
        sdd.setStopName(stopName);
        if (stopLat == "" || stopLng == "") {
            sdd.setStopStatus("所点击的站点不在线路站点列表中！！");
            return sdd;
        }
        if (stopIndex - bd.getCurrentStopIndex() > 0) {
//            int time = (stopIndex - bd.getCurrentStopIndex())*7;

//            sdd.setEstimatedTime(Integer.toString(time));
            sdd.setOffsetStop(stopIndex - bd.getCurrentStopIndex());
            sdd.setStopStatus("2");
            double distance = CommonUtils.getDistance(Double.parseDouble(bd.getLat()), Double.parseDouble(bd.getLng()), Double.parseDouble(stopLat), Double.parseDouble(stopLng));
            sdd.setEstimatedTime(Integer.toString((int) (distance / 1000 * 3.5)));
            sdd.setDistance(Integer.toString((int) distance));
        } else if (stopIndex - bd.getCurrentStopIndex() == 0) {
            int time = (stopIndex - bd.getCurrentStopIndex()) * 7;
            sdd.setEstimatedTime(Integer.toString(time));
            sdd.setOffsetStop(stopIndex - bd.getCurrentStopIndex());
            sdd.setStopStatus("1");
            double distance = CommonUtils.getDistance(Double.parseDouble(bd.getLat()), Double.parseDouble(bd.getLng()), Double.parseDouble(stopLat), Double.parseDouble(stopLng));
            sdd.setDistance(Integer.toString((int) distance));
        } else {
            sdd.setOffsetStop(stopIndex - bd.getCurrentStopIndex());
            sdd.setStopStatus("0");
        }
        return sdd;
    }


    @Override
    public StopInfoDTO getStopNameByID(int stopCode) {
        return stopMapper.getStopInfo(stopCode);
    }


    @Override
    public StopDTO getStopIDByName(String stopName) {
        return stopMapper.getStopByName(stopName);
    }
}

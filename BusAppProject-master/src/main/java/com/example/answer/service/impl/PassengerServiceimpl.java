package com.example.answer.service.impl;

import com.example.answer.dto.BusDTO;
import com.example.answer.dto.BusWorkingDTO;
import com.example.answer.dto.CodeDTO;
import com.example.answer.mapper.PassengerMapper;
import com.example.answer.service.PassengerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerServiceimpl implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;


    // 传入最近站点 lat lng
    public List<BusWorkingDTO> getWorkingList(String lat, String lng) {
        List<CodeDTO> cdList = passengerMapper.getWorkingCodeList();
        List<BusWorkingDTO> bwdList = new ArrayList<>();
        for (int i = 0; i < cdList.size(); i++) {
            BusWorkingDTO bwd = passengerMapper.getWorkingList(cdList.get(i).getBusID(), cdList.get(i).getRouteID(), cdList.get(i).getDriverID());
            if (bwd == null) {
                continue;
            } else {
//                double distance = CommonUtils.getDistance(Double.parseDouble(lat), Double.parseDouble(lng), Double.parseDouble(bwd.getLat()), Double.parseDouble(bwd.getLng()));
//                int time = (int) (distance/1000*3.5);
                double distance=0;


                bwd.setForecastTime(Integer.toString((int) (distance / 1000 * 3.5)));
//                System.out.println();
                bwd.setDistance(Integer.toString((int) distance));
                if (bwd.getDirection() == 0) {
                    JSONObject jsonObjectStartStop = JSONObject.fromObject(bwd.getStartStop());
                    bwd.setStartStop(jsonObjectStartStop.getString("forward").replaceAll("\"", "\'"));
                    JSONObject jsonObjectEndStop = JSONObject.fromObject(bwd.getEndStop());
                    bwd.setEndStop(jsonObjectEndStop.getString("forward").replaceAll("\"", "\'"));
                } else {
                    JSONObject jsonObjectStartStop = JSONObject.fromObject(bwd.getStartStop());
                    bwd.setStartStop(jsonObjectStartStop.getString("reverse").replaceAll("\"", "\'"));
                    JSONObject jsonObjectEndStop = JSONObject.fromObject(bwd.getEndStop());
                    bwd.setEndStop(jsonObjectEndStop.getString("reverse").replaceAll("\"", "\'"));
                }
                bwdList.add(bwd);
            }


        }
        return bwdList;
    }
    public List<BusDTO> getBusPositionList(){
        List<BusDTO> busDTOList = new ArrayList<>();

        for(int i = 1;i <= 1; i++){
            BusDTO busDTO = passengerMapper.getBusPositionById(i);
            if(busDTO!=null){
                busDTOList.add(busDTO);
            }
        }
        return busDTOList;
    }

    public BusDTO getBusPosition(int busID){
        BusDTO busDTO = passengerMapper.getBusPositionById(busID);
        return busDTO;
    }
}

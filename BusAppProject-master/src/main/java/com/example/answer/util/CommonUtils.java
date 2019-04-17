package com.example.answer.util;

import com.example.answer.dto.StopDTO;
import com.example.answer.dto.StopNameDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonUtils {
    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }


    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    /**
     * 通过经纬度获取最近距离的经纬度信息
     * @param lat
     * @param lng
     * @param stopList
     */
    public static StopDTO getNearPosition(double lat, double lng, List<StopDTO> stopList){
        System.out.println("getNearPosition:" + lat);
        System.out.println("getNearPosition:" + lng);
        double minDistance = getDistance(lat,lng,Double.parseDouble(stopList.get(0).getLat()),Double.parseDouble(stopList.get(0).getLng()));
        int minIndex = 0;
        for(int i = 1 ; i < stopList.size(); i++){
            double distance = getDistance(lat,lng,Double.parseDouble(stopList.get(i).getLat()),Double.parseDouble(stopList.get(i).getLng()));
            System.out.println(i + ":" + (int) distance);
            System.out.println("stopID:" + stopList.get(i).getStopID());
            if(distance < minDistance) {
                minDistance = distance;
                minIndex = i;
            }
        }
        System.out.println(stopList.get(minIndex));
        return stopList.get(minIndex);
    }

    /**
     * 获取一定范围内的站点数组
     * @param lat
     * @param lng
     * @param stopNameList
     * @param distance
     * @return
     */
    public static List<StopNameDTO> getRangeList(double lat,double lng,List<StopNameDTO> stopNameList, double distance) {
        List<StopNameDTO> snd = new ArrayList<>();
        for(int i = 0;i < stopNameList.size();i++){
            double presentDistance = getDistance(lat,lng,Double.parseDouble(stopNameList.get(i).getLat()),Double.parseDouble(stopNameList.get(i).getLng()));
            if(presentDistance < distance) {
                int pd = (int) presentDistance;
                stopNameList.get(i).setDistance(Integer.toString(pd));
                snd.add(stopNameList.get(i));
            }
        }
        if(snd.size() == 0) {
            return snd;
        }
        Collections.sort(snd);
        return snd;
    }

    /**
     * 获取最近站点编号
     * @param stopList
     * @param stopName
     * @return
     */
    public static int getNearIndex(String stopList,String stopName) {
        stopList = stopList.replaceAll("'","");
        String[] stopNameArray = stopList.substring(1,stopList.length()-1).split(",");
//        System.out.println("stopName:"+stopName);
        for(int i =0 ;i <stopNameArray.length;i++) {
           if(stopNameArray[i].equals(stopName)) {
               return i;
           }
        }
        return -1;
    }


}

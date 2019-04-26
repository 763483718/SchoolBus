package com.example.answer.service;

import com.example.answer.dto.DriverDTO;

public interface DriverService {
    // 获取司机信息
    public DriverDTO getDriverInfo(String tel);

    // 更新司机站点情况
    public int setStopIndex(int busID, int routeID, int currentIndex, int direction);

    // 校车发车
    public int setBusStart(int busID, int routeId, String driverAccount, int direction);

    // 司机抵达终点
    public int setTerminalIndex(int busID, int currentIndex);

    // 更新司机坐标
    public int setBusPosition(int busID, String lat, String lng);
}

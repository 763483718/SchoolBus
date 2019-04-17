package com.example.answer.service;

import com.example.answer.dto.DriverInfoDTO;
import com.example.answer.dto.UserInfoDTO;

public interface UserService {

    // 设置用户名
    public int setUserName(String role,String tel,String name);

    // 设置用户性别
    public int setUserSex(String role,String tel,int sex);

    // 设置用户生日
    public int setUserBirthday(String role,String tel,String birthday);

    // 获取用户信息
    public UserInfoDTO getUserInfo(String role, String tel);

    public DriverInfoDTO getDriverInfo(String role,String tel);
}

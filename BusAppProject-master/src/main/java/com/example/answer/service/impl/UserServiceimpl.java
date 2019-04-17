package com.example.answer.service.impl;

import com.example.answer.dto.DriverInfoDTO;
import com.example.answer.dto.UserInfoDTO;
import com.example.answer.mapper.DriverMapper;
import com.example.answer.mapper.UserMapper;
import com.example.answer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public int setUserName(String role,String tel,String name) {
        if(role == "driver") {
            return driverMapper.setDriverName(name,tel);
        }else {
            return userMapper.setUserName(name,tel);
        }
    }
    @Override
    public int setUserSex(String role,String tel,int sex) {
        if(role == "driver") {
            return driverMapper.setDriverSex(sex,tel);
        }else {
            return userMapper.setUserSex(sex,tel);
        }
    }
    @Override
    public int setUserBirthday(String role,String tel,String birthday) {
        if(role == "driver") {
            return driverMapper.setDriverBirthday(birthday,tel);
        }else {
            return userMapper.setUserBirthday(birthday,tel);
        }
    }
    @Override
    public UserInfoDTO getUserInfo(String role, String tel) {
//        UserInfoDTO uid = new UserInfoDTO();
        System.out.println("getUserInfo - role:" + role);
        System.out.println("getUserInfo - tel:" + tel);
        if (role.equals("user")) {
            UserInfoDTO user = userMapper.getUserInfo(tel);
            return user;
        }
        return null;
    }

    @Override
    public DriverInfoDTO getDriverInfo(String role,String tel){
        if(role.equals("driver")) {
            return driverMapper.getDriverUserInfo(tel);
        }
        return null;
    }
}

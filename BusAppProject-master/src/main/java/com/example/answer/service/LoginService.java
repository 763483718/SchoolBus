package com.example.answer.service;

import com.aliyuncs.exceptions.ClientException;
import com.example.answer.bean.Driver;
import com.example.answer.bean.User;

import java.util.Map;

public interface LoginService {
    // 判断账号是否为司机账号
    public boolean isValidDriver(String tel);

    // 判断账号是否为乘客账号
    public boolean isVaildUser(String tel);

    //通过账号判断是否为乘客
    public User isVaildUserByAccount(String account);

    public Driver isValidDriverByAccount(String account);

    public int setPassWord(String tel,String newPassWord);

    // 创建乘客账号
    public int createUser(String tel);

    // 判断账号密码是否正确
    public boolean isValidAccount(String tel,String code);

    // 获取短信验证码
    public Map<String, Object> getVerificaCode(String tel) throws ClientException;

    // 根据 手机号 获取 userID
    public int getUserIDByTel(String tel);

}

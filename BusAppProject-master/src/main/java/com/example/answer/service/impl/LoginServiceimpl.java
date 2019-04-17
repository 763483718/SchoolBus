package com.example.answer.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.example.answer.bean.Driver;
import com.example.answer.bean.User;
import com.example.answer.mapper.LoginMapper;
import com.example.answer.service.LoginService;
import com.example.answer.util.AliyunMessageUtil;
import com.example.answer.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceimpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;

//    public boolean isValidDriver(String tel);
    @Override
    public boolean isValidDriver(String tel){
        List<Driver> dirver = loginMapper.isValidDriver(tel);
        if(dirver == null || dirver.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public  Driver isValidDriverByAccount(String account){
        Driver driver=loginMapper.isValidDriverByAccount(account);
        if(null==driver){
            return null;
        }
        return driver;
    }
    @Override
    public User isVaildUserByAccount(String account){
        User user=loginMapper.isVaildUserByAccount(account);
        if(null==user){
            return null;
        }
        return user;
    }


    @Override
    public boolean isVaildUser(String tel){
        List<User> user = loginMapper.isVaildUser(tel);
        if(user == null || user.size() == 0) {
            return false;
        }
        return true;
    }
    @Override
    // 创建乘客账号
    public int createUser(String tel){
        return loginMapper.createUser(tel);
    }
    @Override
    public  boolean isValidAccount(String tel,String code){
        // if (telehpone code 配对)
        System.out.println("123");
        return true;
    }
    @Override
    public Map<String, Object> getVerificaCode(String tel) throws ClientException{
        String randomNum = CommonUtils.createRandomNum(6);
        String jsonContent = "{\"code\":\"" + randomNum + "\"}";
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("phoneNumber", tel);
        System.out.println("tel:"+tel);
        paramMap.put("msgSign", "校车监控");
        paramMap.put("templateCode", "SMS_135005010");
        paramMap.put("jsonContent", jsonContent);
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
        if(!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
            if(sendSmsResponse.getCode() == null) {
                //这里可以抛出自定义异常
                resultMap.put("status","1");
//                System.out.println("进入1");
            }
            if(!sendSmsResponse.getCode().equals("OK")) {
                System.out.println(sendSmsResponse.getCode());
                //这里可以抛出自定义异常
                resultMap.put("status","2");
//                System.out.println("进入2");
            }
        }
        resultMap.put("status","0");
        resultMap.put("msgNum",randomNum);
        return resultMap;
    }

    @Override
    // 根据 手机号 获取 userID
    public int getUserIDByTel(String tel) {
        return loginMapper.getUserIDByTel(tel);
    }


    public int setPassWord(String tel,String newPassWord){
        return loginMapper.setPassWord(tel,newPassWord);
    }

}

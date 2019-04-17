package com.example.answer.service;

import com.example.answer.bean.Admin;

import java.util.List;

public interface AdminService {
    // 获取 admin 列表
    public List<Admin> getAdminList();

    // 判断账号密码是否正确
    public boolean isValidAdmin(String account,String password);

    // 根据账号获取adminID
    public long getAdminIdByAccount(String account);

    // 注册创建admin账号
    public int createAdmin(String account,String password,String telephone);

    // 判断账号是否存在
    public boolean isAccountExist(String account);

    // 判断手机号是否存在
    public boolean isPhoneExist(String phone);

}

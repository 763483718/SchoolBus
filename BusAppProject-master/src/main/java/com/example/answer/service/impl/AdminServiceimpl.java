package com.example.answer.service.impl;

import com.example.answer.bean.Admin;
import com.example.answer.dto.PaperDTO;
import com.example.answer.mapper.AdminMapper;
import com.example.answer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceimpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdminList(){
        return adminMapper.getAdminList();
    }

    @Override
    public boolean isValidAdmin(String account,String password){
        List<Admin> admin = adminMapper.isValidAdmin(account,password);
        if(admin == null || admin.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public long getAdminIdByAccount(String account){
        return adminMapper.getAdminIdByAccount(account);
    }

    @Override
    public int createAdmin(String account,String password,String telephone){
        return adminMapper.createAdmin(account,password,telephone);
    }

    @Override
    public boolean isAccountExist(String account){
        List<Admin> admin = adminMapper.isAccountExist(account);
        if(admin == null || admin.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isPhoneExist(String phone){
        List<Admin> admin = adminMapper.isPhoneExist(phone);
        if(admin == null || admin.size() == 0){
            return false;
        }
        return true;
    }


}

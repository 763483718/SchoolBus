package com.example.answer.mapper;

import com.example.answer.bean.Driver;
import com.example.answer.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginMapper {
    @Select("SELECT * FROM driver where driverTelephone = #{telephone}")
    public List<Driver> isValidDriver(@Param("telephone") String tel);

    @Select("SELECT * FROM driver where dirverAccount = #{account}")
    public Driver isValidDriverByAccount(@Param("account") String account);

    @Select("SELECT * FROM user where userAccount = #{account}")
    public User isVaildUserByAccount(@Param("account") String account);

    @Select("SELECT * FROM user where userTelephone = #{telephone}")
    public List<User> isVaildUser(@Param("telephone") String tel);

    @Insert("INSERT INTO user (userTelephone,userAccount,userPassWord) VALUES(#{telephone},#{telephone},123456)")
    public int createUser(@Param("telephone") String tel);

    // 根据 手机号 获取 userID
    @Select("SELECT userID FROM user where userTelephone = #{telephone}")
    public int getUserIDByTel(@Param("telephone") String tel);

    @Update("UPDATE user set userPassword=#{newPassWord} where userAccount=#{telephone}")
    public int setPassWord(@Param("telephone") String tel, @Param("newPassWord") String newPassWord);

}

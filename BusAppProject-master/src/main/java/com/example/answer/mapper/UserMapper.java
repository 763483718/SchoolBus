package com.example.answer.mapper;

import com.example.answer.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 设置用户名
    @Update("UPDATE user SET userName = #{userName} WHERE userTelephone = #{telephone}")
    public int setUserName(@Param("userName") String userName, @Param("telephone") String tel);

    // 设置用户性别
    @Update("UPDATE user SET userSex = #{userSex} WHERE userTelephone = #{telephone}")
    public int setUserSex(@Param("userSex") int userSex, @Param("telephone") String tel);

    // 设置用户生日
    @Update("UPDATE user SET userBirthday = #{userBirthday} WHERE userTelephone = #{telephone}")
    public int setUserBirthday(@Param("userBirthday") String userBirthday, @Param("telephone") String tel);

    // 获取用户信息
    @Select("SELECT userTelephone,userName,userSex,userBirthday from user WHERE userTelephone = #{telephone}")
    public UserInfoDTO getUserInfo(@Param("telephone") String tel);
}

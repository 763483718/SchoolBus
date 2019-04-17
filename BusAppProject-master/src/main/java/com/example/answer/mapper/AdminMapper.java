package com.example.answer.mapper;

//import com.sun.xml.internal.txw2.annotation.XmlNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.example.answer.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM tb_admin")
    public List<Admin> getAdminList();

    @Select("SELECT * FROM tb_admin where adminAccount = #{username} and adminPassword = #{password}")
    public List<Admin> isValidAdmin(@Param("username") String user, @Param("password") String pwd);

    @Select("SELECT adminID FROM tb_admin where adminAccount = #{account}")
    public long getAdminIdByAccount(@Param("account") String account);

    @Insert("INSERT INTO tb_admin (adminAccount,adminPassword,adminTelephone) VALUES(#{account},#{password},#{telephone})")
    public int createAdmin(@Param("account") String account,@Param("password") String password,@Param("telephone") String telephone);

    @Select("select * from tb_admin where adminAccount = #{account}")
    public List<Admin> isAccountExist(@Param("account") String account);

    @Select("select * from tb_admin where adminTelephone = #{phone}")
    public List<Admin> isPhoneExist(@Param("phone") String phone);

}

package com.example.answer.dto;

import lombok.ToString;


/**
 * @author Chenguanzhou
 * @ClassName com.example.answer.dto.UserInfoDTO
 * @Description
 */


@ToString
public class UserInfoDTO extends UserDTO  {
    UserInfoDTO(String userTelephone,String userName,int userSex,String userBirthday)
    {
        super(userTelephone,userName,userSex,userBirthday);
    }

}

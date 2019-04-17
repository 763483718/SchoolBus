package com.example.answer.mapper;

import com.example.answer.dto.PaperDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper {
    @Select("SELECT libraryID,libraryTitle,libraryDesc FROM tb_library where adminID = (SELECT adminID FROM tb_admin where adminAccount = #{username})")
    public List<PaperDTO> getPaperList(@Param("username") String user);

    @Insert("INSERT INTO tb_library (libraryTitle,libraryDesc,adminID) VALUES(#{title},#{desc},#{adminID})")
    public int createPaper(@Param("title") String title,@Param("desc") String desc,@Param("adminID") long adminID);

    @Delete("DELETE FROM tb_library WHERE libraryID = #{libraryID}")
    public int deletePaper(@Param("libraryID") long libraryID);

}

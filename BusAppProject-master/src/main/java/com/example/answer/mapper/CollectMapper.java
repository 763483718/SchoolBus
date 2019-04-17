package com.example.answer.mapper;

import com.example.answer.bean.Collect;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {
    //根据telehphone 搜索收藏线路信息
    @Select("SELECT routeID,direction FROM collect where userID = (select userID FROM user where userTelephone = #{telephone})")
    public List<Collect> getCollectList(@Param("telephone") String tel);

    // 新增线路收藏
    @Insert("INSERT INTO collect (userID,routeID,direction) VALUES(#{userID},#{routeID},#{direction})")
    public int createCollect(@Param("userID") int userID,@Param("routeID") int routeID,@Param("direction") int direction);

    // 取消线路收藏
    @Delete("DELETE FROM collect WHERE userID = #{userID} AND routeID = #{routeID} AND direction = #{direction}")
    public int deleteCollect(@Param("userID") int userID,@Param("routeID") int routeID,@Param("direction") int direction);
}

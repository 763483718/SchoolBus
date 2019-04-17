package com.example.answer.mapper;


import com.example.answer.dto.DriverDTO;
import com.example.answer.dto.DriverInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DriverMapper {
    // 获取司机信息
    @Select("SELECT driver.`dirverID`,driver.`busID`,driver.`driverTelephone`,driver.`licence`,driver.`driverName`, bus.`routeID`,bus.`currentStopIndex`,bus.`isDepart` FROM driver,bus where driver.`driverTelephone` = #{telephone} and bus.`driverID`= (SELECT driver.`dirverID` FROM driver where driver.`driverTelephone` = #{telephone})")
    public DriverDTO getDriverInfo(@Param("telephone") String tel);

    // 根据手机号获取校车信息
//    @Select("SELECT isDepart,routeID,currentStopIndex FROM bus where driverID = (SELECT driverID FROM driver where driverTelephone = #{telephone})")


    // 司机打卡
    @Update("UPDATE bus SET isDepart = #{isDepart} , routeID = #{routeID} ,currentStopIndex = #{currentStopIndex} , currentStopTime = #{currentStopTime},direction = #{direction} WHERE busID = #{busID}")
    public int setStopIndex(@Param("busID") int busID, @Param("routeID") int routeID, @Param("isDepart") int isDepart, @Param("currentStopIndex") int currentStopIndex, @Param("currentStopTime") String currentStopTime, @Param("direction") int direction);

    // 司机抵达终点
    @Update("UPDATE bus SET isDepart = #{isDepart} , currentStopIndex = #{currentStopIndex} , currentStopTime = #{currentStopTime} WHERE busID = #{busID}")
    public int setTerminalIndex(@Param("busID") int busID, @Param("isDepart") int isDepart, @Param("currentStopIndex") int currentStopIndex, @Param("currentStopTime") String currentStopTime);

    // 更新司机实时坐标
    @Update("UPDATE bus SET position = #{position} ,currentPositionTime = #{currentPositionTime} WHERE busID = #{busID}")
    public int setBusPosition(@Param("busID") int busID, @Param("position") String position, @Param("currentPositionTime") String cpTime);

    // 设置司机用户名
    @Update("UPDATE driver SET driverName = #{driverName} WHERE driverTelephone = #{telephone}")
    public int setDriverName(@Param("driverName") String driverName, @Param("telephone") String tel);

    // 设置司机用户性别
    @Update("UPDATE driver SET driverSex = #{driverSex} WHERE driverTelephone = #{telephone}")
    public int setDriverSex(@Param("driverSex") int driverSex, @Param("telephone") String tel);

    // 设置司机用户生日
    @Update("UPDATE driver SET driverBirthday = #{driverBirthday} WHERE driverTelephone = #{telephone}")
    public int setDriverBirthday(@Param("driverBirthday") String driverBirthday, @Param("telephone") String tel);

    // 获取用户信息
    @Select("SELECT driverTelephone,driverName,driverSex,driverBirthday from driver WHERE driverTelephone = #{telephone}")
    public DriverInfoDTO getDriverUserInfo(@Param("telephone") String tel);

    @Update("UPDATE bus set isDepart=1,routeID=#{routeID},currentStopTime=#{currentStopTime},direction=#{direction} where busID=#{busID}")
    public int setBusStart(@Param("busID") int busID, @Param("routeID") int routeID, @Param("currentStopTime") String currentStopTime, @Param("direction") int direction);

    @Update("UPDATE driver set busID=#{busID} where dirverAccount=#{driverAccount}")
    public int setBusIDOfDriver(@Param("busID") int busID, @Param("driverAccount") String driverAccount);
}

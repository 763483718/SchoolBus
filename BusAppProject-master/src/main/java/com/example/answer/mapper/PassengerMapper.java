package com.example.answer.mapper;

import com.example.answer.dto.BusDTO;
import com.example.answer.dto.BusWorkingDTO;
import com.example.answer.dto.CodeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PassengerMapper {

    // 获取运行中校车的Code对象
    @Select("SELECT busID,routeID,driverID FROM bus WHERE isDepart = 1")
    public List<CodeDTO> getWorkingCodeList();

    // 获取运行中司机的信息
    @Select("SELECT bus.`busID`,bus.`routeID`,bus.`driverID`,driver.`driverName`,bus.`licence`,route.`routeName`,route.`startStop`,route.`endStop`,bus.`direction`,bus.`lng`,bus.`lat` FROM bus,driver,route WHERE bus.`busID`= #{busID} And route.`routeID`= #{routeID} And driver.`dirverID`=#{driverID}")
    public BusWorkingDTO getWorkingList(@Param("busID") int busID,@Param("routeID") int routeID,@Param("driverID") int driverID);

    // 根据校车ID获取校车信息
    @Select("SELECT lng,lat,currentStopIndex,currentStopTime,currentPositionTime from bus where busID = #{busCode}")
    public BusDTO getBusPositionById(@Param("busCode") int busCode);


}

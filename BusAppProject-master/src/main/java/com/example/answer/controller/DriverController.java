package com.example.answer.controller;

import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.dto.DriverDTO;
import com.example.answer.service.DriverService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api")
@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;


    @ApiOperation(value = "获取司机（个人）信息", notes = "司机查询自己信息,请求中附带Token", response = JsonResult.class)
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getDriverInfo", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getDriverInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
//            DriverDTO driver = driverService.getDriverInfo("17816873920");
//            r.setStatus("0");
//            r.setMsg("获取司机信息成功");
//            r.setResult(driver);

            Object claims = request.getAttribute("claims");
            if (claims == null) {
                r.setStatus("-1002");
                r.setMsg("账户未登录");
            } else {
                JSONObject json = JSONObject.fromObject(claims);
                System.out.println("json:" + json);
                String tel = json.getString("tel");
                System.out.println("tel:" + tel);
                System.out.println("getDriverInfo（）+" + tel);
                DriverDTO driver = driverService.getDriverInfo(tel);
                r.setStatus("0");
                r.setMsg("获取司机信息成功");
                r.setResult(driver);
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "发车接口", notes = "根据校车编号，修改此次发车的司机，线路，方向 并修改司机对应的校车", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busID", value = "校车ID", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "routeID", value = "路线ID", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "driverAccount", value = "司机账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "direction", value = "往屏风/朝晖方向", required = true, dataType = "Integer", paramType = "query")
    }
    )
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "setBusStart", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> d(
            @RequestParam(value = "busID", required = true) int busID,
            @RequestParam(value = "routeID", required = true) int routeID,
            @RequestParam(value = "driverAccount", required = true) String driverAccount,
            @RequestParam(value = "direction", required = true) int direction)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
            if (driverService.setBusStart(busID, routeID, driverAccount, direction) != 2) {
                r.setStatus("-1100");
                r.setMsg("设置错误");
            } else {
                r.setStatus("0");
                r.setMsg("设置站点成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

///////////////////////////////////////////这里！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    @ApiOperation(value = "校车自动定位", notes = "校车自动定位", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "经度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "setPosition", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> setPosition(
            @RequestParam(value = "lat", required = true) String lat,
            @RequestParam(value = "lng", required = true) String lng
    )throws ServletException{
        JsonResult r = new JsonResult();
        try {
            if (driverService.setBusPosition(1, lat, lng) == 0) {
                r.setStatus("-1100");
                r.setMsg("设置错误");
            } else {
                r.setStatus("0");
                r.setMsg("设置站点成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


            @ApiOperation(value = "司机打卡接口", notes = "司机到站打卡", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busCode", value = "校车ID", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "routeCode", value = "线路Id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "currentIndex", value = "当前序号", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "direction", value = "往屏风/朝晖方向", required = true, dataType = "Integer", paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "setStopIndex", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> setStopIndex(
            @RequestParam(value = "busCode", required = true) int busID,
            @RequestParam(value = "routeCode", required = true) int routeID,
            @RequestParam(value = "currentIndex", required = true) int currentIndex,
            @RequestParam(value = "direction", required = true) int direction,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {


            System.out.println(busID + routeID + currentIndex + direction);
            if (driverService.setStopIndex(busID, routeID, currentIndex, direction) == 0) {
                r.setStatus("-1100");
                r.setMsg("设置错误");
            } else {
                r.setStatus("0");
                r.setMsg("设置站点成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "到达终点站", notes = "记录次趟校车结束", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busCode", value = "校车Id", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "setTerminalIndex", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> setTerminalIndex(
            @RequestParam(value = "busCode", required = true) int busID,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
            if (driverService.setTerminalIndex(busID, 0) == 0) {
                r.setStatus("-1100");
                r.setMsg("设置错误");
            } else {
                r.setStatus("0");
                r.setMsg("设置站点成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "设置校车位置", notes = "更改校车当前位置坐标", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busCode", value = "校车Id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "position", value = "校车当前位置坐标", required = true, dataType = "String", paramType = "query")

    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "setBusPosition", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> setBusPosition(
            @RequestParam(value = "busCode", required = true) int busID,
            @RequestParam(value = "position", required = true) String position)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
            if (driverService.setBusPosition(busID, "0","0") == 0) {
                r.setStatus("-1100");
                r.setMsg("设置错误");
            } else {
                r.setStatus("0");
                r.setMsg("设置站点成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}

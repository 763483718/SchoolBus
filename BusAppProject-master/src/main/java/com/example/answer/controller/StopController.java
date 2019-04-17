package com.example.answer.controller;

import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.dto.StopDTO;
import com.example.answer.dto.StopInfoDTO;
import com.example.answer.dto.StopNameDTO;
import com.example.answer.service.StopService;
import com.example.answer.util.CommonUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/api")
@RestController
public class StopController {
    @Autowired
    private StopService stopService;

    @SuppressWarnings("unused")
    private static class StopListResponse {
        public int nearStopCode;  // 最近点stopID
        public List<StopDTO> stopList;  // 所有站点列表

        public StopListResponse() {
        }

        public int getNearStopCode() {
            return nearStopCode;
        }

        public void setNearStopCode(int nearStopCode) {
            this.nearStopCode = nearStopCode;
        }

        public List<StopDTO> getStopList() {
            return stopList;
        }

        public void setStopList(List<StopDTO> stopList) {
            this.stopList = stopList;
        }
    }

    @ApiOperation(value = "获得最近站点ID，并获取所有站点位置信息", notes = "传入当前坐标", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "y属于[x,y]", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lng", value = "x属于[x,y]", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "getStopPosition", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopPosition(
            @RequestParam(value = "lat", required = true) String lat,
            @RequestParam(value = "lng", required = true) String lng,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            System.out.println("lat:" + lat);
            System.out.println("lng:" + lng);
            StopListResponse slr = new StopListResponse();
            List<StopDTO> stopList = stopService.getStopPosition();
            StopDTO stopDTO = CommonUtils.getNearPosition(Double.parseDouble(lat), Double.parseDouble(lng), stopList);
            System.out.println("stopDTO:" + stopDTO);
            slr.setNearStopCode(stopDTO.getStopID());
            slr.setStopList(stopList);
            r.setResult(slr);
            r.setStatus("0");
            r.setMsg("获取站点信息成功");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "根据站点ID获取站点名字", response = JsonResult.class)
    @ApiImplicitParam(name = "stopID", value = "站点ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "getStopNameByID", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopNameByID(
            @RequestParam(value = "stopID", required = true) String stopID
    ) throws ServletException {
        JsonResult r = new JsonResult();

        try {
            System.out.println(stopID);
            StopInfoDTO name = stopService.getStopNameByID(Integer.parseInt(stopID));
            r.setResult(name);
            r.setMsg("获取成功");
            r.setStatus("0");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "根据站点名字获取站点ID", response = JsonResult.class)
    @ApiImplicitParam(name = "stopName", value = "站点名字", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "getStopIDByName", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopIDByName(
            @RequestParam(value = "stopName", required = true) String stopName
    ) throws ServletException {
        JsonResult r = new JsonResult();

        try {
            System.out.println(stopName);
            StopDTO id = stopService.getStopIDByName(stopName);
            r.setResult(id);
            r.setMsg("获取成功");
            r.setStatus("0");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "获取距离某站点的距离信息,几号线经过此站,此站坐标", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "y属于[x,y]", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lng", value = "x属于[x,y]", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "stopCode", value = "想要查询的站点ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "getStopInfo", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopInfo(
            @RequestParam(value = "lat", required = true) String lat,
            @RequestParam(value = "lng", required = true) String lng,
            @RequestParam(value = "stopCode", required = true) int stopCode,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            System.out.println("lat1111:" + lat);
            System.out.println("lng1111:" + lng);
            StopInfoDTO sid = stopService.getStopInfo(stopCode);
            if (sid == null) {
                r.setStatus("-1001");
                r.setMsg("错误的stopCode");
            } else {
                double distance = CommonUtils.getDistance(Double.parseDouble(lat), Double.parseDouble(lng), Double.parseDouble(sid.getLat()), Double.parseDouble(sid.getLng()));
                sid.setDistance(Integer.toString((int) distance));
                r.setResult(sid);
                r.setStatus("0");
                r.setMsg("获取信息成功");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "获取最近的几个站点及到最近几个站点的距离", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "y属于[x,y]", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "lng", value = "x属于[x,y]", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "getNearStopList", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getNearStopList(
            @RequestParam(value = "lat", required = true) String lat,
            @RequestParam(value = "lng", required = true) String lng,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {

            System.out.println(lat + lng);
            List<StopNameDTO> stopNameList = stopService.getStopName();
            List<StopNameDTO> snl = CommonUtils.getRangeList(Double.parseDouble(lat), Double.parseDouble(lng), stopNameList, 2000);
            r.setResult(snl);
            r.setMsg("获取成功");
            r.setStatus("0");
//            StopDTO stopDTO = CommonUtils.getNearPosition(Double.parseDouble(lat),Double.parseDouble(lng),stopList);
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "估计到某站到时间，这个功能先不要用了。", response = JsonResult.class)

    @RequestMapping(value = "getStopDetail", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopDetail(
            @RequestParam(value = "stopName", required = true) String stopName,
            @RequestParam(value = "busCode", required = true) int busCode,
            @RequestParam(value = "stopIndex", required = true) int stopIndex,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
            r.setResult(stopService.getStopDetail(stopName, busCode, stopIndex));
            r.setStatus("0");
            r.setMsg("获取站点详情成功");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


}

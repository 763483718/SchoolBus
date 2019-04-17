package com.example.answer.controller;

import com.example.answer.bean.Collect;
import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.bean.Route;
import com.example.answer.dto.RouteInfoDTO;
import com.example.answer.dto.RouteStopDTO;
import com.example.answer.service.LoginService;
import com.example.answer.service.RouteService;
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
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private LoginService loginService;

    public static JsonResult test;


    // 获取Route信息
    @ApiOperation(value = "查看所有路线", notes = "返回所有路线,包括所有站点信息", response = JsonResult.class)
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getRouteList", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getRouteList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();

//        JsonResult r = new JsonResult();
        try {
            List<Route> routes = routeService.getRouteList();
            String position;
            r.setStatus("0");
//            System.out.println(routes);
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getStopList() == null) {
                    System.out.println("the null of index is " + i);
                } else {
                    JSONObject jsonObjectStopList = JSONObject.fromObject(routes.get(i).getStopList());
                    String temp[] = jsonObjectStopList.getString("forward").split("\",\"");
//                    System.out.println(jsonObjectStopList.getString("forward"));
                    temp[0] = temp[0].split("\"")[1];
                    temp[temp.length - 1] = temp[temp.length - 1].split("\"")[0];
//                    position="[";
                    position="";
                    for (int k = 0; k < temp.length; k++) {
//                        System.out.print(temp[k]);
//                        System.out.println();
                        position+=routeService.getPosition(temp[k]);
                        if(k!=temp.length-1){
                            position+=",";
                        }
                    }
//                    position+="]";
//                    System.out.println(position);
                    routes.get(i).setPostion(position);
                    routes.get(i).setStopList(jsonObjectStopList.getString("forward").replaceAll("\"", "\'"));
                }
                JSONObject jsonObjectStartStop = JSONObject.fromObject(routes.get(i).getStartStop());
                routes.get(i).setStartStop(jsonObjectStartStop.getString("forward").replaceAll("\"", "\'"));
                JSONObject jsonObjectEndStop = JSONObject.fromObject(routes.get(i).getEndStop());
                routes.get(i).setEndStop(jsonObjectEndStop.getString("forward").replaceAll("\"", "\'"));
//                System.out.println(routes);
            }
            r.setResult(routes);
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
//        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "查看经过特定站点的路线", notes = "当不输入站点时，返回所有路线", response = JsonResult.class)
    @ApiImplicitParam(name = "stopCode", value = "站点编号", required = false, dataType = "String", paramType = "path")
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getRouteInfoList", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getRouteInfoList(@RequestParam(value = "stopCode", required = false) String stopID,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) throws ServletException {
        JsonResult r = new JsonResult();
        try {

            System.out.println("stopID:" + stopID);
            int stopCode;
            List<RouteInfoDTO> routes = new ArrayList<>();
            Object claims = request.getAttribute("claims");
            if (claims == null) {
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            } else {
                JSONObject json = JSONObject.fromObject(claims);
                String tel = json.getString("tel");
                if (stopID == null) {
                    stopCode = -1;
                    System.out.println("传的参数为空");
                    routes = routeService.getRouteInfoList(stopCode);
                } else {
                    stopCode = Integer.parseInt(stopID);
                    routes = routeService.getRouteInfoList(stopCode);
                }

                System.out.println(tel);
                List<Collect> collects = routeService.getCollectList(tel);

                for (int i = 0; i < routes.size(); i++) {
                    for (int j = 0; j < collects.size(); j++) {
                        if (collects.get(j).getRouteID() == routes.get(i).getRouteID()) {
                            routes.get(i).setCollect(true);
                            continue;
                        }
                        if (j == collects.size()) {
                            routes.get(i).setCollect(false);
                        }
                    }
                    JSONObject jsonObjectStartStop = JSONObject.fromObject(routes.get(i).getStartStop());
                    routes.get(i).setStartStop(jsonObjectStartStop.getString("forward").replaceAll("\"", "\'"));
                    JSONObject jsonObjectEndStop = JSONObject.fromObject(routes.get(i).getEndStop());
                    routes.get(i).setEndStop(jsonObjectEndStop.getString("forward").replaceAll("\"", "\'"));
                }
                r.setStatus("0");
                r.setResult(routes);
            }

        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
//        return ResponseEntity.ok(r);
    }

    // 获取Route站点列表
    @ApiOperation(value = "获取指定线路经过站点", notes = "分为往朝晖方向、屏风方向", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeCode", value = "线路代码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "routeDirection", value = "0往屏风，1往朝晖", required = true, dataType = "int", paramType = "path")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getStopList", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getStopList(@RequestParam(value = "routeCode", required = true) int routeCode,
                                                  @RequestParam(value = "routeDirection", required = true) int routeDirection,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            RouteStopDTO stopList = routeService.getRouteStopList(routeCode, routeDirection);
            r.setStatus("0");
            r.setResult(stopList);
            r.setMsg("获取列表成功");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    // 获取收藏线路列表
    @ApiOperation(value = "获取收藏线路信息", notes = "根据当前登陆账号获取其收藏路线，返回：\n" +
            "线路ID\n" +
            "线路名称(routeName)\n" +
            "线路名称(byName)\n" +
            "起始站点\n" +
            "发车时间\n" +
            "到站时间\n" +
            "票价\n" +
            "方向\n", response = JsonResult.class
    )
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getCollectList", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getCollectList(HttpServletRequest request,
                                                     HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            Object claims = request.getAttribute("claims");
            if (claims == null) {
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            } else {
                JSONObject json = JSONObject.fromObject(claims);
                String tel = json.getString("tel");
                List<RouteInfoDTO> routes = routeService.getCollectRouteList(tel);
                for (int i = 0; i < routes.size(); i++) {
                    JSONObject jsonObjectStartStop = JSONObject.fromObject(routes.get(i).getStartStop());
                    routes.get(i).setStartStop(jsonObjectStartStop.getString("forward").replaceAll("\"", "\'"));
                    JSONObject jsonObjectEndStop = JSONObject.fromObject(routes.get(i).getEndStop());
                    routes.get(i).setEndStop(jsonObjectEndStop.getString("forward").replaceAll("\"", "\'"));
                }
                r.setStatus("0");
                r.setResult(routes);
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    // 新增线路收藏
    @ApiOperation(value = "新增收藏线路", notes = "传入线路号及方向，收藏至当前登陆用户", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeID", value = "线路ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "direction", value = "方向", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "createCollect", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> createCollect(@RequestParam(value = "routeID", required = true) int routeID,
                                                    @RequestParam(value = "direction", required = true) int direction,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            Object claims = request.getAttribute("claims");
            if (claims == null) {
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            } else {
                JSONObject json = JSONObject.fromObject(claims);
                String tel = json.getString("tel");
                int userID = loginService.getUserIDByTel(tel);
                if (routeService.createCollect(userID, routeID, direction) > 0) {
                    r.setStatus("0");
                    r.setMsg("新增收藏成功");
                } else {
                    r.setStatus("-1001");
                    r.setMsg("新增收藏失败");
                }
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    // 取消线路收藏
    @ApiOperation(value = "删除收藏路线", notes = "传入线路号及方向，取消当前登陆账号的收藏", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeID", value = "线路ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "direction", value = "方向", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "deleteCollect", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> deleteCollect(@RequestParam(value = "routeID", required = false) int routeID,
                                                    @RequestParam(value = "direction", required = false) int direction,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            System.out.println("进入了deleteCollect");
            Object claims = request.getAttribute("claims");
            if (claims == null) {
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            } else {
                JSONObject json = JSONObject.fromObject(claims);
                String tel = json.getString("tel");
                int userID = loginService.getUserIDByTel(tel);
                if (routeService.deleteCollect(userID, routeID, direction) > 0) {
                    r.setStatus("0");
                    r.setMsg("取消收藏成功");
                } else {
                    r.setStatus("-1001");
                    r.setMsg("取消收藏失败");
                }
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    // 获取校车详情
    @ApiOperation(value = "获取校车详细信息", notes = "返回：\n" +
            "startStop\n" +
            "endStop\n" +
            "startTime\n" +
            "endTime\n" +
            "price\n" +
            "stopList\n" +
            "currentStopIndex\n" +
            "stopHoverIndex", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeCode", value = "线路ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "busCode", value = "校车ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "direction", value = "方向", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "nearStopName", value = "附近站点名字", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getBusDetail", method = RequestMethod.GET)
    public ResponseEntity<JsonStatus> getBusDetail(@RequestParam(value = "routeCode", required = true) int routeCode,
                                                   @RequestParam(value = "busCode", required = true) int busCode,
                                                   @RequestParam(value = "direction", required = true) int direction,
                                                   @RequestParam(value = "nearStopName", required = true) String nearStopName,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();
        try {
            r.setResult(routeService.getBusDetail(routeCode, busCode, nearStopName, direction));
            r.setMsg("获取成功");
            r.setStatus("0");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}

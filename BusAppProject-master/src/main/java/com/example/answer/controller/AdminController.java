package com.example.answer.controller;

import com.aliyun.mns.common.ClientException;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.example.answer.bean.Admin;
import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.service.AdminService;
import com.example.answer.util.AliyunMessageUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.ws.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/api")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    // RequestMapping  不写 method 将会支持所有的method方式

    @RequestMapping(value = "getAdminList",method = RequestMethod.GET)
    public ResponseEntity<JsonResult>getAdminList(){
        JsonResult r = new JsonResult();
        try{
            List<Admin> admins = adminService.getAdminList();
            r.setStatus("0");
            r.setResult(admins);
        }catch (Exception e){
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @RequestMapping(value = "login11", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> login(@RequestParam(value = "account",required = false) String username, @RequestParam(value = "password",required = false) String password,
                                            HttpServletRequest request,HttpServletResponse response)
            throws ServletException {


        Object claims = request.getAttribute("claims");
        if(claims == null){
            System.out.println("null~~~");
        }else{
            JSONObject json  = JSONObject.fromObject(claims);
            String roles = json.getString("roles");
            System.out.println("roles:"+roles);
        }

        JsonResult r = new JsonResult();
        try{
            if(adminService.isValidAdmin(username,password)){
                LoginResponse lr = new LoginResponse(Jwts.builder().setSubject("admin")
                .claim("roles", username).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
                r.setStatus("0");
                r.setResult(lr);
//                response.setHeader("Access-Token",lr.toString());
                r.setMsg("登陆成功");
                System.out.println("login success");
            }else{
                // -1001 账号密码错误
                r.setStatus("-1001");
                r.setMsg("账号密码错误");
                System.out.println("login error");
            }
        }catch (Exception e){
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            r.setMsg("服务器异常");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

    @RequestMapping(value = "adminRegister",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>resgister(@RequestParam(value = "account",required = false) String account, @RequestParam(value = "password",required = false) String password,
                                               @RequestParam(value = "telephone",required = false) String telephone,HttpServletRequest request,HttpServletResponse response)
            throws ServletException{
        JsonStatus r = new JsonStatus();
        try{
            int flag = adminService.createAdmin(account,password,telephone);
            System.out.println("account:"+account);
            System.out.println("password:"+password);
            System.out.println("telephone"+telephone);
            if(flag<0){
                r.setStatus("-1005");
                r.setMsg("账号重复了吧");
                System.out.println("flag:"+flag);
            }else{
                r.setStatus("0");
                r.setMsg("注册成功");
            }
        }catch (Exception e){

            r.setStatus("-1000");
            r.setMsg("服务器异常");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "isAccountExist",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>isAccountExist(@RequestParam(value = "account",required = false) String account)
        throws ServletException{
        JsonStatus r = new JsonStatus();
        try{
            if(adminService.isAccountExist(account)){
                r.setStatus("-1006");
                r.setMsg("账号已经存在");
            }else{
                r.setStatus("0");
                r.setMsg("该账号可使用");
            }
        }catch (Exception e){
            r.setStatus("-1000");
            r.setMsg("服务器异常");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "isPhoneExist",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>isPhoneExist(@RequestParam(value = "phone",required = false) String phone)
            throws ServletException{
        JsonStatus r = new JsonStatus();
        try{
            if(adminService.isPhoneExist(phone)){
                r.setStatus("-1006");
                r.setMsg("手机号已经存在");
            }else{
                r.setStatus("0");
                r.setMsg("该手机号可用");
            }
        }catch (Exception e){
            r.setStatus("-1000");
            r.setMsg("服务器异常");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "getMsgCode",method = RequestMethod.GET)
    public ResponseEntity<JsonStatus>getMsgCode(@RequestParam(value = "phone",required = false) String phone)
            throws ServletException,ClientException {
        JsonStatus r = new JsonStatus();
        try{
            String phoneNumber = "17816873920";
            String randomNum = createRandomNum(6);
            String jsonContent = "{\"code\":\"" + randomNum + "\"}";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("phoneNumber", phoneNumber);
            paramMap.put("msgSign", "浙工大科创中心");
            paramMap.put("templateCode", "xxxxxxxx");
            paramMap.put("jsonContent", jsonContent);
            System.out.println("paramMap:"+paramMap);
            SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
            System.out.println("调用成功");
            System.out.println("SendSmsResponse:"+sendSmsResponse);
            if(!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
                if(sendSmsResponse.getCode() == null) {
                    System.out.println(sendSmsResponse.getCode());
                    //这里可以抛出自定义异常
                }
                if(!sendSmsResponse.getCode().equals("OK")) {
                    //这里可以抛出自定义异常
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);






    }
    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }


}

package com.example.answer.controller;

import com.example.answer.bean.Driver;
import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.bean.User;
import com.example.answer.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@RequestMapping("/api")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @SuppressWarnings("unused")
    private static class LoginCodeResponse {
        public String msgNum;  // 验证码
        public String tamp;  // 过期时间

        public LoginCodeResponse(final String msgNum, final String tamp) {
            this.msgNum = msgNum;
            this.tamp = tamp;
        }
    }

    @SuppressWarnings("unused")
    private static class LoginTokenResponse {
        public String token;  // 用户token
        public String role;

        public LoginTokenResponse(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }


    @ApiOperation(value = "获取验证码", notes = "向用户手机号发送验证码", response = JsonResult.class)
    @ApiImplicitParam(name = "telephone", value = "用户手机号", required = true, dataType = "String", paramType = "query")
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "getVerificaCode", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> getVerificaCode(@RequestParam(value = "telephone", required = true) String tel, HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        JsonResult r = new JsonResult();
        try {
            System.out.println("this is tel:" + tel);
            Map<String, Object> codeMap = loginService.getVerificaCode(tel);//获取短信验证码
            //test:
            System.out.println("codeMap:" + codeMap);//测试codeMap内容
            //test
            if (codeMap.get("status").equals("0")) {
                r.setStatus("0");
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                Calendar c = Calendar.getInstance();//使用默认时区和语言环境这种方法获得一个日历
                c.add(Calendar.MINUTE, 500);/** 修改*/
                String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
                LoginCodeResponse lr = new LoginCodeResponse(Jwts.builder().setSubject("msgNum")
                        .claim("msgNum", codeMap.get("msgNum"))
                        .setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey")
                        .compact(), currentTime);

                r.setResult(lr);
//                response.setHeader("Access-Token",lr.toString());
                r.setMsg("获取短信验证码成功");
            }

        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
//        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "账号密码登陆", notes = "使用固定账号密码登陆", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "loginInUsername", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> loginInUsername(@RequestParam(value = "userName", required = true) String userName,
                                                      @RequestParam(value = "password", required = true) String password
    ) throws ServletException {
        JsonResult r = new JsonResult();
        try {
            Driver driver = loginService.isValidDriverByAccount(userName);
            User user = loginService.isVaildUserByAccount(userName);

            if (user == null && driver == null) {


                loginService.createUser(userName);


                r.setResult("创建新账号成功");
                r.setStatus("201");
                r.setMsg("创建新账号成功");
            } else {
                String tel = user == null ? driver.getDriverTelephone() : user.getUserTelephone();
                String passwordT = user == null ? driver.getDirverPassword() : user.getUserPassword();
                System.out.println("passwordT:" + passwordT);
                if (passwordT.equals(password)) {
                    r.setStatus("0");
                    LoginTokenResponse lr = new LoginTokenResponse(Jwts.builder().setSubject("token")
                            .claim("tel", tel)
                            .setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256, "secretkey")
                            .compact());
                    if (driver != null) {
                        lr.setRole("driver");
                        System.out.println(driver.getBusID());
                    } else if (user != null) {
                        lr.setRole("user");
                    }
                    r.setResult(lr);
                    r.setMsg("登陆成功");
                    r.setStatus("200");
                } else {
                    r.setResult("密码不正确");
                    r.setStatus("400");
                    r.setMsg("登陆失败,密码不正确");
                }
            }

        } catch (Exception e) {
            System.out.println("There is a error");
            r.setMsg(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "修改密码", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "newPassWord", value = "新密码", required = true, paramType = "query")
    })
    @ApiResponse(code = 200, message = "修改成功", response = JsonResult.class)
    @RequestMapping(value = "setPassWord", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> setPassWord(@RequestParam(value = "telephone", required = true) String telephone,
                                                  @RequestParam(value = "newPassWord", required = true) String newPassWord
    ) throws SecurityException {
        JsonResult r = new JsonResult();

        try {
            int res = loginService.setPassWord(telephone, newPassWord);
            r.setResult("修改成功");
            r.setStatus("200");
            r.setMsg("修改成功");
        } catch (Exception e) {
            System.out.println("There is a error");
            r.setMsg(e.getMessage());
        }


        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "验证码登陆", notes = "使用验证码登陆", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "手机收到的验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tamp", value = "获取验证码时前端收到的时间戳", required = true, paramType = "query"),
            @ApiImplicitParam(name = "msgNum", value = "获取验证码时前端收到的密文", required = true, paramType = "query")
    })
    @ApiResponse(code = 200, message = "请求成功", response = JsonResult.class)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<JsonStatus> login(@RequestParam(value = "telephone", required = true) String telephone,
                                            @RequestParam(value = "code", required = true) String code,
                                            @RequestParam(value = "tamp", required = true) String tamp,
                                            @RequestParam(value = "msgNum", required = true) String msgNum
    ) throws ServletException {
        JsonResult r = new JsonResult();
        try {
            System.out.println("telephone:" + telephone);
            System.out.println("code:" + code);
            System.out.println("tamp:" + tamp);
            System.out.println("msgNum:" + msgNum);
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                    .parseClaimsJws(msgNum)
                    .getBody();
            JSONObject json = JSONObject.fromObject(claims);
            String realCode = json.getString("msgNum");
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
            System.out.println("realCode:" + realCode);
            System.out.println("code:" + code);
            System.out.println("currentTime:" + currentTime);
            System.out.println("tamp:" + tamp);
            System.out.println(tamp.compareTo(currentTime));
            if (tamp.compareTo(currentTime) > 0) {
                if (code.equals(realCode)) {
                    r.setStatus("0");
                    LoginTokenResponse lr = new LoginTokenResponse(Jwts.builder().setSubject("token")
                            .claim("tel", telephone)
                            .setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256, "secretkey")
                            .compact());
                    if (loginService.isValidDriver(telephone)) {
                        lr.setRole("driver");
                    } else {
                        lr.setRole("user");
                        if (loginService.isVaildUser(telephone)) {
                            System.out.println("该账号已经注册过了");
                        } else {
                            loginService.createUser(telephone);
                        }
                    }
                    r.setResult(lr);
                    r.setMsg("登陆成功");
                } else {
                    System.out.println("code:" + code);
                    System.out.println("realCode:" + realCode);
                    r.setStatus("-1001");
                    r.setMsg("验证失败");
                }
            } else {
                System.out.println("验证码超时了");
                r.setStatus("-1003");
                r.setMsg("验证码超时");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


}

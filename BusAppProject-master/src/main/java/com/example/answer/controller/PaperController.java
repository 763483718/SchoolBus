package com.example.answer.controller;

import com.example.answer.bean.JsonResult;
import com.example.answer.bean.JsonStatus;
import com.example.answer.dto.PaperDTO;
import com.example.answer.service.AdminService;
import com.example.answer.service.PaperService;
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
import java.util.List;

@RequestMapping("/api")
@RestController
public class PaperController
{
    @Autowired
    private PaperService paperService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "getPaperList",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>getPaperList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        JsonResult r = new JsonResult();

        try{
            Object claims = request.getAttribute("claims");
            if(claims == null){
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            }else{
                JSONObject json  = JSONObject.fromObject(claims);
                String roles = json.getString("roles");
                List<PaperDTO> papers = paperService.getPaperList(roles);
                r.setStatus("0");
                r.setResult(papers);
            }

        }catch (Exception e){
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("-1000");
            e.printStackTrace();

        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value = "createPaper",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>createPaper(@RequestParam(value = "title") String title,@RequestParam(value = "desc") String desc, HttpServletRequest request, HttpServletResponse response)
        throws ServletException{
        JsonStatus r = new JsonStatus();
        try{
            Object claims = request.getAttribute("claims");
            if(claims == null){
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            }else{
                JSONObject json  = JSONObject.fromObject(claims);
                String roles = json.getString("roles");
                int flagId = paperService.createPaper(title,desc,adminService.getAdminIdByAccount(roles));
                if(flagId < 0){
                    r.setStatus("-1005");
                    r.setMsg("插入数据失败");
                }else{
                    r.setStatus("0");
                    r.setMsg("成功插入数据");
                }
            }

        }catch (Exception e){
            r.setStatus("-1000");
            e.printStackTrace();

        }
        return ResponseEntity.ok(r);
    }
    @RequestMapping(value = "deletePaper",method = RequestMethod.POST)
    public ResponseEntity<JsonStatus>deletePaper(@RequestParam(value = "id") long libraryID, HttpServletRequest request, HttpServletResponse response)
            throws ServletException{
        JsonStatus r = new JsonStatus();
        try{
            Object claims = request.getAttribute("claims");
            if(claims == null){
                r.setStatus("-1002");
                r.setMsg("账号未登陆");
            }else{
                JSONObject json  = JSONObject.fromObject(claims);
                String roles = json.getString("roles");
                int flagId = paperService.deletePaper(libraryID);
                if(flagId < 0){
                    r.setStatus("-1005");
                    r.setMsg("删除数据失败");
                }else{
                    r.setStatus("0");
                    r.setMsg("删除数据成功");
                }
            }

        }catch (Exception e){
            r.setStatus("-1000");
            e.printStackTrace();

        }
        return ResponseEntity.ok(r);
    }

}

package com.example.answer.bean;

// 仅返回 状态码 和 提示信息
public class JsonStatus {
    protected String status = null;

    protected String msg = "";

    public JsonStatus(){
    }
    public JsonStatus(String status,String msg){
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

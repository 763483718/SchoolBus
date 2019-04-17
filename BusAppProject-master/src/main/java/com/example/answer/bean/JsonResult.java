package com.example.answer.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// 返回 状态码 和 提示信息 和 数据结果
@ApiModel(value="JsonResult",description = "所有返回都是此类")
public class JsonResult extends JsonStatus{

    @ApiModelProperty(name="result",value = "返回的实际信息,内部是一个json数组",example = "[{name,age},{stop,routeID}]")
    private Object result;

    public JsonResult(){
        super();
        this.result = "";
    }


    public JsonResult(String status,String msg,Object result) {
        super(status,msg);
        this.result =result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

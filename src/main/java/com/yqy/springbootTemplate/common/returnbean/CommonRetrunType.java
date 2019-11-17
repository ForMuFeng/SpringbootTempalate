package com.yqy.springbootTemplate.common.returnbean;

import lombok.Data;

/**
 * @program: seckill-springboot
 * @description:规范化前端返回数据
 * @author: Mr.Yqy
 * @create: 2019-05-23 11:51
 **/
@Data
public class CommonRetrunType {

    private String status;

    private Object data;

    private String resultMsg;



    /**
     * 成功式返回消息
     * @param resultMsg
     * @return
     */
    public static CommonRetrunType ok(String resultMsg) {

        return CommonRetrunType.create("success",null,resultMsg);
    }


    /**
     * 成功时返回数据
     * @param data
     * @return
     */
    public static CommonRetrunType ok(Object data) {
        return CommonRetrunType.create("success",data,null);
    }


    /**
     * 失败时返回消息
     * @param resultMsg
     * @return
     */
    public static CommonRetrunType fail(String resultMsg) {
        return CommonRetrunType.create("fail",null,resultMsg);

    }

    /**
     * 失败时返回数据
     * @param data
     * @return
     */
    public static CommonRetrunType fail(Object data) {
        return CommonRetrunType.create("fail",data,null);

    }


    public static CommonRetrunType create(String status, Object data, String resultMsg) {
        CommonRetrunType ct = new CommonRetrunType();
        ct.setStatus(status);
        ct.setData(data);
        ct.setResultMsg(resultMsg);
        return ct;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
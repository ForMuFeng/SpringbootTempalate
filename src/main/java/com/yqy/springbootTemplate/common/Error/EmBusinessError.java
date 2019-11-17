package com.yqy.springbootTemplate.common.Error;

public enum EmBusinessError implements CommonError{
    //    通用错误类型00001
    UNKNOW_ERROR(10001,"系统繁忙"),
    //    20000开头为用户信息相关错误等
    USER_NOT_EXIST(20001,"用户不存在");

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String msg) {
        this.errMsg=msg;
        return this;
    }
}

package com.yqy.springbootTemplate.common.Error;

/**
 * @program: seckill-springboot
 * @description: 异常处理
 * @author: Mr.Yqy
 * @create: 2019-05-23 12:45
 **/

/**
 * 包装器业务异常类实现
 * 通过这种方式，无论是newBusinessException还是new EmBusinessError，都可以有errorCode和errorMessage的组装定义
 * 都有setErrMsg的方法，可以覆盖原本枚举中定义的errorMsg信息
 */
public class BusinessException extends Exception implements CommonError{
    private CommonError commonError;



    //直接接受EmBusinessError的传参用于构造业务异常
    public BusinessException (CommonError commonError){
        super();//调用父类的初始化方法
        this.commonError=commonError;
    }



    //接受自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String msg) {
        this.commonError.setErrMsg(msg);
        return this;
    }
}

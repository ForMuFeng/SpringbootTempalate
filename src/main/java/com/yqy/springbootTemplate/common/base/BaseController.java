package com.yqy.springbootTemplate.common.base;

import cn.hutool.http.HtmlUtil;
import com.yqy.springbootTemplate.common.Error.BusinessException;
import com.yqy.springbootTemplate.common.Error.EmBusinessError;
import com.yqy.springbootTemplate.common.returnbean.CommonRetrunType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: seckill-springboot
 * @description: Controller基类
 * @author: Mr.Yqy
 * @create: 2019-05-23 14:17
 **/
public class BaseController {

    public static final Logger log = LoggerFactory.getLogger(AbstractController.class);
    //定义exceptionhandler解决未被Controller层吸收的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        log(ex, request);
        //便于调试的时候查看原因
        ex.printStackTrace();
        Map<String,Object> message=new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException be=(BusinessException)ex;
            message.put("errCode",be.getErrorCode());
            message.put("errMsg",be.getErrMsg());
        }else if(ex instanceof DuplicateKeyException){
            message.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","索引重复异常");
            return CommonRetrunType.create("fail",message,"添加值重复，请检查");
        }
        else if(ex instanceof NullPointerException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","空指针异常");
        }
        else if(ex instanceof ClassCastException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","类型强制转换异常");
        }
        else if(ex instanceof ArrayIndexOutOfBoundsException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","数组下标越界异常");
        }
        else if(ex instanceof FileNotFoundException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","文件未找到异常");
        }
        else if(ex instanceof SQLException||ex instanceof DataAccessException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","操作数据库异常");
        } else if(ex instanceof IOException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","输入输出异常");
        }else if(ex instanceof IndexOutOfBoundsException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","下标越界异常");
        }else if(ex instanceof NoHandlerFoundException){
            message.put("errCode",EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","请确认接口url是否错误");
        }else if(ex instanceof RuntimeException){
            return CommonRetrunType.create("fail",null,ex.getMessage());
        }
        else {
            message.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrorCode());
            message.put("errMsg","非常见异常："+ex.getCause());
        }

        return CommonRetrunType.create("fail",message,"系统繁忙");
    }


    /**
     * InitBinder .
     * String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
     * @param binder binder
     */
    @InitBinder
    public void webInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : HtmlUtil.escape(text.trim()));
            }
        });

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);

    }



    private void log(Exception ex, HttpServletRequest request) {
        log.error("************************异常开始*******************************");

        log.error("异常信息：", ex);
        log.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        log.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            log.error(name + "---" + request.getParameter(name));
        }
        log.error("************************异常结束*******************************");
    }
}

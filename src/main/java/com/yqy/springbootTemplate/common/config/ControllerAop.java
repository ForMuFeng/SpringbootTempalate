package com.yqy.springbootTemplate.common.config;

/**
 * @Auth:yqy
 * @Date 2019/8/30 13:27
 */

import com.yqy.springbootTemplate.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 处理和包装异常.
 *
 * @author heli0
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {


    /**
     * handlerControllerMethod.
     */
    @Around(value = "execution(public com.yqy.springbootTemplate.common.returnbean.CommonRetrunType *(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();

        // 如果需要打印入参，可以从这里取出打印
        Object[] args = pjp.getArgs();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String ip = CommonUtil.getIpAddr(request);
        // 本次操作用时（毫秒）
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.debug("[{}] [{}]use time: {} ms,params: {}", ip, pjp.getSignature().getName(), elapsedTime,
                Arrays.toString(args));
        return result;
    }



}

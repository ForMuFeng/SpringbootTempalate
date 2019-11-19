package com.yqy.springboottemplate.shiroTests;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * @Auth:yqy
 * @Date 2019/11/18 21:34
 */
public class SimpleShiroTest {
    SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
    @Before
    public void addUser(){
        //添加一个用户
        simpleAccountRealm.addAccount("Tom","123","admin","admin1");
    }

    @Test
    public void doTest(){
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();


        UsernamePasswordToken token=new UsernamePasswordToken("Tom","123");


        subject.login(token);
        //        -------------用户进入登录状态------------------

        //用户名错误：UnknownAccountException  密码错误：IncorrectCredentialsException
        System.out.println("登录是否认证:"+subject.isAuthenticated());
        subject.isAuthenticated();


        //用户如果没有对应权限会抛出   UnauthorizedException
        subject.checkRole("admin");//一个角色
        subject.checkRoles("admin","admin1");//多个角色
        //用户登出
        subject.logout();
        System.out.println("登出是否认证:"+subject.isAuthenticated());


    }
}

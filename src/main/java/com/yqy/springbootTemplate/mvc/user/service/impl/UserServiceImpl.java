package com.yqy.springbootTemplate.mvc.user.service.impl;

import com.yqy.springbootTemplate.mvc.user.entity.User;
import com.yqy.springbootTemplate.mvc.user.mapper.UserMapper;
import com.yqy.springbootTemplate.mvc.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqy
 * @since 2019-11-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

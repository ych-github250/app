package com.ych.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ych.modules.system.mapper.UserMapper;
import com.ych.modules.system.model.po.User;
import com.ych.modules.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YCH   `
 * @since 2023-10-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> findAll() {
        Page<User> page = new Page<>(1, 10);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(User::getCreateTime).orderByAsc(User::getUsername);
        Page<User> userPage = userMapper.selectPage(page, null);
        return userPage;
    }
}

package com.ych.modules.system.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ych.modules.system.model.po.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YCH   `
 * @since 2023-10-16
 */
public interface UserService extends IService<User> {

    Page<User> findAll();
}

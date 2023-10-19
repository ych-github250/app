package com.ych.modules.system.controller;

import com.ych.core.web.entity.Response;
import com.ych.modules.system.model.po.User;
import com.ych.modules.system.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YCH   `
 * @since 2023-10-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public Response test() {
        return Response.success(userService.findAll());
    }

    @PostMapping
    public Response add(@RequestBody User user) {
        userService.save(user);
        return Response.success();
    }


}

package com.ych.modules.system.controller;

import com.ych.core.mail.MailSender;
import com.ych.core.mail.ToEmail;
import com.ych.core.redis.service.RedisCache;
import com.ych.core.web.entity.Response;
import com.ych.core.web.entity.ResponseCode;
import com.ych.core.web.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/demo")
@Api(tags = "测试接口")
public class DemoController {
    @Resource
    private RedisCache redisCache;

    @Resource
    private MailSender mailSender;

    @GetMapping("/hello")
    @ApiOperation("测试接口-Hello World")
    public String hello() {
        redisCache.set("hello", "world");
        String hello = redisCache.get("hello");
        System.out.println(hello);
        return "Hello World!";
    }

    @GetMapping("/demo")
    @ApiOperation("测试接口-Response")
    public Response demo(Integer type) {
        if (type == 1) {
            int i = 1 / 0;
        } else if (type == 2) {
            throw new RuntimeException("测试异常");
        } else if (type == 3) {
            return Response.fail();
        } else if (type == 4) {
            return Response.fail("测试失败");
        } else if (type == 5) {
            return Response.fail(ResponseCode.FAIL);
        } else if (type == 6) {
            throw new BusinessException(ResponseCode.FAIL);
        } else if (type == 7) {
            return Response.success("Hello World!");
        }
        return Response.success();
    }

    @GetMapping
    @ApiOperation("测试接口-日志")
    public Response test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            log.info("测试日志：{}", "Hello World! -- " + i);
        }
        long end = System.currentTimeMillis();
        log.info("耗时：{}", end - start);
        return Response.success("Hello World!");
    }

    @GetMapping
    @ApiOperation("测试邮箱")
    public Response testEmail() {
        ToEmail toEmail = new ToEmail();
        mailSender.sendSimpleMail(toEmail);
        return Response.success();
    }

}

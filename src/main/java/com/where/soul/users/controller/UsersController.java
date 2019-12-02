package com.where.soul.users.controller;


import com.where.soul.common.Result;
import com.where.soul.common.exception.BizException;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.where.soul.common.base.BaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/users/users")
@Slf4j
public class UsersController extends BaseController {
    @GetMapping("/{id}")
    public Result getUserInfo(@PathVariable("id") String id) {
        log.info("-----------" + id);
        return Result.success(id);
    }

    @GetMapping("test")
    public Result test() {
        int i = Integer.parseInt("dsakf");
        return Result.success(null);
    }

    @GetMapping("null")
    public Result testNull() {
        String nStr = null;
        nStr.equals("jdskaf");
        return Result.success(null);
    }

    @GetMapping("exception")
    public Result testException() {
        throw  new BizException(-1, "测试异常捕获！");
    }

    @GetMapping("success")
    public Result testSuccess() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("test", "test");
        map.put("array", new ArrayList<>());
        map.put("obj", new HashMap<>(0));
        return Result.success(map);
    }
}

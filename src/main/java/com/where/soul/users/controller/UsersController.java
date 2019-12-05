package com.where.soul.users.controller;


import com.where.soul.common.Constant;
import com.where.soul.common.LoginManager;
import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import com.where.soul.common.util.FormatterUtil;
import com.where.soul.users.dto.UserDTO;
import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.service.IAvatarService;
import com.where.soul.users.service.IUsersService;
import com.where.soul.users.vo.UserOptionVO;
import com.where.soul.users.vo.UserUpdateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController extends BaseController {
    private final IUsersService usersService;
    private final IAvatarService avatarService;
    private final LoginManager loginManager;

    public UsersController(IUsersService usersService, IAvatarService avatarService, LoginManager loginManager) {
        this.usersService = usersService;
        this.avatarService = avatarService;
        this.loginManager = loginManager;
    }

    @GetMapping
    public Result getUserInfo(HttpServletRequest request) {
        String id = (String) request.getAttribute(Constant.ID);
        Users users = usersService.getById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(users, userDTO);
        userDTO.setGender(FormatterUtil.formatterGender(users.getGender()));

        Integer avatarId = users.getAvatarId();
        if (avatarId != null) {
            Avatar avatar = avatarService.getById(avatarId);
            userDTO.setAvatar(avatar.getAvatar());
        }
        return Result.success(userDTO);
    }

    @PostMapping("/login")
    public Result restLogin(@Valid UserOptionVO userOptionVO, HttpServletResponse response, HttpServletRequest request) {
        String id1 = (String) request.getAttribute(Constant.ID);
        System.out.println(id1);
        Integer id = usersService.getUserCountByLoginNameAndPassword(userOptionVO.getLoginName(), userOptionVO.getPassword());
        if (id == 0) {
            return Result.error("用户名或密码错误");
        }
        // TODO 用 redis 实现登录
        String key = loginManager.put(String.valueOf(id));
        Cookie cookie = new Cookie(Constant.W_TOKEN, key);
        response.addCookie(cookie);
        return Result.success(key);
    }

    @PostMapping("/check/name")
    public Result restAuthLoginName(String loginName) {
        if (StringUtils.isEmpty(loginName)) {
            return Result.customError("该名称已被占用！");
        }
        Integer num = usersService.getUserCountByLoginName(loginName);
        if (num > 0) {
            return Result.customError("该名称已被占用！");
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result restRegister(@Valid UserOptionVO userOptionVO) {
        Integer num = usersService.getUserCountByLoginName(userOptionVO.getLoginName());
        if (num > 0) {
            return Result.customError("该名称已被占用！");
        }
        Users user = usersService.addUser(userOptionVO.getLoginName(), userOptionVO.getPassword());
        if (user == null) {
            return Result.error();
        }
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result restUpdate(UserUpdateVO userUpdateVO) {
        Users user = new Users();
        BeanUtils.copyProperties(userUpdateVO, user);
        if (userUpdateVO.getGender() !=null) {
            user.setGender(userUpdateVO.getGender() == 1);
        }

        Security security = new Security();
        String phone = userUpdateVO.getPhone();
        String email = userUpdateVO.getEmail();
        if (phone != null) {
            security.setPhone(phone);
        }
        if (email != null) {
            security.setPhone(email);
        }
        Integer integer = usersService.updateUserById(user);
        return Result.success(integer);
    }

}

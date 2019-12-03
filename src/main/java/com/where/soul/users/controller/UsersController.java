package com.where.soul.users.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.where.soul.common.Result;
import com.where.soul.common.util.Regexp;
import com.where.soul.users.dto.UserDTO;
import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.service.IAvatarService;
import com.where.soul.users.service.IUsersService;
import com.where.soul.users.vo.UserUpdateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.where.soul.common.base.BaseController;

import javax.validation.constraints.Pattern;

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
    private final IUsersService usersService;
    private final IAvatarService avatarService;

    public UsersController(IUsersService usersService, IAvatarService avatarService) {
        this.usersService = usersService;
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}")
    public Result getUserInfo(@PathVariable("id") String id) {
        Users users = usersService.getById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(users.getGender());
        userDTO.setUsername(users.getUsername());
        userDTO.setAvatar(null);
        Integer avatarId = users.getAvatarId();
        if (avatarId != null) {
            Avatar avatar = avatarService.getById(avatarId);
            userDTO.setAvatar(avatar.getAvatar());
        }
        return Result.success(userDTO);
    }

    @PostMapping("/login")
    public Result restLogin(String loginName, String password) {
        Integer num = usersService.getUserCountByLoginNameAndPassword(loginName, password);
        if (num == 1) {
            // TODO 实现登录
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/check/name")
    public Result restAuthLoginName(String loginName) {
        Integer num = usersService.getUserCountByLoginName(loginName);
        if (num > 0) {
            return Result.customError("该名称已被占用！");
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result restRegister(String loginName, String password) {
        Integer num = usersService.getUserCountByLoginName(loginName);
        if (num > 0) {
            return Result.customError("该名称已被占用！");
        }
        Users user = usersService.addUser(loginName, password);
        if (user == null) {
            return Result.error();
        }
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result restUpdate(UserUpdateVO userUpdateVO) {
        Users user = new Users();
        user.setId(userUpdateVO.getId());
        user.setUsername(userUpdateVO.getUsername());
        user.setGender(userUpdateVO.getGender() == 1);

        Security security = new Security();
        String phone = userUpdateVO.getPhone();
        String email = userUpdateVO.getEmail();
        if (phone != null && !"".equals(phone)) {
            security.setPhone(phone);
        }
        if (email != null && !"".equals(email)) {
            security.setPhone(email);
        }
        return null;
    }

}

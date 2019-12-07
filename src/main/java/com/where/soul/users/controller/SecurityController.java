package com.where.soul.users.controller;


import com.where.soul.common.Constant;
import com.where.soul.common.Result;
import com.where.soul.common.ResultEnum;
import com.where.soul.common.util.FormatterUtil;
import com.where.soul.common.util.Regexp;
import com.where.soul.users.dto.UserDTO;
import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Users;
import com.where.soul.users.service.ISecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.where.soul.common.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 用户安全表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/users/security")
@Slf4j
public class SecurityController extends BaseController {

    private final ISecurityService securityService;

    public SecurityController(ISecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/find")
    public Result restUserInfo(String phoneOrEmail) {
        if (phoneOrEmail == null || "".equals(phoneOrEmail)) {
            return Result.error();
        }
        Pattern patternPhone = Pattern.compile(Regexp.PHONE);
        Pattern patternEmail = Pattern.compile(Regexp.EMAIL);

        Matcher phoneMatch = patternPhone.matcher(phoneOrEmail);
        Matcher emailMatch = patternEmail.matcher(phoneOrEmail);

        Users user = null;
        // 是手机号码
        if (phoneMatch.find()) {
            user = securityService.findUserByPhone(phoneOrEmail);
        }
        // 是邮箱
        if (emailMatch.find()) {
            user = securityService.findUserByPhone(phoneOrEmail);
        }

        if (user == null) {
            return Result.error("没有找到该用户！");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return Result.success(userDTO);
    }

    @PostMapping
    public Result resetPassword(String id, String password) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(password)) {
            return Result.error(ResultEnum.ARG_EMPTY);
        }
        Integer integer = securityService.updateUserPasswordByUserId(Integer.valueOf(id), password);
        if (integer == -1) {
            return Result.error("用户不存在！");
        }
        return Result.success();
    }
}

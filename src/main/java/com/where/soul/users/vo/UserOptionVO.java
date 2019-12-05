package com.where.soul.users.vo;

import com.where.soul.common.Constant;
import com.where.soul.common.util.Regexp;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author lw
 */
@Data
public class UserOptionVO {

    @Size(max = Constant.MAX_LOGIN_NAME, message = "登录名不能超过" + Constant.MAX_LOGIN_NAME + "位")
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @Size(min = Constant.MIN_PASSWORD, max = Constant.MAX_PASSWORD, message = "密码长度为" + Constant.MIN_PASSWORD + "至" + Constant.MAX_PASSWORD +"位")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = Regexp.PASSWORD, message = "密码为数字,字母,符号且长度"+ Constant.MIN_PASSWORD + "至" + Constant.MAX_PASSWORD +"位")
    private String password;
}

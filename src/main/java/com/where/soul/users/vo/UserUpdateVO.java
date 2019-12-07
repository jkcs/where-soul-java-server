package com.where.soul.users.vo;

import com.where.soul.common.util.Regexp;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author lw
 */
@Data
public class UserUpdateVO {

    private String username;

    private String avatar;

    @Pattern(regexp = Regexp.GENDER, message = "性别不合法")
    private Integer gender;

    @Pattern(regexp = Regexp.PHONE, message = "手机号码不合法")
    private String phone;

    @Pattern(regexp = Regexp.EMAIL, message = "手机号码不合法")
    private String email;

}

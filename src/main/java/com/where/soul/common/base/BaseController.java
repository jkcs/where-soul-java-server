package com.where.soul.common.base;

import com.where.soul.common.Constant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lw
 */
public class BaseController {

    protected Integer getUserId(HttpServletRequest request) {
        String id = (String) request.getAttribute(Constant.ID);
        return Integer.valueOf(id);
    }

    protected String getToken(HttpServletRequest request) {
        return request.getHeader(Constant.W_TOKEN);
    }
}

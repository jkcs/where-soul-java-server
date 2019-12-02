package com.where.soul.config;

import com.where.soul.common.Result;
import com.where.soul.common.ResultEnum;
import com.where.soul.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lw
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param request   request
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest request, BizException exception) {
        log.error("业务异常！-> {}", exception.getMessage());
        return Result.error(exception.getCode(), exception.getMessage());
    }


    /**
     * 处理空指针的异常
     *
     * @param request   request
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, NullPointerException exception) {
        log.error("空指针异常！-> ", exception);
        return Result.error(ResultEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理请求方法不支持的异常
     *
     * @param request   request
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        log.error("请求方法不支持异常！-> ", exception);
        return Result.error(ResultEnum.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param request   request
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception exception) {
        log.error("未知异常！-> ", exception);
        return Result.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }

}

package com.where.soul.config;

import com.where.soul.common.Result;
import com.where.soul.common.ResultEnum;
import com.where.soul.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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
        return Result.error(ResultEnum.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理参数校验异常
     *
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result exceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return Result.customError(message);
        }
        return Result.error(ResultEnum.ARG_EMPTY);
    }

    /**
     * 处理参数校验异常
     *
     * @param exception exception
     * @return Result 对象
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result exceptionHandler(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return Result.customError(message);
        }
        return Result.error(ResultEnum.ARG_EMPTY);
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

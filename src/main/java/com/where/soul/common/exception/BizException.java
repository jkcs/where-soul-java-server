package com.where.soul.common.exception;

import com.where.soul.common.base.BaseErrorInterface;

/**
 * @author lw
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    protected Integer code;
    /**
     * 错误信息
     */
    protected String message;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInterface errorInterface) {
        super(errorInterface.getResultMessage());
        this.code = errorInterface.getResultCode();
        this.message = errorInterface.getResultMessage();
    }

    public BizException(BaseErrorInterface errorInterface, Throwable cause) {
        super(errorInterface.getResultMessage(), cause);
        this.code = errorInterface.getResultCode();
        this.message = errorInterface.getResultMessage();
    }

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}

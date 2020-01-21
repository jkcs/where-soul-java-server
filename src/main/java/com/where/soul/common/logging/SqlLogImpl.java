package com.where.soul.common.logging;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.logging.Log;
/**
 *
 * @author lw
 */
@Slf4j
public class SqlLogImpl implements Log  {
    public SqlLogImpl(String clazz) {
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        log.error(s);
//        System.err.println(s);
    }

    @Override
    public void debug(String s) {
//        System.out.println(s);
        log.debug(s);
    }

    @Override
    public void trace(String s) {
//        System.out.println(s);
        log.trace(s);
    }

    @Override
    public void warn(String s) {
//        System.out.println(s);
        log.warn(s);
    }
}

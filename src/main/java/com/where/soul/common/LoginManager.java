package com.where.soul.common;

import com.where.soul.common.util.GeneratorUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 暂时先用这个管理登录，有时间改成 redis
 *
 * @author lw
 */
@Component
public class LoginManager {
    private Map<String, String>  mangerMap = new ConcurrentHashMap<>();

    public String put(String id) {
        String key = GeneratorUtil.generatorMd5(LocalDateTime.now().toString(), id);
        this.mangerMap.put(key, id);
        return key;
    }

    public String get(String key) {
        return this.mangerMap.get(key);
    }

    /**
     * 判断是否登录
     * @param key 键
     * @return 是否登录
     */
    public boolean isLogin(String key) {
        String idMap = this.get(key);
        return !StringUtils.isEmpty(idMap);
    }

}

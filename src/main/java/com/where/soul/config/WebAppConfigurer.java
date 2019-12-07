package com.where.soul.config;

import com.where.soul.common.LoginManager;
import com.where.soul.common.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lw
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    private final LoginManager loginManager;

    public WebAppConfigurer(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // addPathPatterns 用于添加拦截规则
                // excludePathPatterns 用户排除拦截
                InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AuthInterceptor(loginManager));
                interceptorRegistration.addPathPatterns("/**");
                interceptorRegistration
                        .excludePathPatterns("/users/login", "/users/login/out", "/users/register","/users/check/name", "/**/security/**");
            }
        };
    }

}

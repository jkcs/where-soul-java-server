package com.where.soul.com.where.soul.users.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
class UsersController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void postRegister() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("loginName", "adminTest11111111111111111111111111111111111111111111");
        map.add("password", "admin");

        MvcResult mvcResult = mockMvc.perform(
            post("/users/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(JSONObject.toJSONString(map))
                .params(map)
        ).andReturn();
        log.info(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void postLogin() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post("/users/users/login"))
                .andDo(print())
                .andReturn();
        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void testGen() throws Exception {
//        byte[] digest = MD5.create().digest("21123");
//        String s = String.valueOf(digest);
//        System.out.println(s);
        String s = SecureUtil.md5("12321").toString();
        System.out.println(s);
    }
}

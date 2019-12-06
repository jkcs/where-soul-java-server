package com.where.soul.com.where.soul.users.controller;

import com.where.soul.common.util.GeneratorUtil;
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

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
class UsersController {
    @Autowired
    private MockMvc mockMvc;
    private String loginName = "admin";
    private String password = "123456";

    @Test
    void getUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/users/users/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andReturn();

        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void postRegister() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("loginName", loginName);
        map.add("password", password);

        MvcResult mvcResult = mockMvc.perform(
            post("/users/users/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(map)
        ).andReturn();

        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void postLogin() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("loginName", loginName);
        map.add("password", password);

        MvcResult mvcResult = mockMvc
                .perform(post("/users/users/login").params(map))
                .andReturn();
        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void postUpdate() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "1");
        map.add("gender", "1");
        map.add("password", password);

        MvcResult mvcResult = mockMvc
                .perform(post("/users/users/update").params(map))
                .andReturn();
        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void genAES() {
//        byte[] encoded = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//        System.out.println(Arrays.toString(encoded));
        String s = "1";
        String s1 = GeneratorUtil.generatorAesCode(s);
        System.out.println(s1);
    }

    @Test
    void deAES() {
        String s = "b7780a86f9e458ed8f36a19de1c759eb";
        String s1 = GeneratorUtil.decryptAesCode(s);
        System.out.println(s1);
    }


    @Test
    void genPassword() {
        String s = GeneratorUtil.generatorMd5(loginName, password);
        System.out.println(s);
    }
}

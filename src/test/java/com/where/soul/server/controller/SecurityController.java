package com.where.soul.server.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
class SecurityController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserByPhoneOrEmail() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("phoneOrEmail", "15907898652");
        MvcResult mvcResult = mockMvc.perform(
                post("/users/security/find")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(map)
        ).andReturn();

        log.warn(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}

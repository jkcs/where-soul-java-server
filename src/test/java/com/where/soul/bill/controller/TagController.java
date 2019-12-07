package com.where.soul.bill.controller;

import com.where.soul.common.Constant;
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
class TagController {
    private String token = "d90b58c61cea1c2b1bc9862381a27db0";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addTag() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        MvcResult mvcResult = mockMvc.perform(
                post("/bill/tag/add")
                        .header(Constant.W_TOKEN, token)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(map)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}

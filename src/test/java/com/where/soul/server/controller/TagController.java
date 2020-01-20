package com.where.soul.server.controller;

import com.where.soul.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        map.add("name", "测试父4");
        map.add("parentId", "4");
        MvcResult mvcResult = mockMvc.perform(
                post("/bill/tag/add")
                        .header(Constant.W_TOKEN, token)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(map)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void getTags() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/bill/tag")
                        .header(Constant.W_TOKEN, token)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void getTagChildren() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/bill/tag/children/1")
                        .header(Constant.W_TOKEN, token)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void testArrayList() throws Exception {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list);
    }
}

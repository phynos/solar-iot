package com.phynos.solar.module.index.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void date() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/example/date")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("begin", "2021-05-25")
                .param("end", "2021-05-26")
                .param("time", "2021-05-26 10:37:00");
        MvcResult mvcResult = mockMvc.perform(builder)
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(content);
        assertNotNull(content);
    }

    @Test
    void localDate() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/example/localDate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("begin", "2021-05-25")
                .param("end", "2021-05-26")
                .param("time", "2021-05-26 10:37:00");
        MvcResult mvcResult = mockMvc.perform(builder)
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(content);
        assertNotNull(content);
    }

}
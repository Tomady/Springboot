package com.travelmaker.board.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@Slf4j
public class BoardControllerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(
            String.valueOf(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap()
            )
        );
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(
            MockMvcRequestBuilders.post("/board/register")
            .param("title", "/board/register 테스트")
            .param("writer", "yellow_dan")
            .param("content", "springboot thymeleaf test")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(
            String.valueOf(
                mockMvc.perform(
                    MockMvcRequestBuilders
                    .get("/board/get")
                    .param("bno", "1")
                )
                .andReturn()
                .getModelAndView()
                .getModelMap()
            )
        );
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "23")
                .param("title", "title 수정")
                .param("writer", "yellowDan")
                .param("content", "나는 바보다")
            ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc.perform(
            MockMvcRequestBuilders.post("/board/remove")
            .param("bno", "23")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testListPaging() throws Exception {
        log.info(
            String.valueOf(
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/board/list")
                    .param("pageNum", "2")
                    .param("amount", "50")
                ).andReturn().getModelAndView().getModelMap()
            )
        );
    }
}

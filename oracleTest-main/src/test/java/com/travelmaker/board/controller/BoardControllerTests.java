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
            .param("management_id", "1")
            .param("title", "/board/register 테스트")
            .param("code_id", "F3333")
            .param("nickname", "yellow_dan")
            .param("bcontent", "springboot thymeleaf test")
            .param("status", "F")
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
                    .param("bbs_id", "1")
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
                .param("bbs_id", "23")
                .param("management_id", "1")
                .param("title", "title 수정")
                .param("code_id", "F3333")
                .param("nickname", "yellowDan")
                .param("bcontent", "나는 바보다")
                .param("status", "F")
                .param("plan_id", (String) null)
            ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc.perform(
            MockMvcRequestBuilders.post("/board/remove")
            .param("bbs_id", "23")
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

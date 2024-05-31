package com.travelmaker.sample;

import com.google.gson.Gson;
import com.travelmaker.home.domain.Ticket;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@WebAppConfiguration
public class SampleControllerTests {
    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testConvert() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTno(123);
        ticket.setOwner("Admin");
        ticket.setGrade("AAA");

        String jsonStr = new Gson().toJson(ticket);

        log.info(jsonStr);

        mockMvc.perform(
            post("/sample/ticket")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonStr))
            .andExpect(status().is(200)
        );
    }
}

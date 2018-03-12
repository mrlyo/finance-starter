package com.cgi.financeplanner.financeplanner;

import com.cgi.financeplanner.controller.MainController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getshouldredirect() throws Exception {

        this.mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getstartshouldreturnhomeview() throws Exception {
        this.mockMvc.perform(get("/start"))
                .andExpect(status().isOk())
                .andExpect(
                        view().name("homeview")
                );
    }


}

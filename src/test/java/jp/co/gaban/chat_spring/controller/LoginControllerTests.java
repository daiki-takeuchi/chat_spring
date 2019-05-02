package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by DaikiTakeuchi on 2019/05/01.
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(LoginController.class)
public class LoginControllerTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    LoginController controller;
//
//    @MockBean
//    private UserService userService;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }

    @Test
    public void getLoginTest() throws Exception {
        // when
//        mockMvc.perform(get("/login"))
//                .andExpect(status().isOk());
    }
}

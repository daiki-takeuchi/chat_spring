package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.model.form.LoginForm;
import jp.co.gaban.chat_spring.domain.model.validator.PasswordMatchValidator;
import jp.co.gaban.chat_spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by DaikiTakeuchi on 2019/05/01.
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(LoginController.class)
public class LoginControllerTests {

    private final static Long TEST_USER1_ID = 1L;
    private final static String TEST_USER1_NAME = "test_user1_name";
    private final static String TEST_USER1_MAIL = "test1@test.com";
    private final static String TEST_USER1_PASSWORD = "test1";

    @Mock
    private PasswordMatchValidator validator;

    @Mock
    private UserService userService;

    @Mock
    private RequestAttributes attrs;

    private MockMvc mockMvc;

    @InjectMocks
    LoginController controller;

    @Before
    public void setup() {
        User testUser1 = new User();
        testUser1.setId(TEST_USER1_ID);
        testUser1.setUserName(TEST_USER1_NAME);
        testUser1.setMail(TEST_USER1_MAIL);
        testUser1.setPassword(TEST_USER1_PASSWORD);

        MockitoAnnotations.initMocks(this);
        RequestContextHolder.setRequestAttributes(attrs);

        when(userService.findByMail(any())).thenReturn(testUser1);
        when(attrs.getAttribute("user", RequestAttributes.SCOPE_SESSION)).thenReturn(testUser1);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(new StandaloneMvcTestViewResolver()).build();

    }

    @Test
    public void getLoginTest() throws Exception {
        // when
        MvcResult result = mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();

        LoginForm resultForm = (LoginForm) result.getModelAndView().getModel().get("form");

        // then
        assertThat(resultForm.getMail()).isBlank();
        assertThat(resultForm.getPassword()).isBlank();
    }

    @Test
    public void postLoginInvalidTest() throws Exception {
        // given
        LoginForm form = new LoginForm();
        form.setMail(null);
        form.setPassword(null);

        // when
        mockMvc.perform((post("/login")).flashAttr("form",form))
                .andExpect(model().hasErrors())
                .andExpect(model().attribute("form", form))
                .andExpect(view().name("login"));
    }

    @Test
    public void postLoginValidTest() throws Exception {
        // given
        LoginForm form = new LoginForm();
        form.setMail(TEST_USER1_MAIL);
//        form.setPassword(TEST_USER1_PASSWORD);

        when(validator.isValid(any(), any())).thenReturn(true);

        // when
        mockMvc.perform((post("/login")).flashAttr("form",form))
//                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("form", form))
                .andExpect(view().name("login"));
    }
}

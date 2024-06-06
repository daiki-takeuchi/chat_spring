package jp.co.gaban.chat_spring.controller;

import jakarta.servlet.http.HttpSession;
import jp.co.gaban.chat_spring.annotation.NonAuth;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.model.form.LoginForm;
import jp.co.gaban.chat_spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by takeuchidaiki on 2024/06/03
 */
@Controller
public class LoginController {

    public final static String LOGIN_PAGE = "/login";
    private final static String LOGIN_HTML = "login";
    private final static String LOGOUT_PAGE = "/logout";

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public LoginController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @NonAuth
    @GetMapping(LoginController.LOGIN_PAGE)
    public String login(@ModelAttribute("form") LoginForm loginForm) {
        return LoginController.LOGIN_HTML;
    }

    @NonAuth
    @PostMapping(LoginController.LOGIN_PAGE)
    public String login(@ModelAttribute("form") @Validated LoginForm form, BindingResult result) {
        logger.info("LoginController:[login] Passing through...");
        logger.info("form:" + form.toString());
        logger.info("result:" + result.toString());

        if(!result.hasErrors()) {
            User user = userService.findByMail(form.getMail());
            // ユーザー情報をセッションに保存
            session.setAttribute("user", user);
            return "redirect:";
        }
        return LoginController.LOGIN_HTML;
    }

    @GetMapping(LoginController.LOGOUT_PAGE)
    public String logout() {
        logger.info("LoginController:[logout] Passing through...");
        session.invalidate();
        return "redirect:" + LoginController.LOGIN_HTML;
    }
}

package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.UserService;
import jp.co.gaban.chat_spring.view.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by DaikiTakeuchi on 2019/04/05.
 */
@Controller
public class LoginController {

    private final static String LOGIN_PAGE = "/login";
    private final static String LOGIN_HTML = "login";
    private final static String LOGOUT_PAGE = "/logout";
    private final static String LOGOUT_HTML = "logout";

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = LoginController.LOGIN_PAGE, method = RequestMethod.GET)
    public String index(@ModelAttribute("form") LoginForm form) {
        return LoginController.LOGIN_HTML;
    }

    @RequestMapping(value = LoginController.LOGIN_PAGE, method = RequestMethod.POST)
    public String login(@ModelAttribute("form") @Validated LoginForm form, BindingResult result) {
        logger.debug("LoginController:[login] Passing through...");
        logger.debug("form:" + form.toString());

        if(form.getMail() != null) {
            User user = userService.findByMail(form.getMail());

            // 取得
            User sessUser = (User)session.getAttribute("user");
            if(sessUser != null) {
                logger.debug("user:" + sessUser);
            }
            // 保存
            session.setAttribute("user", user);
            return "redirect:" + LoginController.LOGIN_HTML;
        }
        return LoginController.LOGIN_HTML;
    }

    @RequestMapping(value = LoginController.LOGOUT_PAGE, method = RequestMethod.GET)
    public String logout() {
        logger.debug("LoginController:[logout] Passing through...");
        session.invalidate();
        return "redirect:" + LoginController.LOGOUT_HTML;
    }
}

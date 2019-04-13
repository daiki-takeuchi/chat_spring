package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.annotation.NonAuth;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.model.form.RegisterForm;
import jp.co.gaban.chat_spring.service.UserService;
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
 * Created by DaikiTakeuchi on 2019/04/13.
 */
@Controller
public class UserController {

    private final static String REGISTER_PAGE = "/register";
    private final static String REGISTER_HTML = "user/register";

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    HttpSession session;

    @NonAuth
    @RequestMapping(value = UserController.REGISTER_PAGE, method = RequestMethod.GET)
    public String register(@ModelAttribute("form") RegisterForm form) {
        return UserController.REGISTER_HTML;
    }

    @NonAuth
    @RequestMapping(value = UserController.REGISTER_PAGE, method = RequestMethod.POST)
    public String register(@ModelAttribute("form") @Validated RegisterForm form, BindingResult result) {
        logger.debug("UserController:[register] Passing through...");
        logger.debug("form:" + form.toString());
        logger.debug("result:" + result.toString());

        if(!result.hasErrors()) {
            // 登録処理
            User user = new User();
            user.setUser_name(form.getUser_name());
            user.setMail(form.getMail());
            user.setPassword(form.getPassword());

            userService.save(user);

            // ユーザー情報をセッションに保存
            session.setAttribute("user", user);
            return "redirect:";
        }
        return UserController.REGISTER_HTML;
    }
}

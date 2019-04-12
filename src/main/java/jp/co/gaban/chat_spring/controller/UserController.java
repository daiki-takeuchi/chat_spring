package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.annotation.NonAuth;
import jp.co.gaban.chat_spring.domain.model.form.RegisterForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
@Controller
public class UserController {

    private final static String REGISTER_PAGE = "/register";
    private final static String REGISTER_HTML = "user/register";

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

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
        }
        return UserController.REGISTER_HTML;
    }
}

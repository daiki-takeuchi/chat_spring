package jp.co.gaban.chat_spring.controller;

import javafx.geometry.Pos;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.UserService;
import jp.co.gaban.chat_spring.view.LoginForm;
import jp.co.gaban.chat_spring.view.PostForm;
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
public class PostController {

    private final static String ROOT_PAGE = "/";
    final static String ROOT_HTML = "index";

    private final static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    HttpSession session;

    @RequestMapping(value = {PostController.ROOT_PAGE, "/" + PostController.ROOT_PAGE})
    public String index(@ModelAttribute("form") @Validated PostForm form, BindingResult result) {
        logger.debug("PostController:[index] Passing through...");
        logger.debug("form:" + form.toString());
        return PostController.ROOT_HTML;
    }
}

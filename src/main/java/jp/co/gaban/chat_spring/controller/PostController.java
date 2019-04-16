package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.PostService;
import jp.co.gaban.chat_spring.service.UserService;
import jp.co.gaban.chat_spring.domain.model.form.PostForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final static String ROOT_HTML = "post/index";

    private final static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = {PostController.ROOT_PAGE, "/index"}, method = RequestMethod.GET)
    public String index(@ModelAttribute("form") PostForm form, Model model) {
        logger.debug("PostController:[index] Passing through...");
        logger.debug("form:" + form.toString());

        User sessUser = (User)session.getAttribute("user");
        User user = userService.findById(sessUser.getId());

        Iterable<Post> posts = postService.findAll(1, 10, "id");

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return PostController.ROOT_HTML;
    }

    @RequestMapping(value = {PostController.ROOT_PAGE, "/index"}, method = RequestMethod.POST)
    public String index(@ModelAttribute("form") @Validated PostForm form, BindingResult result, Model model) {
        logger.debug("PostController:[index] Passing through...");
        logger.debug("form:" + form.toString());
        logger.debug("result:" + result.toString());

        User sessUser = (User)session.getAttribute("user");
        User user = userService.findById(sessUser.getId());

        Iterable<Post> posts = postService.findAll(1, 10, "id");

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return PostController.ROOT_HTML;
    }
}

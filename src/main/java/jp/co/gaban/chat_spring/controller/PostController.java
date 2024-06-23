package jp.co.gaban.chat_spring.controller;

import jakarta.servlet.http.HttpSession;
import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.model.form.PostForm;
import jp.co.gaban.chat_spring.service.PostService;
import jp.co.gaban.chat_spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by takeuchidaiki on 2024/06/04
 */
@Controller
public class PostController {

    private final static String ROOT_PAGE = "/";
    private final static String ROOT_HTML = "post/index";
    private final static String ROOT_PAGER_PAGE = "/page/{page}";
    private final static String DELETE_PAGE = "/delete/{id}";

    private final static Logger logger = LoggerFactory.getLogger(PostController.class);

    private final UserService userService;
    private final PostService postService;
    private final HttpSession session;

    @Autowired
    public PostController(UserService userService, PostService postService, HttpSession session) {
        this.userService = userService;
        this.postService = postService;
        this.session = session;
    }

    @GetMapping(value = {PostController.ROOT_PAGE, "/index", PostController.ROOT_PAGER_PAGE})
    public String index(@ModelAttribute("form") PostForm form, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.info("PostController:[index] Passing through...");
        logger.info("form:" + form.toString());

        int page = 1;
        if(argPage.isPresent()) {
            page = argPage.get();
        }

        User sessUser = (User)session.getAttribute("user");
        User user = userService.findById(sessUser.getId());

        Iterable<Post> posts = postService.findAll(page,"id");

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return PostController.ROOT_HTML;
    }

    @PostMapping(value = {PostController.ROOT_PAGE, "/index", PostController.ROOT_PAGER_PAGE})
    public String index(@ModelAttribute("form") @Validated PostForm form, BindingResult result, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.info("PostController:[index] Passing through...");
        logger.info("form:" + form.toString());
        logger.info("result:" + result.toString());

        int page = 1;
        if(argPage.isPresent()) {
            page = argPage.get();
        }

        User sessUser = (User)session.getAttribute("user");
        User user = userService.findById(sessUser.getId());

        Iterable<Post> posts = postService.findAll(page,"id");

        if(!result.hasErrors()) {
            // 登録処理
            Post post = new Post();
            post.setContent(form.getContent());
            post.setUserId(user.getId());
            postService.save(post);

            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return PostController.ROOT_HTML;
    }

    @GetMapping(value = PostController.DELETE_PAGE)
    public String delete(@PathVariable("id") long id) {
        logger.info("PostController:[delete] Passing through...");

        User sessUser = (User)session.getAttribute("user");
        Post post = postService.findById(id);

        if(post.getUserId() != null && post.getUserId().equals(sessUser.getId())) {
            postService.delete(post);
        }
        return "redirect:/";
    }
}

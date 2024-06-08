package jp.co.gaban.chat_spring.controller;

import jakarta.servlet.http.HttpSession;
import jp.co.gaban.chat_spring.annotation.NonAuth;
import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.model.form.PostForm;
import jp.co.gaban.chat_spring.domain.model.form.ProfileWizardForm;
import jp.co.gaban.chat_spring.domain.model.form.RegisterForm;
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
 * Created by takeuchidaiki on 2024/06/01
 */
@Controller
public class UserController {

    private final static String REGISTER_PAGE = "/register";
    private final static String REGISTER_HTML = "user/register";
    private final static String DETAIL_PAGE = "/user/{id}";
    private final static String DETAIL_HTML = "user/detail";
    private final static String DETAIL_PAGER_PAGE = "/user/{id}/page/{page}";
    private final static String INDEX_PAGE = "/user";
    private final static String INDEX_HTML = "user/index";
    private final static String INDEX_PAGER_PAGE = "/user/page/{page}";
    private final static String PROFILE_PAGE = "/profile/{id}";
    private final static String PROFILE_HTML = "user/profile_wizard";

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final PostService postService;
    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, PostService postService, HttpSession session) {
        this.userService = userService;
        this.postService = postService;
        this.session = session;
    }

    @GetMapping(value ={UserController.INDEX_PAGE, UserController.INDEX_PAGER_PAGE})
    public String index(@ModelAttribute("form") PostForm form, @RequestParam("user_name") Optional<String> argUserName, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.info("UserController:[index] Passing through...");
        logger.info("form:" + form.toString());

        int page = 1;
        if(argPage.isPresent()) {
            page = argPage.get();
        }

        String userName = "";
        if(argUserName.isPresent()) {
            userName = argUserName.get();
        }

        User sessUser = (User)session.getAttribute("user");
        User user = userService.findById(sessUser.getId());

        Iterable<User> result = userService.findByUserName(userName, page, "id");

        model.addAttribute("user", user);
        model.addAttribute("result", result);

        return UserController.INDEX_HTML;
    }

    @GetMapping(value = {UserController.DETAIL_PAGE, UserController.DETAIL_PAGER_PAGE})
    public String detail(@ModelAttribute("form") PostForm form, @PathVariable("id") Long id, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.info("UserController:[detail] Passing through...");
        logger.info("form:" + form.toString());

        int page = 1;
        if(argPage.isPresent()) {
            page = argPage.get();
        }

        User user = userService.findById(id);
        Iterable<Post> posts = postService.findByUserId(id, page, "id");

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return UserController.DETAIL_HTML;
    }

    @GetMapping(value = UserController.PROFILE_PAGE)
    public String profile(@ModelAttribute("form") ProfileWizardForm form, @PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        form.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("jobItems", ProfileWizardForm.JOB_ITEMS);

        return UserController.PROFILE_HTML;
    }

    @PostMapping(value = UserController.PROFILE_PAGE)
    public String profile(@ModelAttribute("form") @Validated ProfileWizardForm form, BindingResult result, @PathVariable("id") Long id, Model model) {
        logger.info("UserController:[profile] Passing through...");
        logger.info("form:" + form.toString());
        logger.info("result:" + result.toString());
        User user = userService.findById(id);

        if(!result.hasErrors()) {
            // 登録処理
            user.setUserName(form.getUserName());
            user.setMail(form.getMail());
            user.setSelfIntroduction(form.getSelfIntroduction());
            user.setJob(String.join(" / ", form.getJob()));

            userService.save(user);

            // ユーザー情報をセッションに保存
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("jobItems", ProfileWizardForm.JOB_ITEMS);
        return UserController.PROFILE_HTML;
    }

    @NonAuth
    @GetMapping(value = UserController.REGISTER_PAGE)
    public String register(@ModelAttribute("form") RegisterForm form) {
        return UserController.REGISTER_HTML;
    }

    @NonAuth
    @PostMapping(value = UserController.REGISTER_PAGE)
    public String register(@ModelAttribute("form") @Validated RegisterForm form, BindingResult result) {
        logger.info("UserController:[register] Passing through...");
        logger.info("form:" + form.toString());
        logger.info("result:" + result.toString());

        if(!result.hasErrors()) {
            // 登録処理
            User user = new User();
            user.setUserName(form.getUserName());
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

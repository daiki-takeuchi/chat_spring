package jp.co.gaban.chat_spring.controller;

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

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
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

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = {UserController.INDEX_PAGE, UserController.INDEX_PAGER_PAGE}, method = RequestMethod.GET)
    public String index(@ModelAttribute("form") PostForm form, @RequestParam("user_name") Optional<String> argUserName, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.debug("UserController:[index] Passing through...");
        logger.debug("form:" + form.toString());

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

    @RequestMapping(value = {UserController.DETAIL_PAGE, UserController.DETAIL_PAGER_PAGE}, method = RequestMethod.GET)
    public String detail(@ModelAttribute("form") PostForm form, @PathVariable("id") Long id, Model model, @PathVariable("page") Optional<Integer> argPage) {
        logger.debug("UserController:[detail] Passing through...");
        logger.debug("form:" + form.toString());

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

    @RequestMapping(value = UserController.PROFILE_PAGE, method = RequestMethod.GET)
    public String profile(@ModelAttribute("form") ProfileWizardForm form, @PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        form.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("jobItems", ProfileWizardForm.JOB_ITEMS);

        return UserController.PROFILE_HTML;
    }

    @RequestMapping(value = UserController.PROFILE_PAGE, method = RequestMethod.POST)
    public String profile(@ModelAttribute("form") ProfileWizardForm form, BindingResult result, @PathVariable("id") Long id) {
        User user = userService.findById(id);

        if(!result.hasErrors()) {
            // 登録処理
            user.setUserName(form.getUserName());
            user.setMail(form.getMail());
            user.setSelf_introduction(form.getSelf_introduction());
            user.setJob(String.join(" / ", form.getJob()));

            userService.save(user);

            // ユーザー情報をセッションに保存
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return UserController.PROFILE_HTML;
    }

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

package jp.co.gaban.chat_spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.co.gaban.chat_spring.domain.model.Following;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.FollowingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by takeuchidaiki on 2024/06/04
 */
@Controller
public class FollowingController {

    private final static String FOLLOW_PAGE = "/follow/{following_id}";
    private final static String UNFOLLOW_PAGE = "/unfollow/{following_id}";

    private final static Logger logger = LoggerFactory.getLogger(FollowingController.class);

    private final FollowingService followingService;
    private final HttpSession session;

    @Autowired
    public FollowingController(FollowingService followingService, HttpSession session) {
        this.followingService = followingService;
        this.session = session;
    }

    @GetMapping(value = FollowingController.FOLLOW_PAGE)
    public String follow(@PathVariable("following_id") long followingId, HttpServletRequest request) {
        logger.info("FollowingController:[follow] Passing through...");

        User sessUser = (User)session.getAttribute("user");

        Following f = followingService.findByUserIdAndFollowingId(sessUser.getId(), followingId);
        if(f == null) {
            Following following = new Following();
            following.setUserId(sessUser.getId());
            following.setFollowingId(followingId);

            followingService.save(following);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping(value = FollowingController.UNFOLLOW_PAGE)
    public String unfollow(@PathVariable("following_id") long followingId, HttpServletRequest request) {
        logger.info("FollowingController:[unfollow] Passing through...");

        User sessUser = (User)session.getAttribute("user");

        Following following = followingService.findByUserIdAndFollowingId(sessUser.getId(), followingId);
        if(following != null) {
            followingService.delete(following);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}

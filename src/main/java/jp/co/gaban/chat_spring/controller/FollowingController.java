package jp.co.gaban.chat_spring.controller;

import jp.co.gaban.chat_spring.domain.model.Following;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.FollowingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
@Controller
public class FollowingController {

    private final static String FOLLOW_PAGE = "/follow/{following_id}";
    private final static String UNFOLLOW_PAGE = "/unfollow/{following_id}";

    private final static Logger logger = LoggerFactory.getLogger(FollowingController.class);

    @Autowired
    private FollowingService followingService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = FollowingController.FOLLOW_PAGE, method = RequestMethod.GET)
    public String follow(@PathVariable("following_id") long followingId, HttpServletRequest request) {
        logger.debug("FollowingController:[follow] Passing through...");

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

    @RequestMapping(value = FollowingController.UNFOLLOW_PAGE, method = RequestMethod.GET)
    public String unfollow(@PathVariable("following_id") long followingId, HttpServletRequest request) {
        logger.debug("FollowingController:[unfollow] Passing through...");

        User sessUser = (User)session.getAttribute("user");

        Following following = followingService.findByUserIdAndFollowingId(sessUser.getId(), followingId);
        if(following != null) {
            followingService.delete(following);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}

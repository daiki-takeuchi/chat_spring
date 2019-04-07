package jp.co.gaban.chat_spring;

import jp.co.gaban.chat_spring.annotation.NonAuth;
import jp.co.gaban.chat_spring.controller.LoginController;
import jp.co.gaban.chat_spring.domain.model.User;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by takeuchidaiki on 2019/04/08
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //静的リソースの場合は認証不要
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // @NonAuthがついてるメソッドは認証不要
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        NonAuth annotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
        if (annotation != null) {
            return true;
        }

        User sessUser = (User) request.getSession().getAttribute("user");
        if (sessUser == null) {
            response.sendRedirect(LoginController.LOGIN_PAGE);
            return false;
        }
        return true;
    }
}

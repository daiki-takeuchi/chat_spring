package jp.co.gaban.chat_spring.domain.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByMail(value);
        User sessUser = (User) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                .getAttribute("user", RequestAttributes.SCOPE_SESSION);

        // 同じメールアドレスがなければOK
        if(user == null) return true;
        // 新規登録時に同じメールアドレスがあるばあいはNG
        if(sessUser == null) return false;
        // 更新時に自分のメアドであればOK、自分以外のメアドと重複する場合はNG
        return sessUser.getId().equals(user.getId());
    }
}

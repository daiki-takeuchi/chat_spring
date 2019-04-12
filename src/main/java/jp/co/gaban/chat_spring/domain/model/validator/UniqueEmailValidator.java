package jp.co.gaban.chat_spring.domain.model.validator;

import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserService userService;

    @Override
    public void initialize(UniqueEmail annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByMail(value);
        if(user != null) {
            return false;
        } else {
            return true;
        }
    }
}

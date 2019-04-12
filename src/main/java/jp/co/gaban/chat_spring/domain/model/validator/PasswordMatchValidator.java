package jp.co.gaban.chat_spring.domain.model.validator;

import jp.co.gaban.chat_spring.annotation.PasswordMatch;
import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.service.UserService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    @Autowired
    UserService userService;

    private String mail;
    private String password;
    private String message;

    @Override
    public void initialize(PasswordMatch annotation) {
        this.mail = annotation.mail();
        this.password = annotation.password();
        this.message = annotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String mailValue = (String)beanWrapper.getPropertyValue(mail);
        String passwordValue = (String)beanWrapper.getPropertyValue(password);

        if(mailValue == null || passwordValue == null) {
            return true;
        }

        User user = userService.findByMail(mailValue);
        if(user != null && user.canLogin(passwordValue)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(password).addConstraintViolation();
            return false;
        }
    }
}

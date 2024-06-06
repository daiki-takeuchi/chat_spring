package jp.co.gaban.chat_spring.domain.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.co.gaban.chat_spring.annotation.EqualTo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Created by DaikiTakeuchi on 2019/04/13.
 */
public class EqualToValidator implements ConstraintValidator<EqualTo, Object> {

    private String firstField;
    private String secondField;
    private String message;

    @Override
    public void initialize(EqualTo annotation) {
        this.firstField = annotation.firstField();
        this.secondField = annotation.secondField();
        this.message = annotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String firstFieldValue = (String)beanWrapper.getPropertyValue(firstField);
        String secondFieldValue = (String)beanWrapper.getPropertyValue(secondField);

        if(firstFieldValue == null || secondFieldValue == null) {
            return true;
        }

        if(firstFieldValue.equals(secondFieldValue)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondField).addConstraintViolation();
            return false;
        }
    }
}

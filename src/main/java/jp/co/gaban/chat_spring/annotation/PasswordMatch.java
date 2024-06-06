package jp.co.gaban.chat_spring.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jp.co.gaban.chat_spring.domain.model.validator.PasswordMatchValidator;

import java.lang.annotation.*;

/**
 * Created by takeuchidaiki on 2019/04/13
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordMatchValidator.class})
@ReportAsSingleViolation
public @interface PasswordMatch {
    String message() default "{password Unmatched}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String mail();
    String password();

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        PasswordMatch[] value();
    }
}

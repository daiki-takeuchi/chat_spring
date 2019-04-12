package jp.co.gaban.chat_spring.annotation;

import jp.co.gaban.chat_spring.domain.model.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by takeuchidaiki on 2019/04/13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueEmailValidator.class})
@ReportAsSingleViolation
public @interface UniqueEmail {
    String message() default "{This email has already been registered.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        UniqueEmail[] value();
    }
}

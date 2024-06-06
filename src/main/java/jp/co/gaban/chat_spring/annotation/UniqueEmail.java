package jp.co.gaban.chat_spring.annotation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jp.co.gaban.chat_spring.domain.model.validator.UniqueEmailValidator;

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

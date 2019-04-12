package jp.co.gaban.chat_spring.annotation;

import jp.co.gaban.chat_spring.domain.model.validator.EqualToValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by takeuchidaiki on 2019/04/13
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EqualToValidator.class})
@ReportAsSingleViolation
public @interface EqualTo {
    String message() default "{not equal}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String firstField();
    String secondField();

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        EqualTo[] value();
    }
}

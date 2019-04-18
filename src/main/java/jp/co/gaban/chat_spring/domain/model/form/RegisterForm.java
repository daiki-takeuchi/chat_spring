package jp.co.gaban.chat_spring.domain.model.form;

import jp.co.gaban.chat_spring.annotation.EqualTo;
import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
@EqualTo(firstField = "password", secondField = "passwordConfirmation", message = "{PasswordUnMach}")
public class RegisterForm {
    @NotBlank(message = "{userName}{NotBlank}")
    private String userName;

    @NotBlank(message = "{mail}{NotBlank}")
    @Email
    @UniqueEmail(message = "{UniqueEmail}")
    private String mail;

    @NotBlank(message = "{password}{NotBlank}")
    private String password;

    @NotBlank(message = "{passwordConfirmation}{NotBlank}")
    private String passwordConfirmation;
}

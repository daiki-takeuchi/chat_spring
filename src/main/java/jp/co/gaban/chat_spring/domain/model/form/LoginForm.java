package jp.co.gaban.chat_spring.domain.model.form;

import jp.co.gaban.chat_spring.annotation.PasswordMatch;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
@PasswordMatch(mail = "mail", password = "password", message = "{PasswordMatch}")
public class LoginForm {
    @NotBlank(message = "{mail}{NotBlank}")
    @Email
    private String mail;

    @NotBlank(message = "{password}{NotBlank}")
    private String password;
}

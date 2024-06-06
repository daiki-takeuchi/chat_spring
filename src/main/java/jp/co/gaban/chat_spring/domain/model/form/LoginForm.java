package jp.co.gaban.chat_spring.domain.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jp.co.gaban.chat_spring.annotation.PasswordMatch;
import lombok.Data;

/**
 * Created by takeuchidaiki on 2024/06/03
 */
@Data
@PasswordMatch(mail = "mail", password = "password")
public class LoginForm {

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "LoginForm(mail=" + this.getMail() + ", password=******)";
    }
}

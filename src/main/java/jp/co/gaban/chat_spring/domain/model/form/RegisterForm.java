package jp.co.gaban.chat_spring.domain.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jp.co.gaban.chat_spring.annotation.EqualTo;
import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import lombok.Data;


/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
@EqualTo(firstField = "password", secondField = "passwordConfirmation", message = "{PasswordUnMach}")
public class RegisterForm {
    @NotBlank
    private String userName;

    @NotBlank
    @Email
    @UniqueEmail
    private String mail;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    @Override
    public String toString() {
        return "RegisterForm(userName=" + this.userName + ", mail=" + this.mail + ", password=******, passwordConfirmation=******)";
    }
}

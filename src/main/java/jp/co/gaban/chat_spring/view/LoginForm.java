package jp.co.gaban.chat_spring.view;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
public class LoginForm {
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String password;
}

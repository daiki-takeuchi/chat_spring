package jp.co.gaban.chat_spring.domain.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import jp.co.gaban.chat_spring.domain.model.User;
import lombok.Data;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
public class ProfileWizardForm {
    @NotBlank
    private String userName;

    @NotBlank
    @Email
    @UniqueEmail
    private String mail;

    private String[] job;

    private String selfIntroduction;

    public void setUser(User user) {
        String job = user.getJob();
        this.userName = user.getUserName();
        this.mail = user.getMail();
        this.job = job != null? job.split(" / ", 0): new String[0];
        this.selfIntroduction = user.getSelfIntroduction();
    }

    public final static Map<String, String> JOB_ITEMS =
            Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    put("pencil", "Designer");
                    put("terminal", "Coder");
                    put("laptop", "Developer");
                }
            });
}

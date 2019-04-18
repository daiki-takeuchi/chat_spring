package jp.co.gaban.chat_spring.domain.model.form;

import jp.co.gaban.chat_spring.annotation.UniqueEmail;
import jp.co.gaban.chat_spring.domain.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
public class ProfileWizardForm {
    @NotBlank(message = "{userName}{NotBlank}")
    private String userName;

    @NotBlank(message = "{mail}{NotBlank}")
    @Email
    @UniqueEmail(message = "{UniqueEmail}")
    private String mail;

    private String[] job;

    private String self_introduction;

    public void setUser(User user) {
        String job = user.getJob();
        this.userName = user.getUserName();
        this.mail = user.getMail();
        this.job = job != null? job.split(" / ", 0): new String[0];
        this.self_introduction = user.getSelf_introduction();
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

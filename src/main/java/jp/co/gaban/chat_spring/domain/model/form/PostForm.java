package jp.co.gaban.chat_spring.domain.model.form;

import jp.co.gaban.chat_spring.domain.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Data
public class PostForm {
    @NotBlank
    @Size(min = 1, max = 128)
    private String content;
}

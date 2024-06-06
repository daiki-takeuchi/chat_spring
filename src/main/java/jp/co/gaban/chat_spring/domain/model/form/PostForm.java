package jp.co.gaban.chat_spring.domain.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by takeuchidaiki on 2024/06/04
 */
@Data
public class PostForm {
    @NotBlank
    @Size(min = 1, max = 128)
    private String content;
}

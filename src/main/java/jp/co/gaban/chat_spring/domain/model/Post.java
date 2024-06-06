package jp.co.gaban.chat_spring.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by takeuchidaiki on 2024/06/03
 */
@Data
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="content")
    private String content;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="created_at", nullable=false)
    private Date createdAt;

    @NotNull
    @Column(name="created_user", nullable = false)
    private String createdUser;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="updated_at", nullable=false)
    private Date updatedAt;

    @NotNull
    @Column(name="updated_user", nullable = false)
    private String updatedUser;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable = false, insertable=false, updatable=false, name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        String createdUser = getLoginUserName();
        this.createdUser = createdUser;
        this.createdAt = new Date();
        this.updatedUser = createdUser;
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedUser = getLoginUserName();
        this.updatedAt = new Date();
    }

    private String getLoginUserName() {
        User sessUser = (User) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                .getAttribute("user", RequestAttributes.SCOPE_SESSION);
        assert sessUser != null;
        return sessUser.getUserName();
    }
}

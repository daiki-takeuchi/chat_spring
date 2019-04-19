package jp.co.gaban.chat_spring.domain.model;

import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by DaikiTakeuchi on 2019/04/05.
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

    @Override
    public String toString() {
        return "<Post:" +
                "'id='" + id +
                "', userId='" + userId +
                "', content='" + content +
                "', user='" + user.toString() +
                "', createdAt='" + createdAt.toString() +
                "', createdUser='" + createdUser +
                "', updatedAt='" + updatedAt.toString() +
                "', updatedUser='" + updatedUser +
                "'>";
    }
}

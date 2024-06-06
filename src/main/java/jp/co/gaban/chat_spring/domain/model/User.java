package jp.co.gaban.chat_spring.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jp.co.gaban.chat_spring.domain.security.MyBCryptPasswordEncoder;
import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by takeuchidaiki on 2024/06/01
 */
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="mail", unique = true)
    private String mail;

    @Column(name="password")
    private String password;

    @Column(name="job")
    private String job;

    @Column(name="self_introduction", length = 2048)
    private String selfIntroduction;

    @NotNull
    @Column(name="created_at", nullable=false)
    private Date createdAt;

    @NotNull
    @Column(name="created_user", nullable = false)
    private String createdUser;

    @NotNull
    @Column(name="updated_at", nullable=false)
    private Date updatedAt;

    @NotNull
    @Column(name="updated_user", nullable = false)
    private String updatedUser;

    @OneToMany(mappedBy = "follower")
    private List<Following> follower;

    @OneToMany(mappedBy = "following")
    private List<Following> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public boolean isFollowed(Long userId) {
        for (Following following : this.following) {
            if(following.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean canLogin(String password) {
        MyBCryptPasswordEncoder passwordEncoder
                = new MyBCryptPasswordEncoder(MyBCryptPasswordEncoder.BCryptVersion.$2B,12);
        return passwordEncoder.matches(password, this.password);
    }

    public void setPassword(String rowPassword) {
        MyBCryptPasswordEncoder passwordEncoder
                = new MyBCryptPasswordEncoder(MyBCryptPasswordEncoder.BCryptVersion.$2B,12);
        this.password = passwordEncoder.encode(rowPassword);
    }

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
        if(sessUser != null) {
            return sessUser.getUserName();
        }
        return this.userName;
    }
}

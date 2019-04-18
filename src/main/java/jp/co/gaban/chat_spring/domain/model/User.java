package jp.co.gaban.chat_spring.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jp.co.gaban.chat_spring.domain.security.MyBCryptPasswordEncoder;
import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by DaikiTakeuchi on 2019/04/05.
 */
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @Column(name="self_introduction")
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
        System.out.println("following:" + this.following);
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

    @Override
    public String toString() {
        return "<User:" +
                "'id='" + id +
                "', userName='" + userName +
                "', mail='" + mail +
                "', password='" + password +
                "', job='" + job +
                "', selfIntroduction='" + selfIntroduction +
                "', createdAt='" + createdAt.toString() +
                "', createdUser='" + createdUser +
                "', updatedAt='" + updatedAt.toString() +
                "', updatedUser='" + updatedUser +
                "'>";
    }
}

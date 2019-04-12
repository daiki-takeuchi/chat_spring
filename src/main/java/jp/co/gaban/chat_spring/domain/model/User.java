package jp.co.gaban.chat_spring.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jp.co.gaban.chat_spring.domain.security.MyBCryptPasswordEncoder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private String user_name;

    @Column(name="mail", unique = true)
    private String mail;

    @Column(name="password")
    private String password;

    @Column(name="job")
    private String job;

    @Column(name="self_introduction")
    private String self_introduction;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="created_at", nullable=false)
    private Date created_at;

    @NotNull
    @Column(name="created_user", nullable = false)
    private String created_user;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="updated_at", nullable=false)
    private Date updated_at;

    @NotNull
    @Column(name="updated_user", nullable = false)
    private String updated_user;

    @OneToMany(mappedBy = "follower")
    private List<Following> follower;

    @OneToMany(mappedBy = "following")
    private List<Following> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public boolean isFollowed(Long userId) {
        for (Following follower : this.follower) {
            if(follower.getUser_id().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean canLogin(String password) {
        PasswordEncoder passwordEncoder = new MyBCryptPasswordEncoder(MyBCryptPasswordEncoder.BCryptVersion.$2B,12);
        if(passwordEncoder.matches(password, this.password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "<User:" +
                "'id='" + id +
                "', user_name='" + user_name +
                "', mail='" + mail +
                "', password='" + password +
                "', job='" + job +
                "', self_introduction='" + self_introduction +
                "', created_at='" + created_at.toString() +
                "', created_user='" + created_user +
                "', updated_at='" + updated_at.toString() +
                "', updated_user='" + updated_user +
                "'>";
    }
}

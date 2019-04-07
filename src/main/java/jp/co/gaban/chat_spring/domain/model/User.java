package jp.co.gaban.chat_spring.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

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
}

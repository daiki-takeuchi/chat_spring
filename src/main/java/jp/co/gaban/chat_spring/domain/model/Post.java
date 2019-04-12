package jp.co.gaban.chat_spring.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private Long user_id;

    @Column(name="content")
    private String content;

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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable = false, insertable=false, updatable=false, name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "<Post:" +
                "'id='" + id +
                "', user_id='" + user_id +
                "', content='" + content +
                "', user='" + user.toString() +
                "', created_at='" + created_at.toString() +
                "', created_user='" + created_user +
                "', updated_at='" + updated_at.toString() +
                "', updated_user='" + updated_user +
                "'>";
    }
}

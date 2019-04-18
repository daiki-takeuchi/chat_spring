package jp.co.gaban.chat_spring.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by DaikiTakeuchi on 2019/04/05.
 */
@Data
@Entity
@Table(name = "following")
public class Following implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="following_id", nullable = false)
    private Long followingId;

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
    private User follower;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable = false, insertable=false, updatable=false, name = "following_id")
    private User following;

    @Override
    public String toString() {
        return "<Following:" +
                "'id='" + id +
                "', userId='" + userId +
                "', followingId='" + followingId +
                "', follower='" + follower.toString() +
                "', following='" + following.toString() +
                "', createdAt='" + createdAt.toString() +
                "', createdUser='" + createdUser +
                "', updatedAt='" + updatedAt.toString() +
                "', updatedUser='" + updatedUser +
                "'>";
    }
}

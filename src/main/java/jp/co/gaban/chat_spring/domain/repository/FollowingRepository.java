package jp.co.gaban.chat_spring.domain.repository;

import jp.co.gaban.chat_spring.domain.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by takeuchidaiki on 2024/06/04
 */
@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

    Following findByUserIdAndFollowingId(Long userId, Long followingId);
}

package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Following;
import jp.co.gaban.chat_spring.domain.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Service
@EnableTransactionManagement
public class FollowingService implements Pagination {

    private final FollowingRepository followingRepository;

    @Autowired
    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }

    public Following findByUserIdAndFollowingId(Long userId, Long followingId) {
        return followingRepository.findByUserIdAndFollowingId(userId, followingId);
    }

    public Following save(Following following) {
        return followingRepository.save(following);
    }

    public void delete(Following following) {
        followingRepository.delete(following);
    }

}

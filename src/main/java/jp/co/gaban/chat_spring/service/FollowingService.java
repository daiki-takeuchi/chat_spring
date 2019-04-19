package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Following;
import jp.co.gaban.chat_spring.domain.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Service
@EnableTransactionManagement
public class FollowingService implements Pagination {

    @Autowired
    FollowingRepository followingRepository;

    public Following findByUserIdAndFollowingId(Long userId, Long followingId) {
        return followingRepository.findByUserIdAndFollowingId(userId, followingId);
    }

    public void save(Following following) {
        followingRepository.save(following);
    }

    public void delete(Following following) {
        followingRepository.delete(following);
    }

}

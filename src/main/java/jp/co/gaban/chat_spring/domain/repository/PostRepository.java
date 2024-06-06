package jp.co.gaban.chat_spring.domain.repository;

import jp.co.gaban.chat_spring.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by takeuchidaiki on 2024/06/03
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long userId, Pageable pager);
}

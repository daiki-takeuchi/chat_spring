package jp.co.gaban.chat_spring.domain.repository;

import jp.co.gaban.chat_spring.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

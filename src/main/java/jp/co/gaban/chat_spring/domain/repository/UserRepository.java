package jp.co.gaban.chat_spring.domain.repository;

import jp.co.gaban.chat_spring.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);

    Page<User> findByUserNameContaining(String userName, Pageable pager);
}

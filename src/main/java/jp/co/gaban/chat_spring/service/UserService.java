package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Service
@EnableTransactionManagement
public class UserService implements Pagination {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Service
@EnableTransactionManagement
public class UserService implements Pagination {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> findAll(int page, int size, String sort) {
        Pageable pager = PageRequest.of(currentPage(page), size, Sort.Direction.ASC, sort);
        return userRepository.findAll(pager);
    }

    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseGet(User::new);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

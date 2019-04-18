package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Created by DaikiTakeuchi on 2019/04/06.
 */
@Service
@EnableTransactionManagement
public class PostService implements Pagination {

    private static final int PAGE_SIZE = 10;

    @Autowired
    PostRepository postRepository;

    public Iterable<Post> findAll(int page, String sort) {
        Pageable pager = PageRequest.of(currentPage(page), PAGE_SIZE, Sort.Direction.ASC, sort);
        return postRepository.findAll(pager);
    }

    public Iterable<Post> findByUserId(Long userId, int page, String sort) {
        Pageable pager = PageRequest.of(currentPage(page), PAGE_SIZE, Sort.Direction.ASC, sort);
        return postRepository.findByUserId(userId, pager);
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
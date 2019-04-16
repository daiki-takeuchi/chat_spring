package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.repository.PostRepository;
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
public class PostService implements Pagination {

    @Autowired
    PostRepository postRepository;

    public Iterable<Post> findAll(int page, int size, String sort) {
        Pageable pager = PageRequest.of(currentPage(page), size, Sort.Direction.ASC, sort);
        return postRepository.findAll(pager);
    }

    public Post findById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElseGet(Post::new);
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}

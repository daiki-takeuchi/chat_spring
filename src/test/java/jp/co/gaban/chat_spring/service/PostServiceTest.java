package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Post;
import jp.co.gaban.chat_spring.domain.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by DaikiTakeuchi on 2019/05/02.
 */
@RunWith(SpringRunner.class)
public class PostServiceTest {

    private final static Long TEST_ID = 1L;
    private final static Long TEST_USER_ID = 2L;
    private final static String TEST_CONTENT = "test_post_content";

    private Post testPost;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Before
    public void setup() {
        this.testPost = new Post();
        this.testPost.setId(TEST_ID);
        this.testPost.setUserId(TEST_USER_ID);
        this.testPost.setContent(TEST_CONTENT);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findAll() {

        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "id");

        List<Post> postList = new ArrayList<>();
        postList.add(testPost);
        Page<Post> postPage = new PageImpl(postList);

        when(this.postRepository.findAll(page)).thenReturn(postPage);
        Iterable<Post> posts = this.postService.findAll(1,"id");

        for (Post post: posts) {
            assertThat(post.getId()).isEqualTo(TEST_ID);
            assertThat(post.getContent()).isEqualTo(TEST_CONTENT);
        }
        verify(this.postRepository, times(1)).findAll(page);
    }

    @Test
    public void findById() {

        when(this.postRepository.findById(TEST_ID)).thenReturn(Optional.ofNullable(this.testPost));

        Post actual = this.postService.findById(TEST_ID);

        assertThat(actual).isEqualTo(this.testPost);

        verify(this.postRepository, times(1)).findById(TEST_ID);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findByUserId() {

        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "id");

        List<Post> postList = new ArrayList<>();
        postList.add(testPost);
        Page<Post> postPage = new PageImpl(postList);

        when(this.postRepository.findByUserId(TEST_USER_ID, page)).thenReturn(postPage);
        Iterable<Post> posts = this.postService.findByUserId(TEST_USER_ID,1,"id");

        for (Post post: posts) {
            assertThat(post.getId()).isEqualTo(TEST_ID);
            assertThat(post.getUserId()).isEqualTo(TEST_USER_ID);
            assertThat(post.getContent()).isEqualTo(TEST_CONTENT);
        }
        verify(this.postRepository, times(1)).findByUserId(TEST_USER_ID, page);
    }

    @Test
    public void save() {

        when(this.postRepository.save(this.testPost)).thenReturn(this.testPost);

        Post actual = this.postService.save(this.testPost);

        assertThat(actual).isEqualTo(this.testPost);

        verify(this.postRepository, times(1)).save(this.testPost);
    }

    @Test
    public void delete() {

        this.postService.delete(this.testPost);

        verify(this.postRepository, times(1)).delete(this.testPost);
    }
}

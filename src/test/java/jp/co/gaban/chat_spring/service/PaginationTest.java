package jp.co.gaban.chat_spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by DaikiTakeuchi on 2019/05/02.
 */
@RunWith(SpringRunner.class)
public class PaginationTest {

    private final static int TEST_MAX = 15;
    private final static int TEST_PAGE_NO = 1;
    private final static int TEST_PAGE_SIZE = 10;

    @InjectMocks
    private PostService postService;

    @Test
    public void currentPage() {
        int expected = 0;
        int actual = this.postService.currentPage(TEST_PAGE_NO);
        assertThat(actual).isEqualTo(expected);

        actual = this.postService.currentPage(0);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void maxPage() {
        int expected = 2;
        int actual = this.postService.maxPage(TEST_MAX, TEST_PAGE_SIZE);
        assertThat(actual).isEqualTo(expected);

        actual = this.postService.maxPage(20, TEST_PAGE_SIZE);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calcPage() {
        PageBean actual = this.postService.calcPage(TEST_MAX, TEST_PAGE_NO, TEST_PAGE_SIZE);
        assertThat(actual.getTotalCount()).isEqualTo(TEST_MAX);
        assertThat(actual.getCurrentPage()).isEqualTo(TEST_PAGE_NO);
        assertThat(actual.getMaxPage()).isEqualTo(this.postService.maxPage(TEST_MAX, TEST_PAGE_SIZE));
    }
}

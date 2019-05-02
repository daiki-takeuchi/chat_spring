package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.Following;
import jp.co.gaban.chat_spring.domain.repository.FollowingRepository;
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
public class FollowingServiceTest {

    private final static Long TEST_ID = 1L;
    private final static Long TEST_USER_ID = 2L;
    private final static Long TEST_FOLLOWING_ID = 3L;

    private Following testFollowing;

    @Mock
    private FollowingRepository followingRepository;

    @InjectMocks
    private FollowingService followingService;

    @Before
    public void setup() {
        this.testFollowing = new Following();
        this.testFollowing.setId(TEST_ID);
        this.testFollowing.setUserId(TEST_USER_ID);
        this.testFollowing.setFollowingId(TEST_FOLLOWING_ID);
    }

    @Test
    public void findByUserIdAndFollowingId() {

        when(this.followingRepository.findByUserIdAndFollowingId(TEST_USER_ID, TEST_FOLLOWING_ID))
                .thenReturn(this.testFollowing);

        Following actual = this.followingService.findByUserIdAndFollowingId(TEST_USER_ID, TEST_FOLLOWING_ID);

        assertThat(actual).isEqualTo(this.testFollowing);

        verify(this.followingRepository, times(1))
                .findByUserIdAndFollowingId(TEST_USER_ID, TEST_FOLLOWING_ID);
    }

    @Test
    public void save() {

        when(this.followingRepository.save(this.testFollowing)).thenReturn(this.testFollowing);

        Following actual = this.followingService.save(this.testFollowing);

        assertThat(actual).isEqualTo(this.testFollowing);

        verify(this.followingRepository, times(1)).save(this.testFollowing);
    }

    @Test
    public void delete() {

        this.followingService.delete(this.testFollowing);

        verify(this.followingRepository, times(1)).delete(this.testFollowing);
    }
}

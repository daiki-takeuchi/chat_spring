package jp.co.gaban.chat_spring.domain.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by takeuchidaiki on 2019/05/03
 */
public class UserTest {

    private final static Long TEST_USER1_ID = 1L;
    private final static String TEST_USER1_NAME = "test_user1_name";
    private final static String TEST_USER1_MAIL = "test1@test.com";
    private final static String TEST_USER1_PASSWORD = "test1";

    private final static Long TEST_USER2_ID = 2L;
    private final static String TEST_USER2_NAME = "test_user2_name";
    private final static String TEST_USER2_MAIL = "test2@test.com";
    private final static String TEST_USER2_PASSWORD = "test2";

    private final static Long TEST_ID = 1L;
    private List<Following> followingList = new ArrayList<>();

    private User testUser1;

    @Mock
    private RequestAttributes attrs;

    @Before
    public void setup() {

        this.testUser1 = new User();
        this.testUser1.setId(TEST_USER1_ID);
        this.testUser1.setUserName(TEST_USER1_NAME);
        this.testUser1.setMail(TEST_USER1_MAIL);
        this.testUser1.setPassword(TEST_USER1_PASSWORD);

        MockitoAnnotations.initMocks(this);
        RequestContextHolder.setRequestAttributes(attrs);
        // セッションが存在しない場合のカバレッジを上げるため、モックを登録する前にprePersistを実施
        this.testUser1.prePersist();

        when(attrs.getAttribute("user", RequestAttributes.SCOPE_SESSION)).thenReturn(this.testUser1);

        User testUser2 = new User();
        testUser2.setId(TEST_USER2_ID);
        testUser2.setUserName(TEST_USER2_NAME);
        testUser2.setMail(TEST_USER2_MAIL);
        testUser2.setPassword(TEST_USER2_PASSWORD);
        testUser2.prePersist();

        Following following = new Following();
        following.setId(TEST_ID);
        following.setUserId(TEST_USER1_ID);
        following.setFollowingId(TEST_USER2_ID);
        following.setFollower(this.testUser1);
        following.setFollowing(testUser2);
        following.prePersist();

        followingList.add(following);
        this.testUser1.setFollowing(followingList);
    }

    @Test
    public void isFollowed() {
        // フォローしている場合
        boolean actual = this.testUser1.isFollowed(TEST_USER1_ID);
        assertThat(actual).isEqualTo(true);

        // フォローしてない場合
        actual = this.testUser1.isFollowed(TEST_USER2_ID);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void canLogin() {
        // ログインできる場合
        boolean actual = this.testUser1.canLogin(TEST_USER1_PASSWORD);
        assertThat(actual).isEqualTo(true);

        // ログインできない場合
        actual = this.testUser1.canLogin(TEST_USER2_PASSWORD);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void prePersist() {
        User user = new User();
        user.prePersist();

        Date creationAt = user.getCreatedAt();
        Date updatedAt = user.getUpdatedAt();
        String createdUser = user.getCreatedUser();
        String updatedUser = user.getUpdatedUser();

        assertThat(creationAt).isNotNull();
        assertThat(updatedAt).isNotNull();
        assertThat(createdUser).isEqualTo(TEST_USER1_NAME);
        assertThat(updatedUser).isEqualTo(TEST_USER1_NAME);
        assertThat(updatedAt).isEqualTo(creationAt);
    }

    @Test
    public void preUpdate() {
        User user = new User();
        user.prePersist();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //Back to work
        }
        user.preUpdate();

        Date creationAt = user.getCreatedAt();
        Date updatedAt = user.getUpdatedAt();

        assertThat(creationAt).isNotNull();
        assertThat(updatedAt).isNotNull();
        assertThat(updatedAt).isAfter(creationAt);
    }
}

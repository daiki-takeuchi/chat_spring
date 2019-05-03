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
public class FollowingTest {

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

    @Mock
    private RequestAttributes attrs;

    @Before
    public void setup() {

        User testUser1 = new User();
        testUser1.setId(TEST_USER1_ID);
        testUser1.setUserName(TEST_USER1_NAME);
        testUser1.setMail(TEST_USER1_MAIL);
        testUser1.setPassword(TEST_USER1_PASSWORD);

        MockitoAnnotations.initMocks(this);
        RequestContextHolder.setRequestAttributes(attrs);
        when(attrs.getAttribute("user", RequestAttributes.SCOPE_SESSION)).thenReturn(testUser1);

        testUser1.prePersist();

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
        following.setFollower(testUser1);
        following.setFollowing(testUser2);
        following.prePersist();

        followingList.add(following);
        testUser1.setFollowing(followingList);
    }

    @Test
    public void prePersist() {
        Following following = new Following();
        following.prePersist();

        Date creationAt = following.getCreatedAt();
        Date updatedAt = following.getUpdatedAt();
        String createdUser = following.getCreatedUser();
        String updatedUser = following.getUpdatedUser();

        assertThat(creationAt).isNotNull();
        assertThat(updatedAt).isNotNull();
        assertThat(createdUser).isEqualTo(TEST_USER1_NAME);
        assertThat(updatedUser).isEqualTo(TEST_USER1_NAME);
        assertThat(updatedAt).isEqualTo(creationAt);
    }

    @Test
    public void preUpdate() {
        Following following = new Following();
        following.prePersist();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //Back to work
        }
        following.preUpdate();

        Date creationAt = following.getCreatedAt();
        Date updatedAt = following.getUpdatedAt();

        assertThat(creationAt).isNotNull();
        assertThat(updatedAt).isNotNull();
        assertThat(updatedAt).isAfter(creationAt);
    }

    @Test(expected = AssertionError.class)
    public void assertionError() {
        when(attrs.getAttribute("user", RequestAttributes.SCOPE_SESSION)).thenReturn(null);

        Following following = new Following();
        following.prePersist();
    }
}

package jp.co.gaban.chat_spring.service;

import jp.co.gaban.chat_spring.domain.model.User;
import jp.co.gaban.chat_spring.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by DaikiTakeuchi on 2019/05/02.
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {

    private final static Long TEST_ID = 1L;
    private final static String TEST_USER_NAME = "test_user_name";
    private final static String TEST_USER_MAIL = "test@test.com";

    private User testUser;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        this.testUser = new User();
        this.testUser.setId(TEST_ID);
        this.testUser.setUserName(TEST_USER_NAME);
        this.testUser.setMail(TEST_USER_MAIL);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findByUserName() {

        Pageable page = PageRequest.of(0,10, Sort.Direction.ASC, "id");

        List<User> userList = new ArrayList<>();
        userList.add(testUser);
        Page<User> userPage = new PageImpl(userList);

        when(this.userRepository.findByUserNameContaining(TEST_USER_NAME, page)).thenReturn(userPage);
        when(this.userRepository.findAll(page)).thenReturn(userPage);
        Iterable<User> users = this.userService.findByUserName(TEST_USER_NAME, 1, "id");

        for (User user: users) {
            assertThat(user.getId()).isEqualTo(TEST_ID);
            assertThat(user.getUserName()).isEqualTo(TEST_USER_NAME);
            assertThat(user.getMail()).isEqualTo(TEST_USER_MAIL);
        }
        verify(this.userRepository, times(1)).findByUserNameContaining(TEST_USER_NAME, page);

        // 検索ワードにuserNameがない場合は全件検索になることを確認
        users = this.userService.findByUserName("", 1, "id");
        for (User user: users) {
            assertThat(user.getId()).isEqualTo(TEST_ID);
            assertThat(user.getUserName()).isEqualTo(TEST_USER_NAME);
            assertThat(user.getMail()).isEqualTo(TEST_USER_MAIL);
        }
        verify(this.userRepository, times(1)).findAll(page);
    }

    @Test
    public void findByMail() {

        when(this.userRepository.findByMail(TEST_USER_MAIL)).thenReturn(this.testUser);

        User actual = this.userService.findByMail(TEST_USER_MAIL);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userRepository, times(1)).findByMail(TEST_USER_MAIL);
    }

    @Test
    public void findById() {

        when(this.userRepository.findById(TEST_ID)).thenReturn(Optional.ofNullable(this.testUser));

        User actual = this.userService.findById(TEST_ID);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userRepository, times(1)).findById(TEST_ID);
    }

    @Test
    public void save() {

        when(this.userRepository.save(this.testUser)).thenReturn(this.testUser);

        User actual = this.userService.save(this.testUser);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userRepository, times(1)).save(this.testUser);
    }
}

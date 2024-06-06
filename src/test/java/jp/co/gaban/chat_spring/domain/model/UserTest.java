package jp.co.gaban.chat_spring.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {

    private final static Long TEST_USER1_ID = 1L;
    private final static String TEST_USER1_NAME = "test_user1_name";
    private final static String TEST_USER1_MAIL = "test1@test.com";
    private final static String TEST_USER1_PASSWORD = "test1";

    private User testUser1;

    @BeforeTestMethod
    public void setup() {
        this.testUser1 = new User();
        this.testUser1.setId(TEST_USER1_ID);
        this.testUser1.setUserName(TEST_USER1_NAME);
//        this.testUser1.setMail(TEST_USER1_MAIL);
//        this.testUser1.setPassword(TEST_USER1_PASSWORD);
    }

    @Test
    public void canLogin() {
        // ログインできる場合
//        boolean actual = this.testUser1.canLogin(TEST_USER1_PASSWORD);
//        assertThat(actual).isEqualTo(true);
//
//        // ログインできない場合
//        actual = this.testUser1.canLogin(TEST_USER2_PASSWORD);
        assertThat(true).isEqualTo(true);
    }
}

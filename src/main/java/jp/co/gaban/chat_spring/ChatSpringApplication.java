package jp.co.gaban.chat_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {"jp.co.gaban.chat_spring.domain"})
@EnableJpaRepositories(basePackages = {"jp.co.gaban.chat_spring.domain"})
@EnableTransactionManagement(proxyTargetClass = true)
public class ChatSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatSpringApplication.class, args);
    }
}

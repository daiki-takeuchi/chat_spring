package jp.co.gaban.chat_spring.config;

import jp.co.gaban.chat_spring.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by takeuchidaiki on 2019/04/08
 */
@Configuration
public class WebApplicationConfiguration {
    @Bean
    public MappedInterceptor interceptor() {
        return new MappedInterceptor(new String[]{"/**"}, new Interceptor());
    }
}

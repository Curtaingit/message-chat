package com.curtain.messagechat;

import cn.wzvtcsoft.bosdomain.persist.BosJpaRepositoryImpl;
import com.curtain.messagechat.actuator.MyEndPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Curtain
 * @date 2018/11/19 10:43
 */

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryBaseClass = BosJpaRepositoryImpl.class)
//spring-boot-admin 修改了 starter-web 的默认设置, classpath:/static 是没有作为静态资源导出的。
//@EnableAdminServer
public class MessageChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageChatApplication.class, args);
	}

	@Configuration
	static class MyEndpointConfiguration{
		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnEnabledEndpoint
		public MyEndPoint myEndPoint(){
			return new MyEndPoint();
		}
	}
}

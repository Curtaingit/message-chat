package com.curtain.messagechat;

import cn.wzvtcsoft.bosdomain.persist.BosJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Curtain
 * @date 2018/11/19 10:43
 */

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryBaseClass = BosJpaRepositoryImpl.class)
public class MessageChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageChatApplication.class, args);
	}
}

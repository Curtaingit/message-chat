package com.curtain.messagechat.repository;

import com.curtain.messagechat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Curtain
 * @date 2018/11/19 10:43
 */
public interface MessageRepository extends JpaRepository<Message,String> {
}

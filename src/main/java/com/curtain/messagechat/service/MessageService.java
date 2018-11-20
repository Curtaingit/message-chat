package com.curtain.messagechat.service;

import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.domain.User;

/**
 * @author Curtain
 * @date 2018/11/19 17:05
 */
public interface MessageService {

    /**
     * 发消息
     * @param message
     * @return
     */

    Message sendMessage(Message message);

    /**
     * 确认消息已读
     * @param user
     * @param message
     */
    Message confirm(User user,Message message);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Message findOne(String id);
}

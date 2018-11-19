package com.curtain.messagechat.service.impl;

import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.repository.MessageRepository;
import com.curtain.messagechat.service.MessageService;
import com.curtain.messagechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Curtain
 * @date 2018/11/19 17:14
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findOne(String id) {
        Optional<Message> message = messageRepository.findById(id);
        if (!message.isPresent()){
            throw new RuntimeException("信息未找到");
        }
        return null;
    }

    @Override
    public void confirm(User user, Message message) {
        message = messageRepository.findById(message.getId()).get();
    }
}

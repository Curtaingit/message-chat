package com.curtain.messagechat.service.impl;

import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.domain.Receiver;
import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.enums.ResultExceptionEnum;
import com.curtain.messagechat.exception.MessageChatException;
import com.curtain.messagechat.repository.MessageRepository;
import com.curtain.messagechat.service.MessageService;
import com.curtain.messagechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
    @PreAuthorize("authenticated")
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findOne(String id) {
        Optional<Message> message = messageRepository.findById(id);
        if (!message.isPresent()){
            throw new MessageChatException(ResultExceptionEnum.MESSAGE_NOT_EXIST);
        }
        return message.get();
    }

    @Override
    @PreAuthorize("authenticated")
    public Message confirm(User user, Message message) {
        message = findOne(message.getId());
        user = userService.findOne(user.getId());
        Set<Receiver> receivers = message.getReceivers();
        for (Receiver receiver :receivers){
            if (receiver.getUser().getId().equals(user.getId())){
                receiver.setReaded();
                return messageRepository.save(message);
            }
        }
        throw new MessageChatException(ResultExceptionEnum.MESSAGE_READER_FAIL);
    }
}

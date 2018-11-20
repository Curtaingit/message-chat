package com.curtain.messagechat.controller;

import cn.wzvtcsoft.validator.anntations.DomainRule;
import cn.wzvtcsoft.validator.anntations.MutationValidated;
import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.service.MessageService;
import graphql.annotation.GraphqlController;
import graphql.annotation.GraphqlMutation;
import graphql.annotation.SchemaDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Curtain
 * @date 2018/11/19 17:04
 */
@GraphqlController("message")
@RestController
@MutationValidated
public class MessageController {
    @Autowired
    private MessageService messageService;

    @SchemaDocumentation("信息确认已读")
    @GraphqlMutation("/confirm")
    public Message confirm(User user, Message message) {
        return messageService.confirm(user, message);
    }

    @SchemaDocumentation("发送信息")
    @GraphqlMutation(path = "/sendMessage")
    public Message sendMessage(@DomainRule("content && title && sendUser && receivers") Message message) {
        return messageService.sendMessage(message);
    }
}

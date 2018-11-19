package com.curtain.messagechat.exception;

import graphql.GraphQLException;
import graphql.errors.BusinessException;

/**
 * @author Curtain
 * @date 2018/11/19 17:25
 */
public class MessageChatException extends BusinessException {



    @Override
    public int getStatus() {
        return super.getStatus();
    }

    public MessageChatException(String message, int status) {
        super(message, status);
    }

    public MessageChatException(String message) {
        super(message);
    }
}

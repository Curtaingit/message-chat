package com.curtain.messagechat.exception;

import com.curtain.messagechat.enums.ResultExceptionEnum;
import graphql.GraphQLException;
import graphql.errors.BusinessException;

/**
 * @author Curtain
 * @date 2018/11/19 17:25
 */
public class MessageChatException extends BusinessException {

    private ResultExceptionEnum resultExceptionEnum;

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    public MessageChatException(ResultExceptionEnum resultExceptionEnum){
        super(resultExceptionEnum.getMessage(),resultExceptionEnum.getCode());
        this.resultExceptionEnum =resultExceptionEnum;
    }
}

package com.curtain.messagechat.enums;

import lombok.Getter;

/**
 * @author Curtain
 * @date 2018/11/20 8:25
 */
@Getter
public enum ResultExceptionEnum {
    /*异常枚举 code  message*/

    MESSAGE_NOT_EXIST(10001,"信息未找到"),
    MESSAGE_READER_FAIL(10002,"设置消息已读失败"),

    USER_NOT_EXIST(20001,"用户不存在"),
    USER_PHONE_IS_BIND(20002,"此手机号已经被账号绑定")

    ;

    private String message;
    private int code;

    ResultExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

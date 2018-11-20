package com.curtain.messagechat.enums;

import lombok.Getter;

/**
 * @author Curtain
 * @date 2018/10/22 17:16
 */
@Getter
public enum RoleAuthorityFunctionEnum {
    /*权限集合*/

    //消息管理
    MESSAGE_MANAGEMENT("A1"),
    //用户管理
    USER_MANAGEMENT("B1"),
    //角色管理
    ROLE_MANAGEMENT("C1")

    ;


    private String message;

    RoleAuthorityFunctionEnum(String message) {
        this.message = message;
    }


}

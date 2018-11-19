package com.curtain.messagechat.domain;

import cn.wzvtcsoft.bosdomain.Entry;
import cn.wzvtcsoft.bosdomain.annotations.Bostype;
import graphql.annotation.SchemaDocumentation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Curtain
 * @date 2018/11/19 10:27
 */
@Getter
@Setter
@Entity
@Bostype("R01")
@SchemaDocumentation("信息接收人")
public class Receiver extends Entry{

    @SchemaDocumentation("用户")
    @ManyToOne
    private User user;

    @SchemaDocumentation("是否已读")
    private boolean readed = false;
}

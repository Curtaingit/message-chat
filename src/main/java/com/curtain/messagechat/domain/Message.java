package com.curtain.messagechat.domain;

import cn.wzvtcsoft.bosdomain.BosEntity;
import cn.wzvtcsoft.bosdomain.annotations.Bostype;
import graphql.annotation.SchemaDocumentation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Curtain
 * @date 2018/11/19 10:25
 */

@Getter
@Setter
@Bostype("M01")
@Entity
@SchemaDocumentation("信息")
public class Message extends BosEntity {

    @SchemaDocumentation("信息内容")
    private String content;

    @SchemaDocumentation("信息内容")
    private String title;

    @ManyToOne
    @SchemaDocumentation("用户")
    private User sendUser;

    @SchemaDocumentation("信息接收人列表")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Receiver> receivers;
}

package com.curtain.messagechat.domain;


import cn.wzvtcsoft.bosdomain.BosEntity;
import cn.wzvtcsoft.bosdomain.annotations.Bostype;
import graphql.annotation.SchemaDocumentation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Curtain
 * @date 2018/10/9 14:15
 */

@Entity
@SchemaDocumentation("角色")
@Bostype("A09")
@Getter
@Setter
public class Role extends BosEntity {

    /**
     * 角色
     */
    @SchemaDocumentation("角色名")
    private String name;

    /**
     * 权限集合
     */
    @SchemaDocumentation("权限集合")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PrivilegeItem> privilegeItems = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", privilegeItems=" + privilegeItems +
                '}';
    }
}

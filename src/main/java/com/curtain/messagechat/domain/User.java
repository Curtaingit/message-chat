package com.curtain.messagechat.domain;

import cn.wzvtcsoft.bosdomain.BosEntity;
import cn.wzvtcsoft.bosdomain.annotations.Bostype;
import graphql.annotation.SchemaDocumentation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Curtain
 * @date 2018/11/19 10:25
 */
@Getter
@Setter
@Bostype("U01")
@Entity
@SchemaDocumentation("用户")
public class User extends BosEntity implements UserDetails {


    @SchemaDocumentation("姓名")
    private String nickname;

    @SchemaDocumentation("电话号码")
    @Length(min = 11, max = 11, message = "长度不正确")

    private String phone;

    @SchemaDocumentation("密码")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RoleItem> roleItems = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> collect = roleItems
                .stream()
                .map(RoleItem::getRole)
                .flatMap(role -> role.getPrivilegeItems().stream())
                .map(PrivilegeItem::getPrivilege)
                .map(privilege -> new SimpleGrantedAuthority(privilege.getAuthority()))
                .collect(Collectors.toSet());

        return collect;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

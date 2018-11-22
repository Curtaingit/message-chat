package com.curtain.messagechat.service.impl;

import com.curtain.messagechat.domain.Privilege;
import com.curtain.messagechat.domain.PrivilegeItem;
import com.curtain.messagechat.domain.Role;
import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.repository.RoleRepository;
import com.curtain.messagechat.service.RoleService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Curtain
 * @date 2018/10/23 16:01
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasAuthority('C1')")
    public Role save(Role role) {

        //todo 需要校验constraint 是否符合规则 能否转化成qfilter  失败则抛出异常

        role = roleRepository.save(role);
        privilegeCheck(role);

        return role;
    }

    @Override
    @PreAuthorize("hasAuthority('C1')")
    public Role update(Role role) {
        //todo 需要校验constraint 是否符合规则 能否转化成qfilter  失败则抛出异常
        role = roleRepository.save(role);
        privilegeCheck(role);
        return role;
    }

    /**
     * 验证修改的权限 是否被允许（包含在当前用户中）
     * @param role
     */
    private void privilegeCheck(Role role) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Privilege> principalPrivilege = principal.getRoleItems()
                .stream()
                .flatMap(roleItem -> roleItem.getRole().getPrivilegeItems().stream())
                .map(PrivilegeItem::getPrivilege).collect(Collectors.toSet());

        Set<Privilege> rolePrivilege = role.getPrivilegeItems().stream()
                .map(PrivilegeItem::getPrivilege).collect(Collectors.toSet());

        if (!principalPrivilege.containsAll(rolePrivilege)) {
            throw new AccessDeniedException("角色添加错误，赋予了本身不具备的权限");
        }
    }

}

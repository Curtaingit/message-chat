package com.curtain.messagechat.service.impl;

import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.domain.Privilege;
import com.curtain.messagechat.domain.PrivilegeItem;
import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.enums.ResultExceptionEnum;
import com.curtain.messagechat.exception.MessageChatException;
import com.curtain.messagechat.repository.UserRepository;
import com.curtain.messagechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Curtain
 * @date 2018/11/19 10:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> phone = userRepository.findByPhone(username);
        if (!phone.isPresent()) {
            throw new UsernameNotFoundException("user not exist");
        }
        return phone.get();


    }

    @Override
    public User findOne(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new MessageChatException(ResultExceptionEnum.USER_NOT_EXIST);
        }
        return user.get();
    }

    /**
     * 配置了修改权限  只有用户本身能修改 或者 包含B1(用户管理这个权限)
     * @param user
     * @return
     */
    @Override
    @PreAuthorize("authenticated && (#user.id==authentication.principal.id || hasAuthority('B1'))")
    public User update(@P("user") User user) {
        //todo 只有用户管理员可以修改   或者 自己改自己本身的    password 不能被修改
        //todo  超级管理员admin的权限和角色不允许修改
        privilegeCheck(user);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        //todo 应该只保存  用户注册时应该 处理的数据
        Optional<User> result = userRepository.findByPhone(user.getPhone());
        if (result.isPresent()) {
            throw new MessageChatException(ResultExceptionEnum.USER_PHONE_IS_BIND);
        }
        return userRepository.save(user);
    }

    /**
     * 验证修改的权限 是否被允许（包含在当前用户中）
     *
     * @param user
     */
    private void privilegeCheck(User user) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Privilege> principalPrivilege = principal.getRoleItems()
                .stream()
                .flatMap(roleItem -> roleItem.getRole().getPrivilegeItems().stream())
                .map(PrivilegeItem::getPrivilege).collect(Collectors.toSet());

        Set<Privilege> administerPrivilege = user.getRoleItems()
                .stream()
                .flatMap(roleItem -> roleItem.getRole().getPrivilegeItems().stream())
                .map(PrivilegeItem::getPrivilege).collect(Collectors.toSet());

        if (!principalPrivilege.containsAll(administerPrivilege)) {
            throw new AccessDeniedException("权限添加错误，赋予了本身不具备的权限");
        }
    }
}

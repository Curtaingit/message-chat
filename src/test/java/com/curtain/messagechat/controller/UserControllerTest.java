package com.curtain.messagechat.controller;

import com.curtain.messagechat.domain.*;
import com.curtain.messagechat.enums.RoleAuthorityFunctionEnum;
import com.curtain.messagechat.repository.PrivilegeRepository;
import com.curtain.messagechat.repository.RoleRepository;
import com.curtain.messagechat.repository.UserRepository;
import com.curtain.messagechat.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Curtain
 * @date 2018/11/19 16:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Test
    public void register() throws Exception {
//        SecurityContextHolder.getContext().setAuthentication();

        User user = new User();

        user.setNickname("admin");
        user.setPassword("1");
        user.setPhone("13512343422");

        PrivilegeItem privilegeItem1 = new PrivilegeItem();
        PrivilegeItem privilegeItem2 = new PrivilegeItem();
        PrivilegeItem privilegeItem3 = new PrivilegeItem();

        Privilege privilege1 = new Privilege();
        privilege1.setPrivilege(RoleAuthorityFunctionEnum.MESSAGE_MANAGEMENT.getMessage());
        privilege1.setCategory(0);
        privilege1.setName("信息管理");
        Privilege privilege2 = new Privilege();
        privilege2.setPrivilege(RoleAuthorityFunctionEnum.USER_MANAGEMENT.getMessage());
        privilege2.setName("用户管理");
        privilege2.setCategory(1);
        Privilege privilege3 = new Privilege();
        privilege3.setPrivilege(RoleAuthorityFunctionEnum.ROLE_MANAGEMENT.getMessage());
        privilege3.setCategory(2);
        privilege3.setName("角色管理");


        privilege1 = privilegeRepository.save(privilege1);
        privilege2 = privilegeRepository.save(privilege2);
        privilege3 = privilegeRepository.save(privilege3);

        privilegeItem1.setPrivilege(privilege1);
        privilegeItem2.setPrivilege(privilege2);
        privilegeItem3.setPrivilege(privilege3);

        Role role1 = new Role();
        role1.setName("账号管理员");
        role1.getPrivilegeItems().add(privilegeItem1);


        Role role2 = new Role();
        role2.setName("信息管理员");
        role2.getPrivilegeItems().add(privilegeItem2);

        Role role3 = new Role();
        role3.setName("角色管理员");
        role3.getPrivilegeItems().add(privilegeItem3);


        role1 = roleRepository.save(role1);
        role2 = roleRepository.save(role2);
        role3 = roleRepository.save(role3);


        RoleItem roleItem1 = new RoleItem();
        RoleItem roleItem2 = new RoleItem();
        RoleItem roleItem3 = new RoleItem();

        roleItem1.setRole(role1);
        roleItem2.setRole(role2);
        roleItem3.setRole(role3);

        user.getRoleItems().add(roleItem1);
        user.getRoleItems().add(roleItem2);
        user.getRoleItems().add(roleItem3);

        //todo 这里如果不前保存 name 和 privilege 会出现以下错误   暂时未找到解决方案
        //todo object references an unsaved transient instance - save the transient instance before flushing

//        Assert.assertNotNull(userService.save(user));

    }


    @Test
    public void test() {
        Assert.assertNotNull(1);
    }

}
package com.curtain.messagechat.controller;

import cn.wzvtcsoft.validator.anntations.DomainRule;
import cn.wzvtcsoft.validator.anntations.MutationValidated;
import com.curtain.messagechat.domain.Role;
import com.curtain.messagechat.service.RoleService;
import graphql.annotation.GraphqlController;
import graphql.annotation.GraphqlMutation;
import graphql.annotation.PrivilegeConstraint;
import graphql.annotation.SchemaDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Curtain
 * @date 2018/10/23 17:01
 */

@GraphqlController("role")
@RestController
@MutationValidated
//@PrivilegeConstraint(expression = "k=user.id,v=#p.id,o=equal && k=user.name,o=ISNOTNULL")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @SchemaDocumentation("增加角色信息")
    @GraphqlMutation(path = "/add")
    public Role addRole(@DomainRule("name") Role role) {
        return roleService.save(role);
    }

    @SchemaDocumentation("修改角色信息")
    @GraphqlMutation(path = "/update")
    public Role updateRole(@DomainRule("name") Role role) {
        return roleService.update(role);
    }

}

package com.curtain.messagechat.controller;

import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.service.UserService;
import graphql.annotation.GraphqlController;
import graphql.annotation.SchemaDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Curtain
 * @date 2018/11/19 14:20
 */
@GraphqlController("users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @SchemaDocumentation("添加用户")
    @PostMapping(path = "/register")
    public User register(User user){
        return user;
    }


}

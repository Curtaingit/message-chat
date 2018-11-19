package com.curtain.messagechat.controller;

import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Curtain
 * @date 2018/11/19 16:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception {

        User user = new User();
        user.setNickname("小明");
        user.setPhone("18157726283");
        user.setPassword("123456");

        Assert.assertNotNull(userService.save(user));

    }

}
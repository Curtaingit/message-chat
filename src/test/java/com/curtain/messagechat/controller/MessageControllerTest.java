package com.curtain.messagechat.controller;

import com.curtain.messagechat.domain.Message;
import com.curtain.messagechat.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Curtain
 * @date 2018/11/28 13:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageControllerTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void sendMessage() throws Exception {

        Message message = new Message();
        message.setContent("test");
        message.setTitle("Test");

        Message message1 = messageService.sendMessage(message);
        System.out.println("MessageControllerTest.sendMessage");
    }

}
package com.curtain.messagechat.service;

import com.curtain.messagechat.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Curtain
 * @date 2018/11/19 10:45
 */
public interface UserService extends UserDetailsService {

    /**
     * 保存
     * @param user
     * @return
     */
    User save(User user);
}

package com.curtain.messagechat.service.impl;

import com.curtain.messagechat.domain.User;
import com.curtain.messagechat.repository.UserRepository;
import com.curtain.messagechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public User save(User user) {
        return userRepository.save(user);
    }
}

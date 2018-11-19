package com.curtain.messagechat.repository;

import com.curtain.messagechat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Curtain
 * @date 2018/11/19 10:42
 */
public interface UserRepository extends JpaRepository<User,String> {

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    Optional<User> findByPhone(String phone);
}

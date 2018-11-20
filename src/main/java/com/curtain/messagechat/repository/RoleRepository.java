package com.curtain.messagechat.repository;


import com.curtain.messagechat.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Curtain
 * @date 2018/10/9 15:37
 */
public interface RoleRepository extends JpaRepository<Role,String> {
}

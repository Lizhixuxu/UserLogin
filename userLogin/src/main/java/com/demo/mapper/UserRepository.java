package com.demo.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.User;


/**
 * 用户Repository
 *
 * @author rx
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Query(value = "select * from t_user where user_name=?1 and password=?2", nativeQuery = true)
    User login(String userName, String password);

}

package com.cn.mongoTest;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: YUANERL
 * @Date: 2018/12/17 15:41
 * @Description:
 */
@Repository
public interface UserDao {

    void saveUser(UserEntity user);

    UserEntity findUserByUserName(String userName);

    void updateUser(UserEntity user);

    void deleteUserById(Long id);

    List<UserEntity> findAll();

}

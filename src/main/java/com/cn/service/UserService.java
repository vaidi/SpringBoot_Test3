package com.cn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.entity.User;

import java.util.List;

/**
 * @program: SpringBoot_Test
 * @Date: 2018/10/23 下午 03:43
 * @Author: Lacey_Hou
 * @Description:
 */

public interface UserService extends IService<User> {

    void insert(User user) throws Exception;

    User selectUser(String id);

    void delete(String id);

    void update(User user) throws Exception;

    List<User> findAll();

    List<String> selectName();
}

package com.cn.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.entity.User;
import com.cn.mapper.UserMapper;
import com.cn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @program: SpringBoot_Test
 * @Date: 2018/10/23 下午 03:45
 * @Author: Lacey_Hou
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;



    @Override
    @Transactional
    public void insert(User user) throws Exception {
        user.setUserId("5");
        userMapper.insert(user);
       throw  new Exception();
    }

    @Override
    public User selectUser(String id){
        return baseMapper.selectById(id);
    }
    @Override
    public void delete(String id){
        baseMapper.deleteById(id);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) throws Exception {

       baseMapper.updateById(user);
      // insert(user);
        try {
           insert(user);
       }catch (Exception e){
           e.printStackTrace();
       }
    }


    @Override
    public List<User> findAll(){
        return baseMapper.selectList(null);
    }

    @Override
    public List<String> selectName() {

        return Collections.emptyList();
    }
}

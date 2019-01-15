package com.cn.service;

import com.cn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: SpringBoot_Test3
 * @Date: 2018/10/24 下午 02:24
 * @Author: Lacey_Hou
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserService userService;


    @Test
    public void insert() throws Exception{
        User user=new User();
        userService.insert(user);
    }

    @Test
    public void selectUser() throws Exception{
       List<User> userList =  userService.findAll();
       userList.forEach(System.out::println);
        User user=userService.selectUser("5");
        System.out.println("selectUser:"+user);

    }

    @Test
    public void delete() throws Exception{
        userService.delete("5");
        System.out.println("delete Sucess!!");
    }

    @Test
    public void update() throws Exception{
        User user = new User();
        user.setMobile("13462950397");
        user.setUserId("1");
//        List<User> list = userService.findAll();
//        list.stream().forEach(System.out::println);
        userService.update(user);
    }

    @Test
    public void findAll() throws Exception{
        List<User> list=userService.findAll();
        for(User user:list){
            System.out.println("findAll:"+user);
        }
    }
}
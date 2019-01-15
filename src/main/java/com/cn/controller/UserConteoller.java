package com.cn.controller;


import com.cn.entity.User;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SpringBoot_Test2")
public class UserConteoller {


    @Autowired
    private UserService userService;


    @RequestMapping("getuser")
    public User getUser(){
        User user =new User();
        user.setMobile("555555");
        user.setUserId("1");
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

package com.cn;

import com.cn.entity.User;
import com.cn.mongoTest.UserDao;
import com.cn.mongoTest.UserEntity;
import com.cn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.cn.Mapper")
public class SpringBootTest3ApplicationTests {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception {







        ExecutorService executorService =Executors.newFixedThreadPool(Integer.MAX_VALUE);

        while (true){
//            UserEntity user = new UserEntity();
//            user.setUserName(UUID.randomUUID().toString().substring(1,10));
//            user.setPassWord(UUID.randomUUID().toString().substring(1,10));
//            user.setAge(new Random().nextInt(100));
//            userDao.saveUser(user);
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    UserEntity user = new UserEntity();
                    user.setUserName(UUID.randomUUID().toString().substring(1,10));
                    user.setPassWord(UUID.randomUUID().toString().substring(1,10));
                    user.setAge(new Random().nextInt(100));
                }
            }));
        }

//        List<UserEntity> list =userDao.findAll();
//        for (UserEntity userEntity : list) {
//            System.err.println("用户详情信息"+userEntity.toString());
//        }
    }

    @Test
    public void findUserByUserName() throws Exception {
        User user =new User();
        user.setMobile("555555");
        user.setUserId("1");
        userService.update(user);
        System.out.println("执行完毕");
    }


}

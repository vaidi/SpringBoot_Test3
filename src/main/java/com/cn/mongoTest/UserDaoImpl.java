package com.cn.mongoTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: YUANEL
 * @Date: 2018/12/17 15:41
 * @Description:
 */
@Component
public class UserDaoImpl implements  UserDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param user
     */
    @Override
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
        List<UserEntity> list = mongoTemplate.findAll(UserEntity.class);
        for (UserEntity userEntity : list) {
            System.out.println(userEntity.toString());
        }
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserEntity user =  mongoTemplate.findOne(query , UserEntity.class);
        return user;
    }

    /**
     * 更新对象
     * @param user
     */
    @Override
    public void updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getUserName()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }

    @Override
    public List<UserEntity> findAll() {
        return mongoTemplate.findAll(UserEntity.class);
    }

}

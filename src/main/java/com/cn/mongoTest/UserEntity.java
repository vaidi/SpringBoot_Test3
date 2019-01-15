package com.cn.mongoTest;

import java.io.Serializable;

/**
 * @Auther: YUANEL
 * @Date: 2018/12/17 15:39
 * @Description:
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;
    private String userName;
    private String passWord;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}

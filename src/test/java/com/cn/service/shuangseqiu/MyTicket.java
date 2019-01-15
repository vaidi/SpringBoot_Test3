package com.cn.service.shuangseqiu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: YUANEL
 * @Date: 2018/11/30 00:06
 * @Description:
 */
public class MyTicket {




    public static void main(String[] args) {
        Random random = new Random(33);
        List<Integer> integers = new ArrayList<>();
        for(int i=0;i<6;i++){
           int m =  random.nextInt(33);
           boolean flag = integers.stream().filter(e->e.intValue()==m).count()>0;
           if(!flag){
               integers.add(m);
           }else{
               i--;
           }
        }
        System.out.println(integers.toString());


    }













}

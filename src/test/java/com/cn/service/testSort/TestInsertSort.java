package com.cn.service.testSort;

import java.util.Arrays;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 17:43
 * @Description:
 */
public class TestInsertSort {

    public static void main(String[] args) {
        int[] array = {10,2,11,20,3,100,1};
        display(array);
        for(int i=1;i<array.length; i++){
            int temp = array[i];//3
            int j;
            for(j = i-1;j>=0&& array[j]>temp;j--){
                array[j+1] = array[j];
            }
            array[j+1] =temp;
        }
        display(array);




    }


    /**
     * 遍历打印
     */
    public static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int num :list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }


}

package com.cn.service.testSort;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/9 10:57
 * @Description:
 */
public class MyTest {

   static class MyClass{

        private Integer mm;

        public Integer getMm() {
            return mm;
        }

        public void setMm(Integer mm) {
            this.mm = mm;
        }
    }


    public static void main(String[] args) {

        String mm ="";
        StringBuilder sb = new StringBuilder();
        if (sb.lastIndexOf(",") != -1) {
            mm =sb.substring(0, sb.length() - 1).toString();

        }
        System.out.println(mm);
        MyClass myClass = new MyClass();
        if(myClass.getMm() ==1){
            System.out.println("7277");
        }


    }


}

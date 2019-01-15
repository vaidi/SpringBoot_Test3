package com.cn.service.testThread;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 14:33
 * @Description:
 */
public class TestCopyOnWriteArraySet {


    // TODO: set是HashSet对象时，程序会出错。
    // private static Set<String> set = new HashSet<String>();
    private static Set<String> set = new CopyOnWriteArraySet<String>();
    public static void main(String[] args) {
        // 同时启动两个线程对set进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value = null;
        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName() + "-" + (i%6);
                set.add(val);
                // 通过“Iterator”遍历set。
                printAll();
            }
        }
    }

}

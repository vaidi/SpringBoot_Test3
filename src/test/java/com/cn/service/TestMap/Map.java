package com.cn.service.TestMap;

/**
 * @Auther: YUANEL
 * @Date: 2018/11/22 20:09
 * @Description:
 */
public interface Map<K,V> {

    V put(K key, V value);

    V get(K k);

    int size();

    interface Entery<K,V>{


        K getKey();

        V getValue();

        V setValue(V value);

    }









}

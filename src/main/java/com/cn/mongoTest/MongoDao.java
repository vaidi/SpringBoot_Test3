package com.cn.mongoTest;

import com.mongodb.DBObject;

import java.util.List;

/**
 * @Auther: YUANERL
 * @Date: 2018/12/25 19:22
 * @Description:
 */
public interface MongoDao {


    /**
     * 根据索引名称获取索引
     * @param dbName 数据库名
     * @param collectionName 集合名
     * @param indexName 索引名字
     * @return
     */
    DBObject getIndexByName(String dbName, String collectionName,String indexName);




    /**
     * 向指定的数据库中添加给定的keys和相应的values,并插入文档的地理位置信息
     * @param dbName 数据库名
     * @param collectionName 集合名
     * @param keys 集合中域的keys
     * @param values 集合中域中的值
     * @param index_name_2d 2d索引的名称
     * @param lon 经度
     * @param lat 纬度
     * @return
     */
    boolean insert(String dbName, String collectionName, String[] keys, Object[] values,String index_name_2d, double
            lon, double lat);


}

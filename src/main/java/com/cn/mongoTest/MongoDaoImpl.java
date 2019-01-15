//package com.cn.mongoTest;
//
//import com.mongodb.*;
//import com.mongodb.client.model.geojson.Point;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @Auther: YUANEL
// * @Date: 2018/12/25 19:25
// * @Description:
// */
//
//@Repository
//public class MongoDaoImpl implements MongoGeoDao {
//    private static Logger logger = LoggerFactory.getLogger(MongoDaoImpl.class);
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//    @Override
//    public DBObject getIndexByName(String dbName, String collectionName, String indexName) {
//        List<DBObject> indexList=this.getIndexInfos(dbName, collectionName);
//        if(null!=indexList){
//            for(DBObject o:indexList){
//                String name=(String) o.get("name");
//                if(StringUtils.isNotBlank(name)&&name.equals(indexName)){
//                    return o;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 向指定的数据库中添加给定的keys和相应的values,并插入文档的地理位置信息
//     * @param dbName 数据库名
//     * @param collectionName 集合名
//     * @param keys 集合中域的keys
//     * @param values 集合中域中的值
//     * @param index_name_2d 2d索引的名称
//     * @param lon 经度
//     * @param lat 纬度
//     * @return
//     */
//    @Override
//    public boolean insert(String dbName, String collectionName, String[] keys, Object[] values, String index_name_2d, double lon, double lat) {
//        DB db=null;
//        DBCollection dbCollection=null;
//        WriteResult result=null;
//        String resultString=null;
//        if(null!=keys && null!=values){
//            if(keys.length!=values.length){
//                return false;
//            }
//            db=this.mongoClient.getDB(dbName);
//            dbCollection=db.getCollection(collectionName);
//            BasicDBObject insertObject=new BasicDBObject();
//            for(int i=0;i<keys.length;i++){//构建添加条件
//                insertObject.put(keys[i], values[i]);
//            }
//            //设置地址位置信息索引字段
//            if(StringUtils.isNotBlank(index_name_2d)){
//                BasicDBObject index_2d = new BasicDBObject();
//                index_2d.put(index_name_2d, "2d");
//                index_2d.put("background", true);
//                //没有2d索引 则创建
//                if(null==this.getIndexByName(dbName, collectionName, index_name_2d)){
//                    dbCollection.ensureIndex(index_2d, index_name_2d, false);
//                }
//            }
//            //地理位置信息
//            insertObject.put( index_name_2d, new Double[]{lon,lat} );
//            try {
//                result=dbCollection.insert(insertObject);
//                resultString=result.getError();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                if(null!=db){
//                    db.requestDone();//请求结束后关闭db
//                }
//            }
//            return null==resultString?false:true;
//
//
//        }
//        return false;
//    }
//}

package com.cn.service.mongo;

import com.cn.mongoTest.Location;
import com.cn.mongoTest.LocationRepository;
import com.cn.mongoTest.LocationRepositoryYaoli;
import com.cn.mongoTest.YaoLiDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * @Auther: YUANEL
 * @Date: 2018/12/26 16:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationRepositoryYaoli yaoliRep;


    @Test
    public void init() {
        // 等同db.location.ensureIndex( {position: "2d"} )
        mongoTemplate.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
        // 初始化数据
        mongoTemplate.save(new Location("天安门", 116.4041602659, 39.9096215780));
        mongoTemplate.save(new Location("东单", 116.4244857287, 39.9144951360));
        mongoTemplate.save(new Location("王府井", 116.4177807251, 39.9175129885));
        mongoTemplate.save(new Location("西单", 116.3834863095, 39.9133467579));
        mongoTemplate.save(new Location("复兴门", 116.3631701881, 39.9129554253));
        mongoTemplate.save(new Location("复兴门", 116.3631701881, 39.9129554253));
        mongoTemplate.save(new Location("西四", 116.3799838526, 39.9299098531));
        mongoTemplate.save(new Location("菜市口", 116.3809950293, 39.8952009239));
        mongoTemplate.save(new Location("东四", 116.4239387439, 39.9306126797));
        System.out.println("插入完毕");
    }



    /**
     * 查找天安门附近3公里的地点
     */
    @Test
    public void findCircleNearTest() {
        List<YaoLiDocument> locations = yaoliRep.findCircleNear(new Point(-122.394372, 		37.765385),  500);
        locations.forEach(location -> {
            System.err.println(location.toString());
        });
    }


    /**
     * 查找左下角是菜市口，右上角是东四，这个方形区域内的所有点
     */
    @Test
    public void findBoxNearTest() {
        Point point1 = new Point(116.3809950293, 39.8952009239);
        Point point2 = new Point(116.4239387439, 39.9306126797);
        List<Location> locations = locationRepository.findBoxWithin(point1, point2);
        locations.forEach(location -> {
            System.err.println(location.toString());
        });
    }
    /**
     * 查找左下角是菜市口，右上角是东四，这个方形区域内的所有点
     */
    @Test
    public void findBoxNearTest1() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(Integer.MAX_VALUE);
        for(int i=0;i<200;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Point point1 = new Point(-122.394372, 		37.765385);
                    Point point2 = new Point(116.4239387439, 100.9306126797);
                    List<YaoLiDocument> locations = yaoliRep.findBoxWithin(point1, point2);
                    locations.forEach(location -> {
                        System.err.println(location.toString());
                    });
                }
            });
        }
        sleep(10000);
    }

}

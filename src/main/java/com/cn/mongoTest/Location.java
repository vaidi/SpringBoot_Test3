package com.cn.mongoTest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/**
 * @Auther: YUANEL
 * @Date: 2018/12/26 16:06
 * @Description:
 */
@Document(collection = "location")
public class Location {

    @Id
    private String id;//地点名称
    private double[] position;//位置信息

    public Location(String id, double lon, double lat) {
        this.id = id;
        double[] p = new double[]{lon, lat};
        this.position = p;
    }

    public Location() {
    }

    public Location(String id, double[] position) {
        this.id = id;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", position=" + Arrays.toString(position) +
                '}';
    }
}

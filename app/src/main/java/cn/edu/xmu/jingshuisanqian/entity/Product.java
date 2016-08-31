package cn.edu.xmu.jingshuisanqian.entity;

import java.io.Serializable;

/**
 * Created by hd_chen on 2016/8/31.
 */
public class Product implements Serializable {
    String tag;
    String time;
    String title;

    public Product() {
    }

    public Product(String tag, String time, String title) {
        this.tag = tag;
        this.time = time;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "tag='" + tag + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

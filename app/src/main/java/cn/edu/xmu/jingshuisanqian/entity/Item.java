package cn.edu.xmu.jingshuisanqian.entity;

import java.io.Serializable;

/**
 * Created by hd_chen on 2016/9/1.
 */
public class Item implements Serializable {
    String itemId;
    String itemName;
    String description;
    String priceNow;
    String priceBefore;

    public Item() {
    }

    public Item(String itemId, String itemName, String description, String priceNow, String priceBefore) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.priceNow = priceNow;
        this.priceBefore = priceBefore;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(String priceNow) {
        this.priceNow = priceNow;
    }

    public String getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(String priceBefore) {
        this.priceBefore = priceBefore;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", priceNow='" + priceNow + '\'' +
                ", priceBefore='" + priceBefore + '\'' +
                '}';
    }
}

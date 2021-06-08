package com.hzy.customview.recyclerview;

/**
 * @author: wxj
 * @date: 2021/4/12
 * @description:
 */
public class User {
    private int id;
    private String name;
    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

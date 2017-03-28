package com.mac.th.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class Floor {
    int id;
    List<Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

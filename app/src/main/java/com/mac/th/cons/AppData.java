package com.mac.th.cons;

import com.mac.th.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class AppData {
    private static Map<Integer, List<Item>> appdata = new HashMap<>();

    public static void setData(Map<Integer, List<Item>> data) {
        appdata = data;
    }

    public static List<Item> getItems(int floorNumber) {
        return appdata.get(floorNumber);
    }

    public boolean isInit() {
        if (appdata.size() != 0)
            return true;
        return false;
    }

    public static List<Item> getItems(int floorNumber, int limit) {
        return new ArrayList<>(appdata.get(floorNumber).subList(0, limit));
    }

    public static Item getItem(int floorNumber, int id) {
        return appdata.get(floorNumber).get(id);
    }
}

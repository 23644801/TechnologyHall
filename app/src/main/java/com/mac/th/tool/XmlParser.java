package com.mac.th.tool;

import android.util.Log;
import android.util.Xml;

import com.mac.th.entity.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class XmlParser {
    private static final String TAG = "XmlParser";

    public static Map<Integer, List<Item>> parse2Item(InputStream is) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式
        int eventType = parser.getEventType();
        Map<Integer, List<Item>> result = null;
        List<Item> floor = null;
        Log.i(TAG, "while start");
        int floorNumber = 0;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            Item item = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    result = new HashMap<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("floor")) {
                        Log.i(TAG, "tag floor");
                        floor = new ArrayList<>();
                        floorNumber = Integer.parseInt(parser.getAttributeValue(0));
                    } else if (parser.getName().equals("item")) {
                        Log.i(TAG, "tag item");
                        item = new Item();
                        String id = parser.getAttributeValue(0);
                        String area = parser.getAttributeValue(1);
                        String name = parser.getAttributeValue(2);
                        boolean isHot = Boolean.parseBoolean(parser.getAttributeValue(3));
                        String explanation_zh = parser.getAttributeValue(4);
                        String instruction_zh = parser.getAttributeValue(5);
                        String explanation_en = parser.getAttributeValue(6);
                        String instruction_en = parser.getAttributeValue(7);
                        item.setId(id);
                        item.setArea(area);
                        item.setName(name);
                        item.setHot(isHot);
                        item.setExplanation_en(explanation_en);
                        item.setExplanation_zh(explanation_zh);
                        item.setInstruction_en(instruction_en);
                        item.setInstruction_zh(instruction_zh);
                        floor.add(item);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("floor")) {
                        Log.i(TAG, "end tag floor");
                        result.put(floorNumber, floor);
                    }
                    break;
            }
            eventType = parser.next();
        }
        Log.i(TAG, "解析完毕:" + result.size());
        return result;
    }
}

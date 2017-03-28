package com.mac.th.tool;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class ItemImageResTool {
    public static List<String> getImages(Context context,int floor, String id){
        List<String> result=new ArrayList<>();
        try {
            String[] list=context.getAssets().list("");
            String startName=floor+"f_"+id+"i_";
            for (String fileName :
                    list) {
                if((fileName.endsWith("JPG")||fileName.endsWith("jpg"))&&fileName.startsWith(startName)){
                    result.add("file:///android_asset/" +fileName);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    public static String getCoverImage(Context context,int floor, String id){
        try {
            String[] list=context.getAssets().list("");
            String startName=floor+"f_"+id+"i_";
            for (String fileName :
                    list) {
                if((fileName.endsWith("JPG")||fileName.endsWith("jpg"))&&fileName.startsWith(startName)){
                   return "file:///android_asset/" +fileName;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}

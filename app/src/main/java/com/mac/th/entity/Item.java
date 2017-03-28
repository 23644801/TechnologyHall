package com.mac.th.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class Item implements Parcelable {
    String id;
    String area;
    String name;
    boolean isHot;
    String explanation_zh;
    String instruction_zh;
    String explanation_en;
    String instruction_en;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public String getExplanation_zh() {
        return explanation_zh;
    }

    public void setExplanation_zh(String explanation_zh) {
        this.explanation_zh = explanation_zh;
    }

    public String getInstruction_zh() {
        return instruction_zh;
    }

    public void setInstruction_zh(String instruction_zh) {
        this.instruction_zh = instruction_zh;
    }

    public String getExplanation_en() {
        return explanation_en;
    }

    public void setExplanation_en(String explanation_en) {
        this.explanation_en = explanation_en;
    }

    public String getInstruction_en() {
        return instruction_en;
    }

    public void setInstruction_en(String instruction_en) {
        this.instruction_en = instruction_en;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.area);
        dest.writeString(this.name);
        dest.writeByte(this.isHot ? (byte) 1 : (byte) 0);
        dest.writeString(this.explanation_zh);
        dest.writeString(this.instruction_zh);
        dest.writeString(this.explanation_en);
        dest.writeString(this.instruction_en);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.id = in.readString();
        this.area = in.readString();
        this.name = in.readString();
        this.isHot = in.readByte() != 0;
        this.explanation_zh = in.readString();
        this.instruction_zh = in.readString();
        this.explanation_en = in.readString();
        this.instruction_en = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

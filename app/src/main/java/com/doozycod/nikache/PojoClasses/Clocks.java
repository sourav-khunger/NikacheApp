package com.doozycod.nikache.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class Clocks implements Parcelable {

    private String productId;
    private String name;
    private String clocksPrice;
    private int thumbnail;
    private String clocksDescription;
    private String clocksLength;
    private String clocksSize;
    private String clocksMaterial;
    private String clocksFeatures;

    public Clocks() {
    }

    public Clocks(String productId, String name, String clocksPrice, int thumbnail,
                  String clocksDescription, String clocksLength, String clocksSize,
                  String clocksMaterial, String clocksFeatures) {

        this.productId = productId;
        this.name = name;
        this.clocksPrice = clocksPrice;
        this.thumbnail = thumbnail;
        this.clocksDescription = clocksDescription;
        this.clocksLength = clocksLength;
        this.clocksSize = clocksSize;
        this.clocksMaterial = clocksMaterial;
        this.clocksFeatures = clocksFeatures;
    }

    public  String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClocksPrice() {
        return clocksPrice;
    }

    public void setClocksPrice(String clocksPrice) {
        this.clocksPrice = clocksPrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getClocksDescription() {
        return clocksDescription;
    }

    public void setClocksDescription(String clocksDescription) {
        this.clocksDescription = clocksDescription;
    }

    public  String getClocksLength() {
        return clocksLength;
    }

    public void setBrassDecorLength(String clocksLength) {
        this.clocksLength = clocksLength;
    }

    public  String getClocksSize() {
        return clocksSize;
    }

    public void setClocksSize(String clocksSize) {
        this.clocksSize = clocksSize;
    }

    public  String getClocksMaterial() {
        return clocksMaterial;
    }

    public void setClocksMaterial(String clocksMaterial) {
        this.clocksMaterial = clocksMaterial;
    }

    public  String getClocksFeatures() {
        return clocksFeatures;
    }

    public void setClocksFeatures(String clocksFeatures) {
        this.clocksFeatures = clocksFeatures;
    }


    public static final Parcelable.Creator<Clocks> CREATOR = new Parcelable.Creator<Clocks>() {
        public Clocks createFromParcel(Parcel source) {
            Clocks clocks = new Clocks();
            clocks.productId = source.readString();
            clocks.name = source.readString();
            clocks.clocksPrice = source.readString();
            clocks.thumbnail = source.readInt();
            clocks.clocksDescription = source.readString();
            clocks.clocksLength = source.readString();
            clocks.clocksSize = source.readString();
            clocks.clocksMaterial = source.readString();
            clocks.clocksFeatures = source.readString();

            return clocks;
        }

        public Clocks[] newArray(int size) {
            return new Clocks[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(name);
        dest.writeString(clocksPrice);
        dest.writeInt(thumbnail);
        dest.writeString(clocksDescription);
        dest.writeString(clocksLength);
        dest.writeString(clocksSize);
        dest.writeString(clocksMaterial);
        dest.writeString(clocksFeatures);
    }
}


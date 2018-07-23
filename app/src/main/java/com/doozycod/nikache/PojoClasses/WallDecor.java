package com.doozycod.nikache.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class WallDecor implements Parcelable {

    private String productId;
    private String name;
    private String walldecorPrice;
    private int thumbnail;
    private String walldecorDescription;
    private String walldecorLength;
    private String walldecorSize;
    private String walldecorMaterial;
    private String walldecorFeatures;
    private String walldecorQtyValue;



    public WallDecor() {

    }

    public WallDecor(String productId, String name, String walldecorPrice, int thumbnail,
                     String walldecorDescription, String walldecorLength, String walldecorSize,
                     String walldecorMaterial, String walldecorFeatures) {

        this.productId = productId;
        this.name = name;
        this.walldecorPrice = walldecorPrice;
        this.thumbnail = thumbnail;
        this.walldecorDescription = walldecorDescription;
        this.walldecorLength = walldecorLength;
        this.walldecorSize = walldecorSize;
        this.walldecorMaterial = walldecorMaterial;
        this.walldecorFeatures = walldecorFeatures;
    }


    public  String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWallDecorPrice() {
        return walldecorPrice;
    }

    public void setWallDecorPrice(String  walldecorPrice) {
        this.walldecorPrice = walldecorPrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getWalldecorDescription() {
        return walldecorDescription;
    }

    public void setWalldecorDescription(String walldecorDescription) {
        this.walldecorDescription = walldecorDescription;
    }

    public  String getWalldecorLength() {
        return walldecorLength;
    }

    public void setWalldecorLength(String walldecorLength) {
        this.walldecorLength = walldecorLength;
    }

    public  String getWalldecorSize() {
        return walldecorSize;
    }

    public void setWalldecorSize(String walldecorSize) {
        this.walldecorSize = walldecorSize;
    }

    public  String getwalldecorMaterial() {
        return walldecorMaterial;
    }

    public void setwalldecorMaterial(String walldecorMaterial) {
        this.walldecorMaterial = walldecorMaterial;
    }

    public  String getWalldecorFeatures() {
        return walldecorFeatures;
    }

    public void setWalldecorFeatures(String walldecorFeatures) {
        this.walldecorFeatures = walldecorFeatures;
    }

    public  String getProductQtyValue() {
        return walldecorQtyValue;
    }

    public void setProductQtyValue(String walldecorQtyValue) {
        this.walldecorQtyValue = walldecorQtyValue;
    }


    public static final Parcelable.Creator<WallDecor> CREATOR = new Parcelable.Creator<WallDecor>() {
        public WallDecor createFromParcel(Parcel source) {
            WallDecor wallDecor = new WallDecor();
            wallDecor.productId = source.readString();
            wallDecor.name = source.readString();
            wallDecor.walldecorPrice = source.readString();
            wallDecor.thumbnail = source.readInt();
            wallDecor.walldecorDescription = source.readString();
            wallDecor.walldecorLength = source.readString();
            wallDecor.walldecorSize = source.readString();
            wallDecor.walldecorMaterial = source.readString();
            wallDecor.walldecorFeatures = source.readString();
            wallDecor.walldecorQtyValue = source.readString();

            return wallDecor;
        }

        public WallDecor[] newArray(int size) {
            return new WallDecor[size];
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
        dest.writeString(walldecorPrice);
        dest.writeInt(thumbnail);
        dest.writeString(walldecorDescription);
        dest.writeString(walldecorLength);
        dest.writeString(walldecorSize);
        dest.writeString(walldecorMaterial);
        dest.writeString(walldecorFeatures);
        dest.writeString(walldecorQtyValue);
    }
}

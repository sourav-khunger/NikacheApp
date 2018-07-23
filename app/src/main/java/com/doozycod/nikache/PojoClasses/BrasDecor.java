package com.doozycod.nikache.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class BrasDecor implements Parcelable {

    private String productId;
    private String name;
    private String brassDecorPrice;
    private int thumbnail;
    private String brassDecorDescription;
    private String brassDecorLength;
    private String brassDecorSize;
    private String brassDecorMaterial;
    private String brassDecorFeatures;

    public BrasDecor() {
    }

    public BrasDecor(String productId, String name, String brassDecorPrice, int thumbnail,
                     String brassDecorDescription, String brassDecorLength, String brassDecorSize,
                     String brassDecorMaterial, String brassDecorFeatures) {

        this.productId = productId;
        this.name = name;
        this.brassDecorPrice = brassDecorPrice;
        this.thumbnail = thumbnail;
        this.brassDecorDescription = brassDecorDescription;
        this.brassDecorLength = brassDecorLength;
        this.brassDecorSize = brassDecorSize;
        this.brassDecorMaterial = brassDecorMaterial;
        this.brassDecorFeatures = brassDecorFeatures;
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

    public String getBrassDecorPrice() {
        return brassDecorPrice;
    }

    public void setBrassDecorPrice(String brassDecorPrice) {
        this.brassDecorPrice = brassDecorPrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getBrassDecorDescription() {
        return brassDecorDescription;
    }

    public void setBrassDecorDescription(String brassDecorDescription) {
        this.brassDecorDescription = brassDecorDescription;
    }

    public  String getBrassDecorLength() {
        return brassDecorLength;
    }

    public void setBrassDecorLength(String brassDecorLength) {
        this.brassDecorLength = brassDecorLength;
    }

    public  String getBrassDecorSize() {
        return brassDecorSize;
    }

    public void setBrassDecorSize(String brassDecorSize) {
        this.brassDecorSize = brassDecorSize;
    }

    public  String getBrassDecorMaterial() {
        return brassDecorMaterial;
    }

    public void setBrassDecorMaterial(String brassDecorMaterial) {
        this.brassDecorMaterial = brassDecorMaterial;
    }

    public  String getBrassDecorFeatures() {
        return brassDecorFeatures;
    }

    public void setBrassDecorFeatures(String brassDecorFeatures) {
        this.brassDecorFeatures = brassDecorFeatures;
    }


    public static final Parcelable.Creator<BrasDecor> CREATOR = new Parcelable.Creator<BrasDecor>() {
        public BrasDecor createFromParcel(Parcel source) {
            BrasDecor brasDecor = new BrasDecor();
            brasDecor.productId = source.readString();
            brasDecor.name = source.readString();
            brasDecor.brassDecorPrice = source.readString();
            brasDecor.thumbnail = source.readInt();
            brasDecor.brassDecorDescription = source.readString();
            brasDecor.brassDecorLength = source.readString();
            brasDecor.brassDecorSize = source.readString();
            brasDecor.brassDecorMaterial = source.readString();
            brasDecor.brassDecorFeatures = source.readString();

            return brasDecor;
        }

        public BrasDecor[] newArray(int size) {
            return new BrasDecor[size];
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
        dest.writeString(brassDecorPrice);
        dest.writeInt(thumbnail);
        dest.writeString(brassDecorDescription);
        dest.writeString(brassDecorLength);
        dest.writeString(brassDecorSize);
        dest.writeString(brassDecorMaterial);
        dest.writeString(brassDecorFeatures);
    }


}


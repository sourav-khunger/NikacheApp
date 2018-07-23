package com.doozycod.nikache.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class UtilityItems implements Parcelable {

    private String productId;
    private String name;
    private String utilityItemsPrice;
    private int thumbnail;
    private String utilityItemsDescription;
    private String utilityItemsLength;
    private String utilityItemsSize;
    private String utilityItemsMaterial;
    private String utilityItemsFeatures;

    public UtilityItems() {
    }

    public UtilityItems(String productId, String name, String utilityItemsPrice, int thumbnail,
                        String utilityItemsDescription, String utilityItemsLength, String utilityItemsSize,
                        String utilityItemsMaterial, String utilityItemsFeatures) {

        this.productId = productId;
        this.name = name;
        this.utilityItemsPrice = utilityItemsPrice;
        this.thumbnail = thumbnail;
        this.utilityItemsDescription = utilityItemsDescription;
        this.utilityItemsLength = utilityItemsLength;
        this.utilityItemsSize = utilityItemsSize;
        this.utilityItemsMaterial = utilityItemsMaterial;
        this.utilityItemsFeatures = utilityItemsFeatures;
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

    public String getUtilityItemsPrice() {
        return utilityItemsPrice;
    }

    public void setUtilityItemsPrice(String utilityItemsPrice) {
        this.utilityItemsPrice = utilityItemsPrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getUtilityItemsDescription() {
        return utilityItemsDescription;
    }

    public void setUtilityItemsDescription(String utilityItemsDescription) {
        this.utilityItemsDescription = utilityItemsDescription;
    }

    public  String getUtilityItemsLength() {
        return utilityItemsLength;
    }

    public void setUtilityItemsLength(String utilityItemsLength) {
        this.utilityItemsLength = utilityItemsLength;
    }

    public  String getUtilityItemsSize() {
        return utilityItemsSize;
    }

    public void setUtilityItemsSize(String utilityItemsSize) {
        this.utilityItemsSize = utilityItemsSize;
    }

    public  String getUtilityItemsMaterial() {
        return utilityItemsMaterial;
    }

    public void setUtilityItemsMaterial(String utilityItemsMaterial) {
        this.utilityItemsMaterial = utilityItemsMaterial;
    }

    public  String getUtilityItemsFeatures() {
        return utilityItemsFeatures;
    }

    public void setUtilityItemsFeatures(String utilityItemsFeatures) {
        this.utilityItemsFeatures = utilityItemsFeatures;
    }

    public static final Parcelable.Creator<UtilityItems> CREATOR = new Parcelable.Creator<UtilityItems>() {
        public UtilityItems createFromParcel(Parcel source) {
            UtilityItems utilityItems = new UtilityItems();
            utilityItems.productId = source.readString();
            utilityItems.name = source.readString();
            utilityItems.utilityItemsPrice = source.readString();
            utilityItems.thumbnail = source.readInt();
            utilityItems.utilityItemsDescription = source.readString();
            utilityItems.utilityItemsLength = source.readString();
            utilityItems.utilityItemsSize = source.readString();
            utilityItems.utilityItemsMaterial = source.readString();
            utilityItems.utilityItemsFeatures = source.readString();

            return utilityItems;
        }

        public UtilityItems[] newArray(int size) {
            return new UtilityItems[size];
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
        dest.writeString(utilityItemsPrice);
        dest.writeInt(thumbnail);
        dest.writeString(utilityItemsDescription);
        dest.writeString(utilityItemsLength);
        dest.writeString(utilityItemsSize);
        dest.writeString(utilityItemsMaterial);
        dest.writeString(utilityItemsFeatures);
    }

}

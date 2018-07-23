package com.doozycod.nikache.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class Furniture implements Parcelable{

    private String productId;
    private String name;
    private String furniturePrice;
    private int thumbnail;
    private String furnitureDescription;
    private String furnitureLength;
    private String furnitureSize;
    private String furnitureMaterial;
    private String furnitureFeatures;

    public Furniture()

    {
    }

    public Furniture(String productId, String name, String furniturePrice, int thumbnail,
                     String furnitureDescription, String furnitureLength, String furnitureSize,
                     String furnitureMaterial, String furnitureFeatures) {

        this.productId = productId;
        this.name = name;
        this.furniturePrice = furniturePrice;
        this.thumbnail = thumbnail;
        this.furnitureDescription = furnitureDescription;
        this.furnitureLength = furnitureLength;
        this.furnitureSize = furnitureSize;
        this.furnitureMaterial = furnitureMaterial;
        this.furnitureFeatures = furnitureFeatures;
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

    public String getFurniturePrice() {
        return furniturePrice;
    }

    public void setFurniturePrice(String furniturePrice) {
        this.furniturePrice = furniturePrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    public  String getFurnitureLength() {
        return furnitureLength;
    }

    public void setFurnitureLength(String furnitureLength) {
        this.furnitureLength = furnitureLength;
    }

    public  String getFurnitureSize() {
        return furnitureSize;
    }

    public void setFurnitureSize(String furnitureSize) {
        this.furnitureSize = furnitureSize;
    }

    public  String getFurnitureMaterial() {
        return furnitureMaterial;
    }

    public void setFurnitureMaterial(String furnitureMaterial) {
        this.furnitureMaterial = furnitureMaterial;
    }

    public  String getFurnitureFeatures() {
        return furnitureFeatures;
    }

    public void setFurnitureFeatures(String furnitureFeatures) {
        this.furnitureFeatures = furnitureFeatures;
    }

    public static final Parcelable.Creator<Furniture> CREATOR = new Parcelable.Creator<Furniture>() {
        public Furniture createFromParcel(Parcel source) {
            Furniture furniture = new Furniture();
            furniture.productId = source.readString();
            furniture.name = source.readString();
            furniture.furniturePrice = source.readString();
            furniture.thumbnail = source.readInt();
            furniture.furnitureDescription = source.readString();
            furniture.furnitureLength = source.readString();
            furniture.furnitureSize = source.readString();
            furniture.furnitureMaterial = source.readString();
            furniture.furnitureFeatures = source.readString();

            return furniture;
        }

        public Furniture[] newArray(int size) {
            return new Furniture[size];
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
        dest.writeString(furniturePrice);
        dest.writeInt(thumbnail);
        dest.writeString(furnitureDescription);
        dest.writeString(furnitureLength);
        dest.writeString(furnitureSize);
        dest.writeString(furnitureMaterial);
        dest.writeString(furnitureFeatures);
    }

}

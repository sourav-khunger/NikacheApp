package com.doozycod.nikache.PojoClasses;


import android.os.Parcel;
import android.os.Parcelable;

public class Antiques implements Parcelable {


    private String productId;
    private String name;
    private String antiquesPrice;
    private int thumbnail;
    private String antiquesDescription;
    private String antiquesLength;
    private String antiquesSize;
    private String antiquesMaterial;
    private String antiquesFeatures;

    public Antiques() {
    }

    public Antiques(String productId, String name, String antiquesPrice, int thumbnail,
                    String antiquesDescription, String antiquesLength, String antiquesSize,
                    String antiquesMaterial, String antiquesFeatures) {

        this.productId = productId;
        this.name = name;
        this.antiquesPrice = antiquesPrice;
        this.thumbnail = thumbnail;
        this.antiquesDescription = antiquesDescription;
        this.antiquesLength = antiquesLength;
        this.antiquesSize = antiquesSize;
        this.antiquesMaterial = antiquesMaterial;
        this.antiquesFeatures = antiquesFeatures;
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

    public String getAntiquesPrice() {
        return antiquesPrice;
    }

    public void setAntiquesPrice(String antiquesPrice) {
        this.antiquesPrice = antiquesPrice;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getAntiquesDescription() {
        return antiquesDescription;
    }

    public void setAntiquesDescription(String antiquesDescription) {
        this.antiquesDescription = antiquesDescription;
    }

    public  String getAntiquesLength() {
        return antiquesLength;
    }

    public void setAntiquesLength(String antiquesLength) {
        this.antiquesLength = antiquesLength;
    }

    public  String getAntiquesSize() {
        return antiquesSize;
    }

    public void setAntiquesSize(String antiquesSize) {
        this.antiquesSize = antiquesSize;
    }

    public  String getAntiquesMaterial() {
        return antiquesMaterial;
    }

    public void setAntiquesMaterial(String antiquesMaterial) {
        this.antiquesMaterial = antiquesMaterial;
    }

    public  String getAntiquesFeatures() {
        return antiquesFeatures;
    }

    public void setAntiquesFeatures(String antiquesFeatures) {
        this.antiquesFeatures = antiquesFeatures;
    }

    public static final Parcelable.Creator<Antiques> CREATOR = new Parcelable.Creator<Antiques>() {
        public Antiques createFromParcel(Parcel source) {
            Antiques antiques = new Antiques();
            antiques.productId = source.readString();
            antiques.name = source.readString();
            antiques.antiquesPrice = source.readString();
            antiques.thumbnail = source.readInt();
            antiques.antiquesDescription = source.readString();
            antiques.antiquesLength = source.readString();
            antiques.antiquesSize = source.readString();
            antiques.antiquesMaterial = source.readString();
            antiques.antiquesFeatures = source.readString();

            return antiques;
        }

        public Antiques[] newArray(int size) {
            return new Antiques[size];
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
        dest.writeString(antiquesPrice);
        dest.writeInt(thumbnail);
        dest.writeString(antiquesDescription);
        dest.writeString(antiquesLength);
        dest.writeString(antiquesSize);
        dest.writeString(antiquesMaterial);
        dest.writeString(antiquesFeatures);
    }


}

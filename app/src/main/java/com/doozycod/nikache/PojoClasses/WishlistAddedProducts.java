package com.doozycod.nikache.PojoClasses;


public class WishlistAddedProducts {

    private String productId;
    private String name;
    private String price;
    private int thumbnail;

    public WishlistAddedProducts() {
    }

    public WishlistAddedProducts(String productId, String name, String price, int thumbnail) {

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String numOfSongs) {
        this.price = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}

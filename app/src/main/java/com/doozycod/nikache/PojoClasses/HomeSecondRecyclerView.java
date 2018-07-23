package com.doozycod.nikache.PojoClasses;

public class HomeSecondRecyclerView {

    private String productId;
    private String name;
    private int price;
    private int thumbnail;

    public HomeSecondRecyclerView() {
    }

    public HomeSecondRecyclerView(String productId, String name, int price, int thumbnail) {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int numOfSongs) {
        this.price = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}

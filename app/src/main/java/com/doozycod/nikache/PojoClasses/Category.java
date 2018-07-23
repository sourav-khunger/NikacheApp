package com.doozycod.nikache.PojoClasses;

public class Category {

//    private String name;
    private int thumbnail;
    private String title;

    public Category() {
    }

    public Category(int thumbnail, String title) {

        this.thumbnail = thumbnail;
        this.title = title;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

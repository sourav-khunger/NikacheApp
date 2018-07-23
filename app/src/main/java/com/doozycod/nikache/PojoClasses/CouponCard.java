package com.doozycod.nikache.PojoClasses;

/**
 * Created by sunilkumar on 23/07/18.
 */

public class CouponCard {

    private String couponLabel;
    private String couponDescription;

    public CouponCard(String couponLabel, String couponDescription) {

        this.couponLabel = couponLabel;
        this.couponDescription = couponDescription;

    }

    public  String getCouponLabel() {
        return couponLabel;
    }

    public void setCouponLabel(String couponLabel) {
        this.couponLabel = couponLabel;
    }


    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }
}

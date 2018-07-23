package com.doozycod.nikache.PojoClasses;


public class CustomerFeedback {

    private String customerName;
    private String customerFeedback;

    public CustomerFeedback() {
    }

    public CustomerFeedback(String customerFeedback, String customerName) {

        this.customerFeedback = customerFeedback;
        this.customerName = customerName;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getCustomerFeedback() {
        return customerFeedback;
    }

    public void setCustomerFeedback(String customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}

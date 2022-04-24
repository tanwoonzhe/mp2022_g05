package com.example.laibrary.ui.book;

public class payment {

    private String bookingid, paymentid;

    public payment(){

    }

    public payment(String bookingid, String paymentid){
        this.bookingid = bookingid;
        this.paymentid = paymentid;
    }

    public String getBookingid(){
        return bookingid;
    }

    public String getPaymentid(){
        return paymentid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

}

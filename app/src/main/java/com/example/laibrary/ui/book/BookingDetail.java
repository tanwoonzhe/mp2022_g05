package com.example.laibrary.ui.book;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;

public class BookingDetail {

    private String bookingID;
    public String fullname,icnumber,phonenumber,namebook,quantity,rentdate,total, rentday, rentmonth, rentyear;


    public BookingDetail() {
    }

    public BookingDetail(String bookingID, String fullname, String icnumber, String phonenumber, String namebook,
                         String quantity, String rentdate, String total, String rentday, String rentmonth, String rentyear) {
        this.bookingID = bookingID;
        this.fullname = fullname;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.namebook = namebook;
        this.quantity = quantity;
        this.rentdate = rentdate;
        this.total = total;
        this.rentday = rentday;
        this.rentmonth = rentmonth;
        this.rentyear = rentyear;
    }

    public String getBookingID(){
        return bookingID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getIcnumber() {
        return icnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getNamebook() {
        return namebook;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getRentdate() {
        return rentdate;
    }

    public String getTotal() {
        return total;
    }

    public String getRentday() {
        return rentday;
    }

    public String getRentmonth() {
        return rentmonth;
    }

    public String getRentyear() {
        return rentyear;
    }

    public void setBookingID(String bookingID){
        this.bookingID = bookingID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setIcnumber(String icnumber) {
        this.icnumber = icnumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setNamebook(String namebook) {
        this.namebook = namebook;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRentdate(String rentdate) {
        this.rentdate = rentdate;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setRentday(String rentday) {
        this.rentday = rentday;
    }

    public void setRentmonth(String rentmonth) {
        this.rentmonth = rentmonth;
    }

    public void setRentyear(String rentyear) {
        this.rentyear = rentyear;
    }

}

package com.example.laibrary.ui.book;

public class BookingDetail {

    private String bookingID;
    public String fullname,icnumber,phonenumber,namebook,quantity,rentdate,total;

    public BookingDetail() {
    }

    public BookingDetail(String bookingID, String fullname, String icnumber, String phonenumber, String namebook, String quantity, String rentdate, String total) {
        this.bookingID = bookingID;
        this.fullname = fullname;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.namebook = namebook;
        this.quantity = quantity;
        this.rentdate = rentdate;
        this.total = total;
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
}

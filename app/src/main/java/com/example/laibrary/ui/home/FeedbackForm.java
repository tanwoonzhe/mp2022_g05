package com.example.laibrary.ui.home;

public class FeedbackForm {

    public String userFDemail;
    public String userFDfeedback;

    public FeedbackForm(){

    }

    public FeedbackForm(String userFDemail, String userFDfeedback) {
        this.userFDemail = userFDemail;
        this.userFDfeedback = userFDfeedback;
    }

    public String getUserFDemail() {
        return userFDemail;
    }

    public void setUserFDemail(String userFDemail) {
        this.userFDemail = userFDemail;
    }

    public String getUserFDfeedback() {
        return userFDfeedback;
    }

    public void setUserFDfeedback(String userFDfeedback) {
        this.userFDfeedback = userFDfeedback;
    }
}

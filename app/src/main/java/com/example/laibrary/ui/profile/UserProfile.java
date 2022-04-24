package com.example.laibrary.ui.profile;

public class UserProfile {
    public String userName;
    public String userAge;
    public String userEmail;

    public UserProfile(){

    }

    public UserProfile(String userName, String userAge, String userEmail) {
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

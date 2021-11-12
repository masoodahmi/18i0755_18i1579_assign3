package com.masoodahmad.i180755_i181579;

import android.net.Uri;

public class contacts {
    private String name;
    private String number;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    Uri img;

    public contacts(String name, String number,String email, Uri image) {
        this.name = name;
        this.number = number;
        this.email=email;
        this.img = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

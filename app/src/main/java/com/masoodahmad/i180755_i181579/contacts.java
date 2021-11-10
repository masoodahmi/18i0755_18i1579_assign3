package com.masoodahmad.i180755_i181579;

import android.net.Uri;

public class contacts {
    private String name, number;

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    Uri img;

    public contacts(String name, String number, Uri image) {
        this.name = name;
        this.number = number;
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

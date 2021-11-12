package com.masoodahmad.i180755_i181579;

import android.graphics.Bitmap;
import android.net.Uri;

public class homee {
    private String name,time,text;
    Uri pic;

    public homee(String name, String time, String text, Uri pic) {

        this.name = name;
        this.time = time;
        this.text = text;
        this.pic = pic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Uri getPic() {
        return pic;
    }

    public void setPic(Uri pic) {
        this.pic = pic;
    }
}

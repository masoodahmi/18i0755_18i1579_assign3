package com.masoodahmad.i180755_i181579;

import android.graphics.Bitmap;
import android.net.Uri;

public class homee {
    private String id, name, time ,text ;
    Bitmap pic;

    public homee(String id, String name, String time, String text, Bitmap pic) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.text = text;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}

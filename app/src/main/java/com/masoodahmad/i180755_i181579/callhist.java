package com.masoodahmad.i180755_i181579;

import android.graphics.Bitmap;
import android.net.Uri;

public class callhist {
    private String name, time ,arrow ;
    Bitmap pic;

    public callhist(String name, String time, String arrow, Bitmap pic) {
        this.name = name;
        this.time = time;
        this.arrow = arrow;
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

    public String getArrow() {
        return arrow;
    }

    public void setArrow(String arrow) {
        this.arrow = arrow;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}

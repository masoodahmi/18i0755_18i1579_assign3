package com.masoodahmad.i180755_i181579;

import android.graphics.Bitmap;
import android.net.Uri;

public class callhist {
    private String name, time ,src, dest;
    Uri pic;

    public callhist(String name, String time, String src, String dest, Uri pic) {
        this.name = name;
        this.time = time;
        this.src = src;
        this.dest = dest;
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Uri getPic() {
        return pic;
    }

    public void setPic(Uri pic) {
        this.pic = pic;
    }
}

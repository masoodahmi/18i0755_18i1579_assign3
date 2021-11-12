package com.masoodahmad.i180755_i181579;

public class chatss{
    String src, dest, text, time;

    public chatss(String src, String dest, String text, String time) {
        this.src = src;
        this.dest = dest;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package com.masoodahmad.i180755_i181579;

public class calllog {
    String src,dest,time;

    public calllog(String src, String dest, String time) {
        this.src = src;
        this.dest = dest;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


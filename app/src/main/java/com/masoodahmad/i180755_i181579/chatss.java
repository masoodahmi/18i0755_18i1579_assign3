package com.masoodahmad.i180755_i181579;

public class chatss{
    String id, text, time, userid;

    public chatss(String id, String text, String time, String userid) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.userid = userid;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

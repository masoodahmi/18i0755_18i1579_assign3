package com.masoodahmad.i180755_i181579;

import android.net.Uri;

public class user_chat {
    String name,text,time;
    Uri pic;
    Integer currentuser;

    public user_chat(String name, String text, String time, Uri pic, Integer currentuser) {
        this.name = name;
        this.text = text;
        this.time = time;
        this.pic = pic;
        this.currentuser = currentuser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Uri getPic() {
        return pic;
    }

    public void setPic(Uri pic) {
        this.pic = pic;
    }

    public Integer getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(Integer currentuser) {
        this.currentuser = currentuser;
    }
}

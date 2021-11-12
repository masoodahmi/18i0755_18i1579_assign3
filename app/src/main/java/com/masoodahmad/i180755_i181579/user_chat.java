package com.masoodahmad.i180755_i181579;

import android.net.Uri;

public class user_chat {
    String user1,user2, text,time;
    String pic;

    public user_chat(String user1, String user2, String text, String time, String pic) {
        this.user1 = user1;
        this.user2 = user2;
        this.text = text;
        this.time = time;
        this.pic = pic;
    }


    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

package com.masoodahmad.i180755_i181579;

public class calllog {
    String arrow,time;
    Integer userid,currentuser;

    public calllog(String arrow, String time, Integer userid, Integer currentuser) {
        this.arrow = arrow;
        this.time = time;
        this.userid = userid;
        this.currentuser = currentuser;
    }

    public String getArrow() {
        return arrow;
    }

    public void setArrow(String arrow) {
        this.arrow = arrow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(Integer currentuser) {
        this.currentuser = currentuser;
    }
}


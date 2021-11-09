package com.masoodahmad.i180755_i181579;

import android.net.Uri;

public class users {
    String email,pass,name,age,gender;
    Uri img;
    Integer usersid;

    public users(){

    }

    public users(String email, String pass, String name, String age, String gender, Uri img, Integer usersid) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.img = img;
        this.usersid = usersid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public Integer getUsersid() {
        return usersid;
    }

    public void setUsersid(Integer usersid) {
        this.usersid = usersid;
    }
}

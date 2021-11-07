package com.masoodahmad.i180755_i181579;

public class users {
    String email,pass,name,age,gender;
    Integer usersid;

    public users(){

    }

    public users(String email, String pass, String name, String age, String gender, Integer usersid) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public Integer getUsersid() {
        return usersid;
    }

    public void setUsersid(Integer usersid) {
        this.usersid = usersid;
    }
}

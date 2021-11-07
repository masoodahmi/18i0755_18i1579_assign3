package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.content.SharedPreferences;

public class SManager {
    SharedPreferences sharedPrefernces;
    SharedPreferences.Editor editor;

    public SManager(Context c) {
        this.sharedPrefernces = c.getSharedPreferences("AppKey", 0);
        this.editor = sharedPrefernces.edit();
        editor.apply();
    }

    public void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    public boolean getLogin(){
        return sharedPrefernces.getBoolean("KEY_LOGIN", false);
    }

    public void setUsername(String username){
        editor.putString("KEY_USERNAME", username);
        editor.commit();
    }

    public String getUsername(){
        return sharedPrefernces.getString("KEY_USERNAME", "");
    }
}

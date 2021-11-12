package com.masoodahmad.i180755_i181579;

import com.google.firebase.database.FirebaseDatabase;

public class Firebase extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

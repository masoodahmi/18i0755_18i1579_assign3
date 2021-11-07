package com.masoodahmad.i180755_i181579;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.CircularFlow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ongoingcall extends AppCompatActivity {
    ImageView ec;
    CircleImageView civ;
    TextView name;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoingcall);
        ec=findViewById(R.id.endcall);
        civ=findViewById(R.id.userpic);
        name=findViewById(R.id.namee);

        Intent i=getIntent();
        String userid=i.getStringExtra("userid");
        DbHelper dbh= new DbHelper(ongoingcall.this);
        SQLiteDatabase db=dbh.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + Database.user_chat.tablename +" where "+ Database.user_chat._ID + " = " + userid,null);
        while (c.moveToNext()){
            @SuppressLint("Range") byte[] arr=c.getBlob(c.getColumnIndex(Database.user_chat.pic));
            Bitmap img= BitmapFactory.decodeByteArray(arr,0,arr.length);
            civ.setImageBitmap(img);
            name.setText(c.getString(c.getColumnIndex(Database.user_chat.name)));


        }
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
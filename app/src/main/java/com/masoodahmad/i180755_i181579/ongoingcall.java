package com.masoodahmad.i180755_i181579;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.CircularFlow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ongoingcall extends AppCompatActivity {
    ImageView ec;
    CircleImageView civ;
    TextView name;
    FirebaseDatabase db;
    DatabaseReference ref;
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
        String username=i.getStringExtra("username");
        String userpic=i.getStringExtra("userpic");
        System.out.println(userpic);
//        db=FirebaseDatabase.getInstance();
//        ref=db.getReference("users");
//


        name.setText(username);
        //civ.setImageURI(Uri.parse(userpic));
        Picasso.get().load(userpic).into(civ);
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
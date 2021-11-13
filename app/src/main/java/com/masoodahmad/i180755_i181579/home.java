package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity {
    RecyclerView rv;
    ImageView logg;
    List<homee> ls;
    CircleImageView profileimg;
    TextView profilename,logout;
    DrawerLayout drwr;
    SManager sManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileimg=findViewById(R.id.profileimage);
        profilename=findViewById(R.id.profilename);

        drwr = findViewById(R.id.drwr);
        sManager = new SManager(getApplicationContext());

        BottomNavigationView bottomnav=findViewById(R.id.botmnav);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        profilename.setText(sManager.getProfilename());
        Picasso.get().load(sManager.getImg()).into(profileimg);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmntcont,new homefragment()).commit();
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sManager.setLogin(false);
                sManager.setUsername("");
                sManager.setProfilename("");
                sManager.setImg("");
                startActivity(new Intent(home.this,login.class));
                finish();
            }
        });
    }
    Fragment homefrag = new homefragment();
    Fragment contfrag = new contactsfragment();
    Fragment callfrag = new callfragment();
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfrag= null;
                    switch (item.getItemId()){
                        case R.id.navmsg:
                            selectedfrag= homefrag;
                            break;
                        case R.id.navcall:
                            selectedfrag= callfrag;
                            break;
                        case R.id.navpeople:
                            selectedfrag= contfrag;
                            break;
                        case R.id.navcam:
                            Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivity(i);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmntcont,selectedfrag).commit();
                    return true;
                }
            };
}
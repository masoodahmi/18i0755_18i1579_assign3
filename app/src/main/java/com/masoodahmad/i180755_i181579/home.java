package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    RecyclerView rv;
    List<homee> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        BottomNavigationView bottomnav=findViewById(R.id.botmnav);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmntcont,new homefragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfrag= null;
                    switch (item.getItemId()){
                        case R.id.navmsg:
                            selectedfrag=new homefragment();
                            break;
                        case R.id.navcall:
                            selectedfrag=new callfragment();
                            break;
                        case R.id.navpeople:
                            selectedfrag=new contactsfragment();
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
package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityService;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.screenshotdetection.ScreenshotDetectionDelegate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    DatabaseReference ref;
    FirebaseDatabase db;

    SManager sManager;
    String useremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db= FirebaseDatabase.getInstance();
        ref=db.getReference("users");
        setContentView(R.layout.activity_home);
        profileimg=findViewById(R.id.profileimage);
        profilename=findViewById(R.id.profilename);

        drwr = findViewById(R.id.drwr);
        sManager = new SManager(getApplicationContext());
        useremail=sManager.getUsername();
        BottomNavigationView bottomnav=findViewById(R.id.botmnav);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        profilename.setText(sManager.getProfilename());
        Picasso.get().load(sManager.getImg()).into(profileimg);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmntcont,new homefragment()).commit();
        logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home.this,login.class);

                status("offline");
                sManager.setLogin(false);
                sManager.setUsername("");
                sManager.setProfilename("");
                sManager.setImg("");

                startActivity(i);

                finish();
            }
        });
    }
    private void status(String status){
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
              for(DataSnapshot data:dataSnapshot.getChildren()){
                  if(data.child("email").getValue().toString().equals(useremail)){
                      ref.child(data.getKey()).child("status").setValue(status);
                      break;
                  }
              }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        status("online");
    }

    @Override
    protected void onStop() {
        super.onStop();
        status("offline");

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        status("offline");
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




//    @Override
//    public void onScreenCaptured(String path) {
//        Toast.makeText(this, "screenshot lia gya h", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onScreenCapturedWithDeniedPermission() {
//        Toast.makeText(this, "Please grant read external storage permission for screenshot detection", Toast.LENGTH_SHORT).show();
//    }



    //private ScreenshotDetectionDelegate screenshotDetectionDelegate = new ScreenshotDetectionDelegate(this, this);



//    companion object {
//        private Integer REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 3009;
//    }
//
//
//
////    private String TAG="home" ;
////
////    @Override
////    public void onStart() {
////        super.onStart();
////        screenshotDetectionDelegate.startScreenshotDetection();
////    }
////
////    @Override
////    public void onStop() {
////        super.onStop();
////        screenshotDetectionDelegate.stopScreenshotDetection();
////    }
////
////    @Override
////    public void onScreenCaptured(@NonNull String s) {
////        Log.d(TAG,"take action");
////    }
////
////    @Override
////    public void onScreenCapturedWithDeniedPermission() {
////        Log.d(TAG,"no");

}
package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chatting extends ScreenshotDetectionActivity {
    TextView username;
    List<chatss> chatList;
    RecyclerView rv;
    EditText  entermsg;
    ImageView sendbtn,chatbckbtn,callbtn;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatabaseReference ref1;
    SManager sManager;
    Adopter3  adapter;
    RecyclerView.LayoutManager lm;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date time=new Date();
        SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
        String t= adf.format(time);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        chatList = new ArrayList<>();
        sManager = new SManager(getApplicationContext());
        username = findViewById(R.id.username);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        entermsg = findViewById(R.id.entermsg);
        String enteredmssg = entermsg.getText().toString();
        sendbtn = findViewById(R.id.sendbtn);
        callbtn=findViewById(R.id.callbtn);
        database = FirebaseDatabase.getInstance();
        ref=database.getReference("user_chat");
        ref1 = database.getReference("chatting");
        ref1.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    System.out.println(d.child("src").getValue().toString());
                    if(
                            (d.child("src").getValue().toString().equals(sManager.getUsername()) && d.child("dest").getValue().toString().equals(intent.getStringExtra("userid")))
                                    ||
                                    (d.child("dest").getValue().toString().equals(sManager.getUsername()) && d.child("src").getValue().toString().equals(intent.getStringExtra("userid")))
                    ){
                        chatList.add(new chatss(d.child("src").getValue().toString(),
                                d.child("dest").getValue().toString(),
                                d.child("text").getValue().toString(),
                                d.child("time").getValue().toString()));
                    }

                }
                adapter =new Adopter3(chatList,getApplicationContext());
                lm= new LinearLayoutManager( getApplicationContext());
                lm.scrollToPosition(adapter.getItemCount()-1);
                rv.setLayoutManager(lm);
                rv.setAdapter(adapter);

            }
        });
        //adapter.notifyDataSetChanged();



        //if (ref.getKey().equals("")){
            //ref.push().setValue(new user_chat("test", "test", "test", "test", "test"));
        //}
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        chatbckbtn=findViewById(R.id.chatbckbtn);
        chatbckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(chatting.this,home.class);

                //startActivity(i);
                //overridePendingTransition(R.);
                finish();
            }
        });




        rv=findViewById(R.id.crv);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            String message = entermsg.getText().toString();
            @Override
            public void onClick(View view) {
                Task<DataSnapshot> data = ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        boolean found = false;
                        for(DataSnapshot d: dataSnapshot.getChildren()){
                            //System.out.println(ref.child(d.getValue().toString()));
                            if (
                                    (intent.getStringExtra("userid").equals(d.child("user1").getValue().toString()) &&
                                            (sManager.getUsername().equals(d.child("user2").getValue().toString())))
                                            ||
                                            (intent.getStringExtra("userid").equals(d.child("user2").getValue().toString()) &&
                                                    sManager.getUsername().equals(d.child("user1").getValue().toString()))

                            ){
                                Date time=new Date();
                                SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
                                String t= adf.format(time);
                                ref.child(d.getKey()).child("time").setValue(t);
                                found = true;
                                ref.child(d.getKey()).child("text").setValue(entermsg.getText().toString());
                                //entermsg.setText("");
                                break;
                            }
                        }
                        if (found){

                            found = false;

                        }else{
                            Date time=new Date();
                            SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
                            String t= adf.format(time);

                            ref.push().setValue(new user_chat(sManager.getUsername(), intent.getStringExtra("userid"), entermsg.getText().toString(),t
                                    , intent.getParcelableExtra("userpic").toString()));
                            //entermsg.setText("");
                        }
                        Date time=new Date();
                        SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
                        String t= adf.format(time);
                        ref1.push().setValue(new chatss(sManager.getUsername(),intent.getStringExtra("userid"), entermsg.getText().toString(), t));


                        entermsg.setText("");
                    }
                });



//                ref.addValueEventListener(new ValueEventListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            for (DataSnapshot data : snapshot.getChildren()) {
//                                if (
//                                        (intent.getStringExtra("userid").equals(data.child("user1").getValue().toString()) &&
//                                                sManager.getUsername().equals(data.child("user2").getValue().toString()))
//                                                ||
//                                                (intent.getStringExtra("userid").equals(data.child("user2").getValue().toString()) &&
//                                                        sManager.getUsername().equals(data.child("user1").getValue().toString()))
//                                ) {
//
//
//                                } else {
//                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
//                                    LocalDateTime now = LocalDateTime.now();
//
//                                    ref.push().setValue(new user_chat(sManager.getUsername(), intent.getStringExtra("userid"), entermsg.getText().toString().trim(), now.toString()
//                                            , intent.getParcelableExtra("userpic").toString()));
//
//                                }
//                            }
//                        }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });





            }
        });

        adapter =new Adopter3(chatList,chatting.this);
        lm= new LinearLayoutManager( chatting.this);
        lm.scrollToPosition(adapter.getItemCount()-1);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        ref1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    if(
                            (d.child("src").getValue().toString().equals(sManager.getUsername()) && d.child("dest").getValue().toString().equals(intent.getStringExtra("userid")))
                                    ||
                                    (d.child("dest").getValue().toString().equals(sManager.getUsername()) && d.child("src").getValue().toString().equals(intent.getStringExtra("userid")))
                    ){
                        chatList.add(new chatss(d.child("src").getValue().toString(),
                                d.child("dest").getValue().toString(),
                                d.child("text").getValue().toString(),
                                d.child("time").getValue().toString()));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onScreenCaptured(String path) {
        Toast.makeText(this, "screenshot lia gya h", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScreenCapturedWithDeniedPermission() {
        Toast.makeText(this, "Please grant read external storage permission for screenshot detection", Toast.LENGTH_SHORT).show();
    }


}
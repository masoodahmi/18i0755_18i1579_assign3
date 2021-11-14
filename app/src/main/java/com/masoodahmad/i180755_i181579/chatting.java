package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chatting extends ScreenshotDetectionActivity {
    TextView username,activestatus;
    List<chatss> chatList;
    RecyclerView rv;
    EditText  entermsg;
    ImageView sendbtn,chatbckbtn,callbtn;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatabaseReference ref1;
    DatabaseReference ref2;
    SManager sManager;
    Adopter3  adapter;
    RecyclerView.LayoutManager lm;
    Intent intent;
    Integer count=1;
    Call call;
    ImageView camerabtn;
    Uri receivedimg;
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
        activestatus = findViewById(R.id.activestatus);
        intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        entermsg = findViewById(R.id.entermsg);
        String enteredmssg = entermsg.getText().toString();
        sendbtn = findViewById(R.id.sendbtn);
        callbtn=findViewById(R.id.callbtn);
        camerabtn = findViewById(R.id.camerabtn);
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult( gallery , 100);
            }
        });
        database = FirebaseDatabase.getInstance();
        ref=database.getReference("user_chat");
        ref1 = database.getReference("chatting");
        ref2 = database.getReference("users");
//        status("online");
        ref2.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                for (DataSnapshot d: snapshot.getChildren()){
                    if(d.child("email").getValue().toString().equals(intent.getStringExtra("userid"))){
                        System.out.println("please");
                        activestatus.setText(d.child("status").getValue().toString());
                        break;
                    }
                }
            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d: snapshot.getChildren()){
                    if(d.child("email").getValue().toString().equals(intent.getStringExtra("userid"))){
                        activestatus.setText(d.child("status").getValue().toString());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                // Instantiate a SinchClient
                android.content.Context context = getApplicationContext();
                SinchClient sinchClient = Sinch.getSinchClientBuilder().context(context)
                        .applicationKey("6dabf6e9-7336-491f-a1ad-ba7e1ecb8122")
                        .environmentHost("ocra.api.sinch.com")
                        .userId("jakjkaka")
                        .build();

                // Specify the client capabilities.
                sinchClient.setSupportManagedPush(true);
                sinchClient.startListeningOnActiveConnection();

                sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener() {

                });


                // Add the client listener that handles client state changes.


                // Start the client
                sinchClient.start();

                CallClient call = sinchClient.getCallClient();
                call.callPhoneNumber("+923032241969");



                // Use the CallClient to place and receive calls

                // Stop listening for incoming calls.
                sinchClient.stopListeningOnActiveConnection();

                // Stop the client when the calling functionality is no longer needed.
                sinchClient.terminateGracefully();

            }
        });



        chatbckbtn=findViewById(R.id.chatbckbtn);
        chatbckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(chatting.this,home.class);

                startActivity(i);
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
       count++;
       if(count%2==0) {
           ref1.push().setValue(new chatss(sManager.getUsername(), intent.getStringExtra("userid")
                   , "Screen shot has been taken", "test"));
       }


    }

    @Override
    public void onScreenCapturedWithDeniedPermission() {
        Toast.makeText(this, "Please grant read external storage permission for screenshot detection", Toast.LENGTH_SHORT).show();
    }


    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call call) {

        }
    }

    private class SinchCallListener implements CallListener{

        @Override
        public void onCallProgressing(Call call) {
            Toast.makeText(getApplicationContext(), "Ringing...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCallEstablished(Call call) {
            Toast.makeText(getApplicationContext(), "Call Established", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCallEnded(Call endcall) {
            Toast.makeText(getApplicationContext(), "Call Ended", Toast.LENGTH_SHORT).show();
            call = null;
            endcall.hangup();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK ){
            receivedimg= data.getData();
            File f = new File(String.valueOf(receivedimg));
            String imageName = f.getName();
            System.out.println(imageName);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            StorageReference strRef= FirebaseStorage.getInstance().getReference();
            strRef=strRef.child("pictures_chat/"+now.toString()+".jpg");
            strRef.putFile(receivedimg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> t=taskSnapshot.getStorage().getDownloadUrl();
                    t.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Date time=new Date();
                            SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
                            String t= adf.format(time);
                            String pic=uri.toString();
                            ref1.push().setValue(new chatss(sManager.getUsername(), intent.getStringExtra("userid"), pic, t));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed!!!!", Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed!!!!", Toast.LENGTH_SHORT).show();
                }
            });

        }



    }
}
package com.masoodahmad.i180755_i181579;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chatting extends AppCompatActivity {
    TextView username;
    List<chatss> chatList;
    RecyclerView rv;
    EditText  entermsg;
    ImageView sendbtn,chatbckbtn,callbtn;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date time=new Date();
        SimpleDateFormat adf=new SimpleDateFormat("HH:mm");
        String t= adf.format(time);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        chatList = new ArrayList<>();
        Intent i = getIntent();
        String userid = i.getStringExtra("userid");
        username = findViewById(R.id.username);
        username.setText(i.getStringExtra("name"));
       // Bitmap img=i.getParcelableExtra("pic");

        entermsg = findViewById(R.id.entermsg);
        sendbtn = findViewById(R.id.sendbtn);
        callbtn=findViewById(R.id.callbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbh= new DbHelper(chatting.this);
                SQLiteDatabase db=dbh.getReadableDatabase();
                Cursor c=db.rawQuery("select * from " + Database.user_chat.tablename +" where "+ Database.user_chat._ID + " = " + userid,null);
                while (c.moveToNext()){
                    String crruser=c.getString(c.getColumnIndex(Database.user_chat.currentuser));
                    db.close();
                    db=dbh.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    cv.put("arrow","outbound");
                    cv.put("time",t);
                    cv.put("userid",userid);
                    cv.put("currentuser",crruser);
                    db.insert(Database.calllog.tablename,null,cv);

                    Intent i=new Intent(chatting.this,ongoingcall.class);
                    i.putExtra("userid",userid);
                    startActivity(i);
                }
            }
        });

        chatbckbtn=findViewById(R.id.chatbckbtn);
        chatbckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(chatting.this,homefragment.class);
                startActivity(i);
                finish();
            }
        });




        rv=findViewById(R.id.crv);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbh= new DbHelper(chatting.this);
                SQLiteDatabase db=dbh.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put(Database.chatss.text,entermsg.getText().toString());
                cv.put(Database.chatss.userid,userid);
                db.insert(Database.chatss.tablename,null,cv);
                cv= new ContentValues();
                cv.put(Database.user_chat.text,entermsg.getText().toString());
                cv.put(Database.user_chat.time,t);
                String [] arr=new String[]{userid};

                db.update(Database.user_chat.tablename,cv,"_ID=?",arr);

                db.close();
                dbh.close();
                chatList.clear();

                dbh= new DbHelper(chatting.this);
                db=dbh.getReadableDatabase();

                Cursor c=db.rawQuery("select * from " + Database.chatss.tablename +" where "+ Database.chatss.userid + " = " + userid,null);

                while(c.moveToNext()){
                    chatList.add(new chatss(c.getString(c.getColumnIndex(Database.chatss._ID)),
                            c.getString(c.getColumnIndex(Database.chatss.text)),
                            c.getString(c.getColumnIndex(Database.chatss.time)),
                            c.getString(c.getColumnIndex(Database.chatss.userid))));
                }

                Adopter3  adapter =new Adopter3(chatList,chatting.this);
                RecyclerView.LayoutManager lm= new LinearLayoutManager( chatting.this);
                rv.setLayoutManager(lm);
                rv.setAdapter(adapter);
                entermsg.setText("");


            }
        });

        DbHelper dbh= new DbHelper(chatting.this);
        SQLiteDatabase db=dbh.getReadableDatabase();

        Cursor c=db.rawQuery("select * from " + Database.chatss.tablename +" where "+ Database.chatss.userid + " = " + userid,null);

        while(c.moveToNext()){
            chatList.add(new chatss(c.getString(c.getColumnIndex(Database.chatss._ID)),
                    c.getString(c.getColumnIndex(Database.chatss.text)),
                    c.getString(c.getColumnIndex(Database.chatss.time)),
                    c.getString(c.getColumnIndex(Database.chatss.userid))));
        }




        Adopter3  adapter =new Adopter3(chatList,chatting.this);
        RecyclerView.LayoutManager lm= new LinearLayoutManager( chatting.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


    }
}
package com.masoodahmad.i180755_i181579;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class DbHelper extends SQLiteOpenHelper {
    Context c;
    String query1="CREATE TABLE " +
            Database.users.tablename + "(" +
            Database.users._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Database.users.name+ " TEXT, "+
            Database.users._EMAIL+ " TEXT, "+
            Database.users._PASSWORD+ " TEXT, "+
            Database.users.pic+ " BLOB, "+
            Database.users.bio+ " TEXT, "+
            Database.users.gender+ " TEXT);";
    String query2="CREATE TABLE " +
            Database.user_chat.tablename + "(" +
            Database.user_chat._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Database.user_chat.name+ " TEXT, "+
            Database.user_chat.text+ " TEXT, "+
            Database.user_chat.pic+ " BLOB, "+
            Database.user_chat.time+ " TEXT, "+
            Database.user_chat.currentuser+ " TEXT);";


    String query3="CREATE TABLE " +
            Database.chatss.tablename + "(" +
            Database.chatss._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Database.chatss.text+ " TEXT, "+
            Database.chatss.time+ " TEXT, "+
            Database.chatss.userid+ " INTEGER);";


    String query4="CREATE TABLE " +
            Database.calllog.tablename + "(" +
            Database.calllog._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Database.calllog.arrow+ " TEXT, "+
            Database.chatss.time+ " TEXT, "+
            Database.calllog.userid+ " INTEGER, "+
            Database.user_chat.currentuser+ " TEXT);";





    String delquery1="DROP TABLE IF EXISTS "+ Database.users.tablename;
    String delquery2="DROP TABLE IF EXISTS "+ Database.user_chat.tablename;
    String delquery3="DROP TABLE IF EXISTS "+ Database.chatss.tablename;
    String delquery4="DROP TABLE IF EXISTS "+ Database.calllog.tablename;
    public DbHelper(@Nullable Context context) {
        super(context,Database.DB_NAME,null,Database.DB_VERSION);
        c = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(query4);
        Bitmap bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.girl);
        ByteArrayOutputStream bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byte [] byarr=bytearr.toByteArray();
        ContentValues cv=new ContentValues();

        cv.put("text","I'm going to karachi tomorrow");
        cv.put("time","Now");
        cv.put("userid","1");
        sqLiteDatabase.insert(Database.chatss.tablename,null,cv);
        cv.put("text","Are you coming? 😘💖");
        cv.put("time","Now");
        cv.put("userid","1");
        sqLiteDatabase.insert(Database.chatss.tablename,null,cv);

        cv.put("text","I'm going to London tomorrow");
        cv.put("time","10:00");
        cv.put("userid","2");
        sqLiteDatabase.insert(Database.chatss.tablename,null,cv);

        cv=new ContentValues();
        cv.put("arrow","outbound");
        cv.put("time","19:00");
        cv.put("userid","2");
        cv.put("currentuser","1");

        sqLiteDatabase.insert(Database.calllog.tablename,null,cv);




        cv=new ContentValues();
        cv.put("name","Janet Fowler");
        cv.put("text","I'm going to karachi");
        cv.put("pic",byarr);
        cv.put("time","Now");
        cv.put("currentuser","1");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);


        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.girl33);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Ayesha Ahmad");
        cv.put("text","Sounds good");
        cv.put("pic",byarr);
        cv.put("time","16:00");
        cv.put("currentuser","1");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);

        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.boy0);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Haseeb Ahmad");
        //cv.put("text","suna haseby kya kr rha h tuuu...");
        cv.put("pic",byarr);
        cv.put("time","23:00");
        cv.put("currentuser","1");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);



        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.boy3);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Bradley Cooper");
        cv.put("text","What are you doing");
        cv.put("pic",byarr);
        cv.put("time","Now");
        cv.put("currentuser","2");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);



        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.girl2);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Emilia Clarke");
        cv.put("text","I am training a dragon");
        cv.put("pic",byarr);
        cv.put("time","12:00");
        cv.put("currentuser","2");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);



        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.girl11);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Zoe Saldana");
        cv.put("text","I dont learn...");
        cv.put("pic",byarr);
        cv.put("time","Mon");
        cv.put("currentuser","2");
        sqLiteDatabase.insert(Database.user_chat.tablename,null,cv);

        //accounts
        cv=new ContentValues();
        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.ca);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Masood Ahmad");
        cv.put(Database.users._EMAIL,"masood");
        cv.put(Database.users._PASSWORD,"asdf");
        cv.put("pic",byarr);
        cv.put("bio","pta ni");
        cv.put("gender","M");
        sqLiteDatabase.insert(Database.users.tablename,null,cv);



        bm= BitmapFactory.decodeResource(c.getResources(),R.drawable.im);
        bytearr=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bytearr);
        byarr=bytearr.toByteArray();


        cv.put("name","Haseeb Ahamd");
        cv.put("emailLLLL","haseeb");
        cv.put("password","zxcv");
        cv.put("pic",byarr);
        sqLiteDatabase.insert(Database.users.tablename,null,cv);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(delquery1);
        sqLiteDatabase.execSQL(delquery2);
        sqLiteDatabase.execSQL(delquery3);
        sqLiteDatabase.execSQL(delquery4);
        onCreate(sqLiteDatabase);
    }




}

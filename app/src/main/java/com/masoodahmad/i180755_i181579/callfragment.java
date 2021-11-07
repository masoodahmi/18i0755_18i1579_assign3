package com.masoodahmad.i180755_i181579;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link callfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class callfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public callfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment callfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static callfragment newInstance(String param1, String param2) {
        callfragment fragment = new callfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("WrongThread")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.callrv, container, false);
        RecyclerView rv;
        rv=view.findViewById(R.id.rvv);
        List<callhist> ls;
        ls=new ArrayList<>();

        Intent previous= getActivity().getIntent();

        String strID = previous.getStringExtra("id");
        System.out.println(strID);
        DbHelper dbh= new DbHelper(getContext());
        SQLiteDatabase db=dbh.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + Database.calllog.tablename +" where "+ Database.calllog.currentuser + " = " + strID,null);
        while(c.moveToNext()){

            @SuppressLint("Range") String ss=c.getString(c.getColumnIndex(Database.calllog.userid));
            System.out.println("sdasdk"+ss);
            Cursor c1=db.rawQuery("select * from " + Database.user_chat.tablename +" where "+ Database.user_chat._ID + " = " + ss,null);
            while (c1.moveToNext()){
                @SuppressLint("Range") String name=c1.getString(c1.getColumnIndex(Database.user_chat.name));
                System.out.println(name);
                @SuppressLint("Range") byte[] arr=c1.getBlob(c1.getColumnIndex(Database.user_chat.pic));
                Bitmap img= BitmapFactory.decodeByteArray(arr,0,arr.length);
                ls.add(new callhist(name,c.getString(c.getColumnIndex(Database.calllog.time)),
                        c.getString(c.getColumnIndex(Database.calllog.arrow)),img));

            }




        }











//        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.girl)
//                + '/' + getResources().getResourceTypeName(R.drawable.girl) + '/' + getResources().getResourceEntryName(R.drawable.girl) );
//
//        Uri out = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.outarrow)
//                + '/' + getResources().getResourceTypeName(R.drawable.outarrow) + '/' + getResources().getResourceEntryName(R.drawable.outarrow) );
//        Uri miss = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.missarrow)
//                + '/' + getResources().getResourceTypeName(R.drawable.missarrow) + '/' + getResources().getResourceEntryName(R.drawable.missarrow) );
//
//
//
//        ls.add(new callhist("Janet Fowler","10:21","inbound - ",out,imageUri));
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.boy)
//                + '/' + getResources().getResourceTypeName(R.drawable.boy) + '/' + getResources().getResourceEntryName(R.drawable.boy) );
//
//        ls.add(new callhist("Jason Boyd","22:14","lost - ",miss,imageUri));
//        ls.add(new callhist("Nicholas Dunn","monday","inbound - ",out,imageUri));
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.girl)
//                + '/' + getResources().getResourceTypeName(R.drawable.girl) + '/' + getResources().getResourceEntryName(R.drawable.girl) );
//
//        ls.add(new callhist("Carol Clark","friday","lost - ",miss,imageUri));


        MyRvAdopter adapter =new MyRvAdopter(ls,getContext());
        RecyclerView.LayoutManager lm= new LinearLayoutManager( getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


        return view;
    }
}
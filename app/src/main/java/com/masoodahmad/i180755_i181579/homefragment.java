package com.masoodahmad.i180755_i181579;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view=inflater.inflate(R.layout.homerv, container, false);
        RecyclerView rv;
        rv=view.findViewById(R.id.rv);
        List<homee> ls;
        ls=new ArrayList<>();
        DbHelper dbh= new DbHelper(getContext());
        SQLiteDatabase db=dbh.getReadableDatabase();
        Intent previous= getActivity().getIntent();

        String strID = previous.getStringExtra("id");
        System.out.println(strID);

        Cursor c=db.rawQuery("select * from " + Database.user_chat.tablename +" where "+ Database.user_chat.currentuser + " = " + strID,null);

        while(c.moveToNext()){

            @SuppressLint("Range") byte[] arr=c.getBlob(c.getColumnIndex(Database.user_chat.pic));
            Bitmap img= BitmapFactory.decodeByteArray(arr,0,arr.length);
            ls.add(new homee(c.getString(c.getColumnIndex(Database.user_chat._ID)),c.getString(c.getColumnIndex(Database.user_chat.name)),
                    c.getString(c.getColumnIndex(Database.user_chat.time)),
                    c.getString(c.getColumnIndex(Database.user_chat.text)),
                    img));
            System.out.println(c.getString(c.getColumnIndex(Database.users.name)));


        }













//        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.girl)
//                + '/' + getResources().getResourceTypeName(R.drawable.girl) + '/' + getResources().getResourceEntryName(R.drawable.girl) );
//
//
//
//
//        ls.add(new homee("Janet Fowler","now","I'm going to San Fransisco...",imageUri));
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.boy)
//                + '/' + getResources().getResourceTypeName(R.drawable.boy) + '/' + getResources().getResourceEntryName(R.drawable.boy) );
//
//        ls.add(new homee("Jason Boyd","16:00","Sounds Good.",imageUri));
//        ls.add(new homee("Nicholas Dunn","9:10","See you there",imageUri));
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.girl)
//                + '/' + getResources().getResourceTypeName(R.drawable.girl) + '/' + getResources().getResourceEntryName(R.drawable.girl) );
//
//        ls.add(new homee("Carol Clark","Mon","You Sent a sticker",imageUri));
//        ls.add(new homee("Ann Carol","Mon","Dinner Tonight?",imageUri));
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getResources().getResourcePackageName(R.drawable.boy)
//                + '/' + getResources().getResourceTypeName(R.drawable.boy) + '/' + getResources().getResourceEntryName(R.drawable.boy) );
//
//        ls.add(new homee("Jeffery Lawrence","Mon","Thats Work for me",imageUri));






        Adopter1 adapter =new Adopter1(ls,getContext());
        RecyclerView.LayoutManager lm= new LinearLayoutManager( getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);







        return view;
    }
}
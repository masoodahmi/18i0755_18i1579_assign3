package com.masoodahmad.i180755_i181579;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contactsfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contactsfragment extends Fragment {
    List<contacts>ls;
    RecyclerView rv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contactsfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contactsfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contactsfragment newInstance(String param1, String param2) {
        contactsfragment fragment = new contactsfragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contactsrv, container, false);
        rv=view.findViewById(R.id.rvvv);
        ls=new ArrayList<>();


        checkPermission();
        Adopter2  adapter =new Adopter2(ls,getContext());
        RecyclerView.LayoutManager lm= new LinearLayoutManager( getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);





        return view;
    }




    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);


        }
        else{
            getContactsList();
        }


    }


    private void getContactsList(){

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor c=getActivity().getContentResolver().query(
                uri,null,null,null,sort
        );
        if(c.getCount()>0){

            while (c.moveToNext()){
                @SuppressLint("Range") String id=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                Uri uriPhone=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection=ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" =?";

                Cursor pc=getActivity().getContentResolver().query(uriPhone,null,selection,new String[]{id},null);
                 if(pc.moveToNext()){
                     @SuppressLint("Range") String number= pc.getString(pc.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    ls.add(new contacts(name,number));
                    pc.close();


                 }
            }
            c.close();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getContactsList();
        }
        else {
            Toast.makeText(getContext(), "Permission denied!!!", Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }
}
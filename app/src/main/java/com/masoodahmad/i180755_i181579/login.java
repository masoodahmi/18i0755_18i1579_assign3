package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.io.ByteArrayOutputStream;

public class login extends AppCompatActivity {
    TextView reg;
    EditText logemail,logpass;
    ImageButton logbtn;
    private FirebaseAuth mAuth;
    SManager sessionManager;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SManager(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();
        reg=findViewById(R.id.registerbtn);
        logemail=findViewById(R.id.logemail);
        logpass=findViewById(R.id.logpass);
        logbtn=findViewById(R.id.logbtn);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //GetData();
                String email=  logemail.getText().toString().trim();
                String pass =  logpass.getText().toString().trim();
                if(email.isEmpty()){
                    logemail.setError("Email is required!!");
                    logemail.requestFocus();
                    return;
                }
                if(pass.isEmpty()){
                    logpass.setError("Password is required!!");
                    logpass.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sessionManager.setLogin(true);
                            sessionManager.setUsername(email);
                            Intent intent=new Intent(login.this, home.class);
                            startActivity(intent );
                            finish();
                        }
                        else{
                            Toast.makeText(login.this,"login Failed!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, SignUp.class);
                startActivity(intent );
                finish();
            }
        });

        if (sessionManager.getLogin()){
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
        }

    }



//    @SuppressLint("Range")
////    void GetData(){
////        boolean flag=false;
////        DbHelper dbh= new DbHelper(login.this);
////        SQLiteDatabase db=dbh.getReadableDatabase();
////        Cursor c=db.rawQuery("select * from " + Database.users.tablename,null);
////        while(c.moveToNext()){
////            if(logemail.getText().toString().equals(c.getString(2)) && logpass.getText().toString().equals(c.getString(3)) )
////            {
////                Toast.makeText(login.this, "Log in Successfull!!!", Toast.LENGTH_SHORT).show();
////                flag=true;
////                Intent intent=new Intent(login.this, home.class);
////                intent.putExtra("id",c.getString(0));
////                System.out.println(c.getString(0));
////                startActivity(intent );
////                finish();
////                break;
////            }
////
////        }
////        if(flag==false) {
////            Toast.makeText(login.this, "Log in Failed!!!", Toast.LENGTH_SHORT).show();
////        }
////
////
////    }

}
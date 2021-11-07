package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextView log;
    EditText signemail,signpass,signcofirmpass;
    ImageButton signupbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        log=findViewById(R.id.loginbtn);
        signemail=findViewById(R.id.signemail);
        signpass=findViewById(R.id.signpass);
        signcofirmpass=findViewById(R.id.signconfirmpass);
        signupbtn=findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signpass.getText().toString().equals(signcofirmpass.getText().toString())  ){
//                    DbHelper dbh= new DbHelper(SignUp.this);
//                    SQLiteDatabase db=dbh.getWritableDatabase();
//                    ContentValues cv=new ContentValues();
//                    cv.put(Database.users._EMAIL,signemail.getText().toString());
//                    cv.put(Database.users._PASSWORD,signpass.getText().toString());
//                    db.insert(Database.users.tablename,null,cv);
//                    db.close();
//                    dbh.close();

                    String email=  signemail.getText().toString().trim();
                    String pass =  signpass.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                users user=new users(email,pass,"masood","21","M",1);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser()
                                .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Intent intent=new Intent(SignUp.this, login.class);
                                            startActivity(intent );
                                            finish();
                                            Toast.makeText(SignUp.this, "Sign Up Successfull!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(SignUp.this, "Sign Up UnSuccessfull!!!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(SignUp.this, "Sign Up UnSuccessfull!!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUp.this, "Password Does not match!!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });



        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this, login.class);
                startActivity(intent );
                finish();
            }
        });

    }
}
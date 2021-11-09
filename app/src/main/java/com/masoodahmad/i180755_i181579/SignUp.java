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

    EditText signemail,signpass,signcofirmpass;
    ImageButton createbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        signemail=findViewById(R.id.signemail);
        signpass=findViewById(R.id.signpass);
        signcofirmpass=findViewById(R.id.signconfirmpass);
        createbtn=findViewById(R.id.createbtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signpass.getText().toString().equals(signcofirmpass.getText().toString()) ){
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
                    String cpass =  signcofirmpass.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                if(email.isEmpty()){
                                    signemail.setError("This should not be empty");
                                    return;
                                }
                                if(pass.isEmpty()){
                                    signpass.setError("This should not be empty");
                                    return;
                                }
                                if(cpass.isEmpty()){
                                    signcofirmpass.setError("This should not be empty");
                                    return;
                                }

                                Intent intent=new Intent(getApplicationContext(), CreateProfile.class);
                                intent.putExtra("email",signemail.getText().toString().trim());
                                intent.putExtra("pass",signpass.getText().toString().trim());
                                startActivity(intent );
                                finish();
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




    }
}
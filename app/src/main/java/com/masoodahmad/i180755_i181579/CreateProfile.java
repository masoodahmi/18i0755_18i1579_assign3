package com.masoodahmad.i180755_i181579;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateProfile extends AppCompatActivity {
    CircleImageView img;
    EditText name,age,gender,phno;
    TextView log;
    FirebaseDatabase database;
    DatabaseReference ref;
    Uri getimg;
    ImageButton signup;
    String dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        log=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.signupbtn);
        name=findViewById(R.id.username);
        age=findViewById(R.id.userage);
        gender=findViewById(R.id.usergender);
        phno=findViewById(R.id.userphno);
        img=findViewById(R.id.uplaodimg);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("users");
        Intent getinfo=getIntent();
        String email=getinfo.getStringExtra("email");
        String pass=getinfo.getStringExtra("pass");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference strRef= FirebaseStorage.getInstance().getReference();
                strRef=strRef.child("pictures/"+email+".jpg");
                strRef.putFile(getimg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> t=taskSnapshot.getStorage().getDownloadUrl();
                        t.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                dp=uri.toString();
                                ref.push().setValue(new users(email,pass,name.getText().toString().trim(),age.getText().toString().trim()
                                        ,gender.getText().toString().trim(),dp,phno.getText().toString().trim()));
                                startActivity(new Intent(getApplicationContext(),login.class));
                                finish();
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


        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult( gallery , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK ){
            getimg= data.getData();
            img.setImageURI(getimg);

        }



    }
}
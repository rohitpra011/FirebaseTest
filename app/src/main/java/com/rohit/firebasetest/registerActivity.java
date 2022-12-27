package com.rohit.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class registerActivity extends AppCompatActivity {
/*private EditText email;
private EditText password;
private Button register;*/
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
       Button register=findViewById(R.id.reg);
       auth= FirebaseAuth.getInstance();

        //to display back button on top
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail=email.getText().toString();
                String textPassword=password.getText().toString();
                if (TextUtils.isEmpty(textEmail)||TextUtils.isEmpty(textPassword)){
                    Toast.makeText(registerActivity.this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
                }else if (textPassword.length()<6){
                    Toast.makeText(registerActivity.this, "Password must be of minimum 6 characters", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(textEmail,textPassword);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



    private void registerUser(String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                  //  Toast.makeText(registerActivity.this, "New user registration successful", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(registerActivity.this, MainActivity.class));
                   // finish();
                    sendEmailVerification();
                }

                //else{
                 //   Toast.makeText(registerActivity.this, "Registration Failed Try again later", Toast.LENGTH_SHORT).show();
               // }
            }
        });
    }



    //email verification
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(registerActivity.this, "Verification email sent. Verify and login again ", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    finish();
                }
            });
        }
        else{
            Toast.makeText(registerActivity.this, "Verification failed", Toast.LENGTH_SHORT).show();
            }
    }

}




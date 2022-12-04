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
    private void registerUser(String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(registerActivity.this, "New user registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registerActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(registerActivity.this, "Registration Failed Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
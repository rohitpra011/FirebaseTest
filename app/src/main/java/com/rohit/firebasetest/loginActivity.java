package com.rohit.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
private FirebaseAuth auth;
    private EditText Email;
    private EditText Password;
  //  private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* EditText Email=findViewById(R.id.email);
        EditText Password=findViewById(R.id.password);
        Button Login=findViewById(R.id.login);*/
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
      //  Login=findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();
     /*   Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email=Email.getText().toString();
                String text_password=Password.getText().toString();
                loginUser(text_email,text_password);
            }
        });*/
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(loginActivity.this, "Login SuccessFul", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    public void Login(View view) {
        String text_email=Email.getText().toString();
        String text_password=Password.getText().toString();
        loginUser(text_email,text_password);
    }
}
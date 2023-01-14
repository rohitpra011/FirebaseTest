package com.rohit.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class loginActivity extends AppCompatActivity {
private FirebaseAuth auth;
    private EditText Email;
    private EditText Password;
  private TextView resetpasvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    //to display back button on top
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



       /* EditText Email=findViewById(R.id.email);
        EditText Password=findViewById(R.id.password);
        Button Login=findViewById(R.id.login);*/
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        resetpasvar=findViewById(R.id.resetpass);
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

        resetpasvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,forget_password.class));
            }
        });

    }

//to enable back button on top
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    checkEmailVarification();
                }
                else
                {
                    Toast.makeText(loginActivity.this, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(loginActivity.this, "Login SuccessFul", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(loginActivity.this,MainActivity.class));
//                finish();
//            }
//        });
    }

    public void Login(View view) {
        String text_email=Email.getText().toString();
        String text_password=Password.getText().toString();

        if (TextUtils.isEmpty(text_email)||TextUtils.isEmpty(text_password)){
            Toast.makeText(loginActivity.this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
        }else if (text_password.length()<6){
            Toast.makeText(loginActivity.this, "Password must be of minimum 6 characters", Toast.LENGTH_SHORT).show();
        }else{
            loginUser(text_email,text_password);
        }



    }

   public void checkEmailVarification()
   {
       FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    /*To avoid Null Pointer Exception*/   assert firebaseUser != null;
       if(firebaseUser.isEmailVerified())
       {
           Toast.makeText(this, "Logged in ", Toast.LENGTH_SHORT).show();
           finish();
           startActivity(new Intent(loginActivity.this,MainActivity.class));
       }
       else
       {
           Toast.makeText(loginActivity.this, "Please check email and verify your account ", Toast.LENGTH_SHORT).show();
       }
   }
}
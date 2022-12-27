package com.rohit.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
    private EditText regmailvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        regmailvar=findViewById(R.id.regmail);
        Button resetbuttonvar = findViewById(R.id.resetbutton);
        Button backtologinvar = findViewById(R.id.backtologin);
        //to handle back to login button
        backtologinvar.setOnClickListener(view -> startActivity(new Intent(forget_password.this,loginActivity.class)));


        //to handle resetbutton
        resetbuttonvar.setOnClickListener(view -> {
            String mail=regmailvar.getText().toString().trim();
            if(mail.isEmpty())
                {
                    Toast.makeText(forget_password.this, "Enter your registered email", Toast.LENGTH_SHORT).show();
                }
            else
                {

                    FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(forget_password.this, "Password resetting link sent on registered email(Please check spam folder if email is not found in inbox)", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(forget_password.this, "Can't reset password, please try after some time.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });



                }
        });

    }

//    public void backToLogin(View view) {
//        startActivity(new Intent(forget_password.this,loginActivity.class));
//    }

}
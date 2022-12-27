package com.rohit.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,StartActivity.class));
            }
        });
      //  FirebaseDatabase.getInstance().getReference().child("Rohit Prajapati").child(" VIT ").setValue("Student");
    text=findViewById(R.id.TextToBeAdded);
    add=findViewById(R.id.AddButton);
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String text_add= text.getText().toString();
            if(text_add.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please type some text", Toast.LENGTH_SHORT).show();
                }
            else{
                    FirebaseDatabase.getInstance().getReference().child("Rohit Prajapati").push().setValue(text_add);
                }
        }
    });

    }
}
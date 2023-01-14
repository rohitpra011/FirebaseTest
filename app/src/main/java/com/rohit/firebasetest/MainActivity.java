package com.rohit.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button add;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String userID;
    
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

                    FirebaseDatabase.getInstance().getReference().child("Rohit").push().setValue(text_add).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity.this, "Data sent to the server successfully", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
        }
    });

    
    firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    reference=FirebaseDatabase.getInstance().getReference("Users");
    userID= firebaseUser.getUid(); 
    final TextView nameofuservar= findViewById(R.id.nameOfUser);
    final TextView mailofuservar= findViewById(R.id.mailOfUser);
    final TextView ageofuservar= findViewById(R.id.ageOfUser);
    reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            UserData userProfile=snapshot.getValue(UserData.class);
                    if(userProfile!=null)
                    {
                        String fullname=userProfile.fullName;
                        String email= userProfile.email;
                        String age= userProfile.age;
                        
                        nameofuservar.setText(fullname);
                        mailofuservar.setText(email);
                        ageofuservar.setText(age);
                    }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
        }
    });
    
    
    
    
    
    
    
    
    
    
    
    
    }
}
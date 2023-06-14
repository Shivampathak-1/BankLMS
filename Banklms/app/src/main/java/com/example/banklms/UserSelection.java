package com.example.banklms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserSelection extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://banklmsdb-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselection);

        databaseReference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Num = "Admin12";
                databaseReference.child("users").child(Num).child("FullName").setValue("Bank Admin1");
                databaseReference.child("users").child(Num).child("Email").setValue("admin1@bank.in");
                databaseReference.child("users").child(Num).child("Password").setValue("Admin@987");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Customer(View v){
        Intent cust = new Intent(this,MainActivity.class);
        startActivity(cust);
    }
    public void Bank(View v){
        Intent bank = new Intent(this,BankLogin.class);
        startActivity(bank);
    }

}
package com.example.banklms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BankLogin extends AppCompatActivity {

    TextInputLayout layout1,layout2;
    TextInputEditText editText1,editText2;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://banklmsdb-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_login);

        layout1 = findViewById(R.id.bankusername1);
        layout2 = findViewById(R.id.bankpass1);
        editText1 = findViewById(R.id.bankuser1);
        editText2 = findViewById(R.id.bankinputpass);

    }

    public void Login1(View view){

        String user = editText1.getText().toString();
        String pass = editText2.getText().toString();

        if(user.equals("") || pass.equals("")){
            Toast.makeText(this, "Please fill required field", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String getName;
                    String getEmail;
                    if(snapshot.hasChild(user)){
                        final String getPass = snapshot.child(user).child("Password").getValue(String.class);

                        if(getPass.equals(pass)){
                            Toast.makeText(BankLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                            SharedPreferences prefs = getSharedPreferences("Banklogin", MODE_PRIVATE);
                            SharedPreferences.Editor editors = prefs.edit();

                            editors.putBoolean("flags", true);
                            editors.apply();

                            Intent intent = new Intent(BankLogin.this,BankHome.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(BankLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(BankLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
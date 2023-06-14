package com.example.banklms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionController;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextInputLayout layout1,layout2;
    TextInputEditText editText1,editText2;
//    DataBaseConnecter db;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://banklmsdb-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.pass1);
        layout2 = findViewById(R.id.username1);
        editText1 = findViewById(R.id.inputpass);
        editText2 = findViewById(R.id.user1);
//        db = new DataBaseConnecter(this);

    }
    public void SignUp(View v){
        Intent b = new Intent(this,MainActivity2.class);
        startActivity(b);
    }
    public void Login(View v){
       String user = editText2.getText().toString();
       String pass = editText1.getText().toString();


       if(user.equals("") || pass.equals("")){
           Toast.makeText(this, "Please fill required field", Toast.LENGTH_SHORT).show();
       }
       else{
//           Boolean checkuserpass = db.checkusernamePassword(user, pass);
//           if(checkuserpass==true){
//               Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//               SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
//               SharedPreferences.Editor editor = pref.edit();
//
//               editor.putBoolean("flag", true);
//               editor.apply();
//
//               Intent logTohome = new Intent(this,Navigation3.class);
//               startActivity(logTohome);
//           }
//           else{
//               Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//           }

           databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   String getName;
                   String getEmail, getNumber, getAge;
                   if(snapshot.hasChild(user)){
                       final String getpassfromfirebase = snapshot.child(user).child("Password").getValue(String.class);
                       getName = snapshot.child(user).child("FullName").getValue(String.class);
                       getEmail = snapshot.child(user).child("Email").getValue(String.class);
                       getNumber = snapshot.child(user).child("Number").getValue(String.class);
                       getAge = snapshot.child(user).child("Age").getValue(String.class);

                       SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                       SharedPreferences.Editor editor = prefs.edit();
                       editor.putString("Username",getName);
                       editor.putString("Usermail",getEmail);
                       editor.putString("Usernumber",getNumber);
                       editor.putString("Userage",getAge);
//                       editor.putLong("Usernumber",getNumber);
                       editor.commit();

                       if(getpassfromfirebase.equals(pass)){
                           Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                           SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                           SharedPreferences.Editor editors = pref.edit();

                           editors.putBoolean("flag", true);
                           editors.apply();


                           Intent logTohome = new Intent(MainActivity.this,Navigation3.class);
//                           logTohome.putExtra("ExtraName",getName);
//                           logTohome.putExtra("ExtraEmail",getEmail);
                           startActivity(logTohome);
                           finish();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }

    }
}
package com.example.banklms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class MainActivity2 extends AppCompatActivity {
    Spinner GenSpin;
    DataBaseConnecter db;
    TextInputLayout layout3, layout4;
    TextInputEditText editText3, editText4;
    EditText email,name,number,ages;
    String[] Gender = {"Male","Female","Other"};
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://banklmsdb-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        db = new DataBaseConnecter(this);
        layout3 = findViewById(R.id.regpass1);
        layout4 = findViewById(R.id.regpass2);
        editText3 = findViewById(R.id.inputpass1);
        editText4 = findViewById(R.id.inputpass2);
        email = findViewById(R.id.editTextTextEmailAddress);
        name = findViewById(R.id.editTextTextPersonName2);
        number = findViewById(R.id.editTextPhone);
        ages = findViewById(R.id.editText);
        GenSpin = findViewById(R.id.spinner3);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_spinner_item,Gender);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenSpin.setAdapter(adapter2);
        GenSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity2.this, value2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String passwordInput = s.toString();
                if(passwordInput.length()>=8){
                    Pattern pattern1 = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern1.matcher(passwordInput);
                    boolean passwordsMatch = matcher.find();
                    if(passwordsMatch){
                        layout3.setHelperText("Your Password is strong");

                        layout3.setError("");
                    }
                    else{
                        layout3.setError("mix of letters(upper and lower case), number and symbols");
                    }
                }else{
                    layout3.setHelperText("Password must 8 characters long");
                    layout3.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void Register(View v){
        String user = email.getText().toString();
        String pass = editText3.getText().toString();
        String repass = editText4.getText().toString();
        String fullName = name.getText().toString();
        String Num = number.getText().toString();
        String age = ages.getText().toString();

        if(user.equals("") || pass.equals("") || repass.equals("") || fullName.equals("") || Num.equals("") || age.equals("")){
            Toast.makeText(this, "please enter required fields", Toast.LENGTH_SHORT).show();
        }
        else{
            if(pass.equals(repass)){
                //Boolean checkuser = db.checkusername(user);

//               if(checkuser==false){
//                    Boolean insert = db.insertData(user,pass);
//                    if(insert==true){
//                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                        Intent regToLog = new Intent(this,MainActivity.class);
//                        startActivity(regToLog);
//                        Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    Toast.makeText(this, "You have already registered please login", Toast.LENGTH_SHORT).show();
//                }

                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(Num)){
                            Toast.makeText(MainActivity2.this, "Email registered please login", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseReference.child("users").child(Num).child("FullName").setValue(fullName);
                            databaseReference.child("users").child(Num).child("Email").setValue(user);
                            databaseReference.child("users").child(Num).child("Password").setValue(pass);
                            databaseReference.child("users").child(Num).child("Age").setValue(age);
                            databaseReference.child("users").child(Num).child("Number").setValue(Num);
                            Toast.makeText(MainActivity2.this, "Registered Successfully Please Login", Toast.LENGTH_SHORT).show();
                            Intent regToLog = new Intent(MainActivity2.this,MainActivity.class);
                            startActivity(regToLog);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
            else{
                Toast.makeText(this, "Password Not Matched", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
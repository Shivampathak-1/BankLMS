package com.example.banklms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences prefs = getSharedPreferences("Banklogin", MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag", false);
                Boolean bankcheck = prefs.getBoolean("flags", false);
                Intent inext;

                if(check){
                    inext = new Intent(Splash_Screen.this,Navigation3.class);

                }
                else if(bankcheck){
                    inext = new Intent(Splash_Screen.this,BankHome.class);
                }
                else {
                    inext = new Intent(Splash_Screen.this, UserSelection.class);
                }
                startActivity(inext);
                finish();
            }
        },3000);
    }
}

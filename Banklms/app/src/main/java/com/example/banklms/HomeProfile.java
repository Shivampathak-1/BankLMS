package com.example.banklms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HomeProfile extends AppCompatActivity {

    TextView homeProfileName1, homeProfileName2, homeProfileEmail1, homeProfileEmail2, homeProfileNumber, homeProfileAge, homeProfileGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profile);

        homeProfileName1 = findViewById(R.id.homeprofname1);
        homeProfileName2 = findViewById(R.id.homeprofname2);
        homeProfileEmail1 = findViewById(R.id.homeprofemail1);
        homeProfileEmail2 = findViewById(R.id.homeprofemail2);
        homeProfileNumber = findViewById(R.id.homeprofnum);
        homeProfileAge = findViewById(R.id.homeprofage);
        homeProfileGender = findViewById(R.id.homeprofgen);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        homeProfileName1.setText(prefs.getString("Username",""));
        homeProfileName2.setText(prefs.getString("Username",""));
        homeProfileEmail1.setText(prefs.getString("Usermail",""));
        homeProfileEmail2.setText(prefs.getString("Usermail",""));
        homeProfileNumber.setText(prefs.getString("Usernumber",""));
        homeProfileAge.setText(prefs.getString("Userage",""));

    }
}
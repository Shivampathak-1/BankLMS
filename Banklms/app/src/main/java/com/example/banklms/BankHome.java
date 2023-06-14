package com.example.banklms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BankHome extends AppCompatActivity {

    NavigationView nav1;
    ActionBarDrawerToggle toggle1;
    DrawerLayout drl1;
    Toolbar tb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_home);

        tb1 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb1);
        nav1=(NavigationView)findViewById(R.id.navmenu2);
        drl1 = (DrawerLayout)findViewById(R.id.drawer1);
        toggle1=new ActionBarDrawerToggle(this,drl1,tb1,R.string.open,R.string.close);
        drl1.addDrawerListener(toggle1);
        toggle1.syncState();

        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId()) {
                    case R.id.menu_home1:
                        Toast.makeText(BankHome.this, "Home panel is open", Toast.LENGTH_LONG).show();
                        drl1.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_setting1:
                        Toast.makeText(BankHome.this, "Setting panel is open", Toast.LENGTH_LONG).show();
                        drl1.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_LogOut1:
                        Toast.makeText(BankHome.this, "LoggedOut Sucessfully", Toast.LENGTH_LONG).show();
                        drl1.closeDrawer(GravityCompat.START);
                        SharedPreferences prefs = getSharedPreferences("Banklogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("flags", false);
                        editor.apply();
                        Intent outToin = new Intent(BankHome.this,UserSelection.class);
                        startActivity(outToin);
                        break;
                }


                return false;
            }
        });

    }

    public void ApplyLoanBank(View view){
        Intent intent = new Intent(this,BankLoanReq.class);
    }
}
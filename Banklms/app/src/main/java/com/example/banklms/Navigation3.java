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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Navigation3 extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drl;
    Toolbar tb;
    TextView textView1,textView2;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation3);

        tb=(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(tb);
        nav=(NavigationView)findViewById(R.id.navmenu1);

        view = nav.getHeaderView(0);
        textView1 = view.findViewById(R.id.profname1);
        textView2 = view.findViewById(R.id.profmail1);


        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        String nameOfUser = prefs.getString("Username","");
        String mailOfUser = prefs.getString("Usermail","");
        textView1.setText(nameOfUser);
        textView2.setText(mailOfUser);

        drl = (DrawerLayout)findViewById(R.id.drawer);
        
        toggle=new ActionBarDrawerToggle(this,drl,tb,R.string.open,R.string.close);
        drl.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) 
            {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Toast.makeText(Navigation3.this, "Home panel is open", Toast.LENGTH_LONG).show();
                        drl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_setting:
                        Toast.makeText(Navigation3.this, "Setting panel is open", Toast.LENGTH_LONG).show();
                        drl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_LogOut:
                        Toast.makeText(Navigation3.this, "LoggedOut Sucessfully", Toast.LENGTH_LONG).show();
                        drl.closeDrawer(GravityCompat.START);
                        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putBoolean("flag", false);
                        editor.apply();

                        Intent outToin = new Intent(Navigation3.this,UserSelection.class);
                        startActivity(outToin);
                        break;
                }
                return true;
            }
        });


    }
    public void LoanCalc(View v){
        Intent lc = new Intent(this,LoanCalculator.class);
        startActivity(lc);
    }
    public void LoanInfo(View v){
        Intent lI = new Intent(this,LoanInformation.class);
        startActivity(lI);
    }
    public void HelpCen(View v){
        Intent HC = new Intent(this,HelpCentre.class);
        startActivity(HC);
    }
    public void Faq(View v){
        Intent faq = new Intent(this,FAQ.class);
        startActivity(faq);
    }
    public void Profile(View v){
        Intent prof = new Intent(this,HomeProfile.class);
        startActivity(prof);
    }

    public void ApplyLoan(View view){
        Intent applyLoan = new Intent(this,ApplyLoan.class);
        startActivity(applyLoan);
    }

}
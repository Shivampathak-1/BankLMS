package com.example.banklms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FAQ extends AppCompatActivity {

    TextView detail1,detail2,detail3,detail4;
    LinearLayout Layout1,Layout2,Layout3,Layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        detail1 = findViewById(R.id.lmsdetails);
        Layout1 = findViewById(R.id.layou1);
        detail2 = findViewById(R.id.lmsdetails2);
        Layout2 = findViewById(R.id.layou2);
        detail3 = findViewById(R.id.lmsdetails3);
        Layout3 = findViewById(R.id.layou3);
        detail4 = findViewById(R.id.lmsdetails4);
        Layout4 = findViewById(R.id.layou4);

    }

    public void Expand1(View view){
        int v = (detail1.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(Layout1, new AutoTransition());
        detail1.setVisibility(v);

    }

    public void Expand2(View view){
        int v = (detail2.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(Layout2, new AutoTransition());
        detail2.setVisibility(v);

    }

    public void Expand3(View view){
        int v = (detail3.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(Layout3, new AutoTransition());
        detail3.setVisibility(v);

    }

    public void Expand4(View view){
        int v = (detail4.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(Layout4, new AutoTransition());
        detail4.setVisibility(v);

    }




}
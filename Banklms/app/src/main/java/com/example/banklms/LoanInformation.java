package com.example.banklms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class LoanInformation extends AppCompatActivity {

    Spinner spinner1, spinner2;
    ImageView imageView;
    TextView textView1, textView2;
    String[] bankname = {"State bank of India","HDFC bank", "Union Bank of India", "Axis Bank", "Canara Bank", "Punjab National Bank","ICICI Bank","Bank Of Baroda"};
    String[] loantype = {"Home Loan","Personal Loan", "Student Loan", "Business Loan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_information);

        spinner1 = findViewById(R.id.choosebankinformationspinner);
        spinner2 = findViewById(R.id.loaninformationspinner);
        textView1 = findViewById(R.id.decription);
        textView2 = findViewById(R.id.loantypedescrition);
        imageView = findViewById(R.id.logoimage);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LoanInformation.this, android.R.layout.simple_spinner_item,bankname);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value1 = parent.getItemAtPosition(position).toString();

                switch (value1){

                    case "State bank of India":
                        imageView.setImageResource(R.drawable.sbilogo);
                        textView1.setText("State Bank of India (SBI) is a leading public sector bank in India that offers a wide range of loan products to its customers. Some of the popular loan products offered by SBI include:");
                        textView2.setText("");
                        break;

                    case "Axis Bank":
                        imageView.setImageResource(R.drawable.axisbanklogo);
                        textView1.setText("About AXIS BANK Axis Bank is one of the first new generation private sector banks to have begun operations in 1994. The Bank was promoted in 1993.");
                        textView2.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(LoanInformation.this, android.R.layout.simple_spinner_item,loantype);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value2 = parent.getItemAtPosition(position).toString();

                switch (value2){

                    case "Home Loan":
                        textView2.setText("SBI offers home loans to individuals who wish to purchase or construct a new home. The bank also offers loans for the renovation and repair of existing homes. The interest rate on home loans starts from 6.70% p.a. and can go up to 7.50% p.a. depending on the loan amount and tenure");
                        break;

                    case "Personal Loan":
                        textView2.setText("Axis Bank Personal Loans offer fast processing of your loan. Personal Loans can be used for plenty of purposes! They can be availed when you are planning a vacation, renovating your home or arranging a dream wedding for yourself or somebody special in the family at starting interest rate of 10.45%.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
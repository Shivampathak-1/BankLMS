package com.example.banklms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ApplyLoan extends AppCompatActivity {

    Spinner spinner1, spinner2,spinner3;
    TextView textView;
    EditText editText;
    String[] bankname = {"State bank of India","HDFC bank", "Union Bank of India", "Axis Bank", "Canara Bank", "Punjab National Bank","ICICI Bank","Bank Of Baroda"};
    String[] loantype = {"Personal Loan", "Student Loan", "Home Loan", "Business Loan"};
    String[] yearofloan = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_loan);

        spinner1 = findViewById(R.id.Choosebankapplyloan);
        spinner2 = findViewById(R.id.Loantypeapplyloan);
        spinner3 = findViewById(R.id.yearloanspinner);
        textView = findViewById(R.id.textView13);
        editText = findViewById(R.id.editText3);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ApplyLoan.this, android.R.layout.simple_spinner_item,bankname);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ApplyLoan.this, android.R.layout.simple_spinner_item,loantype);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value2 = parent.getItemAtPosition(position).toString();

                switch (value2){

                    case "Personal Loan":
                        textView.setText("10%");
                        break;

                    case "Student Loan":
                        textView.setText("8.5%");
                        break;

                    case "Home Loan":
                        textView.setText("12%");
                        break;

                    case "Business Loan":
                        textView.setText("15%");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ApplyLoan.this, android.R.layout.simple_spinner_item,yearofloan);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void ApplyloantoBank(View view){
        String loanamt = editText.toString();
        if(loanamt.equals("")){
            Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Your application is sending to bank", Toast.LENGTH_SHORT).show();
        }
    }
}
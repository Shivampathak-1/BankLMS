package com.example.banklms;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

public class LoanCalculator extends AppCompatActivity {

    Spinner year;
    EditText loanAmount,salary,saving,interest;
    TextView totalInstallment,linedisp,totalamt;
    private int Year;

    String[] bankname = {"State bank of India","HDFC bank", "Union Bank of India", "Axis Bank", "Canara Bank", "Punjab National Bank","ICICI Bank","Bank Of Baroda"};
    String[] loantype = {"Personal Loan", "Student Loan", "Home Loan", "Business Loan"};
    String[] yearofloan = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);


        year = findViewById(R.id.yearspinner);

        loanAmount = findViewById(R.id.editText2);
        salary = findViewById(R.id.incometext);
        saving = findViewById(R.id.savingspinner);
        interest = findViewById(R.id.interestinput);

        totalInstallment = findViewById(R.id.installmentvalue);
        linedisp = findViewById(R.id.linedisplay);
        totalamt = findViewById(R.id.totalamount);
//        interest1 = findViewById(R.id.interestin);




        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(LoanCalculator.this, android.R.layout.simple_spinner_item,yearofloan);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter3);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value3 = parent.getItemAtPosition(position).toString();
                Year = Integer.parseInt(value3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void CalculateLoan(View v){

        String la = loanAmount.getText().toString();
        String sal = salary.getText().toString();
        String sav = saving.getText().toString();
        String rate = interest.getText().toString();

        if(la.equals("") || sal.equals("") || sav.equals("")){
            Toast.makeText(LoanCalculator.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            totalInstallment.setText("");
        }
        else{
            double loanAMT = Integer.parseInt(la);
            double Salaray = Integer.parseInt(sal);
            double Savings = Integer.parseInt(sav);
            double tenure = Year;
            double Intrst = Double.parseDouble(rate);
            double IR = Intrst/1200;
            double remainingsal = Salaray-Savings;

            if(Salaray<Savings){
                Toast.makeText(LoanCalculator.this, "Salary must greater than Savings", Toast.LENGTH_SHORT).show();
            }
            else{
                double totalEMI = (loanAMT*IR*(Math.pow(IR+1,tenure*12)))/((Math.pow(IR+1,tenure*12))-1);
                float totalEmi = (float)totalEMI;

                if(remainingsal>totalEmi){
                    linedisp.setText("You can apply for this loan");
                    linedisp.setTextColor(GREEN);
                    linedisp.setTextSize(20);
                }
                else{
                    linedisp.setText("This loan can be risky for you");
                    linedisp.setTextColor(RED);
                    linedisp.setTextSize(20);
                }

                double totalpayable = totalEMI*tenure*12.0;
                float payableamount = (float)totalpayable;
                String totalamount = String.valueOf(payableamount);
                String totalemi= String.valueOf(totalEmi);
                totalInstallment.setText(totalemi);
                totalamt.setText(totalamount);


            }


        }


    }
}
package com.globomed.julyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name, idNo, number, otp, email, expertise, regNo, password, confirmPsd;
    MaterialButton registerBtn, registerBtnTwo;
    TextInputLayout tPassword, dPassword;
    FirebaseAuth firebaseAuth;
    private Spinner spinner;
    private static final String[] paths = {"Select", "Farmer", "Expert"};
    String phoneNumber, the_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        idNo = findViewById(R.id.id_no);
        number = findViewById(R.id.number);
        otp = findViewById(R.id.otp);
        email = findViewById(R.id.email);
        expertise = findViewById(R.id.expertise);
        regNo = findViewById(R.id.reg_no);
        password = findViewById(R.id.password);
        confirmPsd = findViewById(R.id.confirmPsd);
        registerBtn = findViewById(R.id.register_btn);
        registerBtnTwo = findViewById(R.id.register_btn_two);
        tPassword = findViewById(R.id.t_password);
        dPassword = findViewById(R.id.d_password);

        //Spinner element
        spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        registerBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                email.setVisibility(View.GONE);
                expertise.setVisibility(View.GONE);
                regNo.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                confirmPsd.setVisibility(View.GONE);
                dPassword.setVisibility(View.GONE);
                tPassword.setVisibility(View.GONE);
                registerBtn.setVisibility(View.GONE);
                registerBtnTwo.setVisibility(View.GONE);
                break;
            case 1:
                email.setVisibility(View.GONE);
                expertise.setVisibility(View.GONE);
                regNo.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                confirmPsd.setVisibility(View.GONE);
                dPassword.setVisibility(View.GONE);
                tPassword.setVisibility(View.GONE);
                registerBtn.setVisibility(View.VISIBLE);
                registerBtnTwo.setVisibility(View.GONE);
                break;
            case 2:
                email.setVisibility(View.VISIBLE);
                expertise.setVisibility(View.VISIBLE);
                regNo.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                confirmPsd.setVisibility(View.VISIBLE);
                dPassword.setVisibility(View.VISIBLE);
                tPassword.setVisibility(View.VISIBLE);
                otp.setVisibility(View.GONE);
                registerBtn.setVisibility(View.GONE);
                registerBtnTwo.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



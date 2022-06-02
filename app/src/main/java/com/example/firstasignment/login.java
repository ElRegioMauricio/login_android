package com.example.firstasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    TextView email;
    TextView pass;
    TextView repeatpass;
    ImageView back;
    ImageView next;

    private SharedPreferences sharedPreferences;
    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences("MY_SHARED_PREF",MODE_PRIVATE);

        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        repeatpass = findViewById(R.id.editTextTextPassword2);

        next = findViewById(R.id.imageView9);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        email.setFocusable(true);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail();
                confirmInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePass();
                confirmInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        repeatpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePass();
                confirmInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent2 = new Intent(login.this,Final.class);
                startActivity(intent2);
            }
        });
    }
    public void saveData(){

        String emailInput = email.getText().toString().trim();
        sharedPreferences.edit().putString("Email",emailInput).apply();

    }

    public boolean validateEmail(){
        String emailInput=email.getText().toString().trim();
        String checkString=sharedPreferences.getString("Email",null);
        //que no este vacio
        //que no coincida con email anterior
        //cadena correcta
        if (emailInput.isEmpty()){

            email.setError("This can't be empty");
            return false;
        }
        else if(emailInput.equals(checkString)){
            email.setError("This email is taken");
            return false;
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            email.setError(null);
            email.setHighlightColor(Color.RED);
            return true;
        }
        else{
            email.setError("Type a valid email address");
            return false;
        }

    }
    public boolean validatePass(){
        String passInput=pass.getText().toString().trim();
        String passInputRe=repeatpass.getText().toString().trim();

        if(passInput.isEmpty()){
            pass.setError("This can't be empty");
            return false;
        }
        else if(!passInput.equals(passInputRe)){
            pass.setError(null);
            repeatpass.setError("Password must match");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(passInput).matches()){
            pass.setError("password must be at least 8 characters, 1 uppercase and 1 lowercase");
            return false;
        }
        else{

            repeatpass.setError(null);
            return true;
        }


    }
    public void confirmInput(){


        if(validatePass()==true&&validateEmail()==true){
            next.setVisibility(View.VISIBLE);

        }
        else{
            next.setVisibility(View.INVISIBLE);
        }
    }
}
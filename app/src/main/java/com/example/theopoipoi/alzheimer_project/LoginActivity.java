package com.example.theopoipoi.alzheimer_project;


import android.content.Intent;

import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText edit_text_name,edit_text_password;

    //Declaration Button
    Button sign_in_button;
    Button register_button;

    //Declaration DatabaseHelper
    DatabaseHelper DatabaseHelper;

    //Declaration Intent
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHelper = new DatabaseHelper(this);
        initViews();
        //intent = new Intent(getApplicationContext(), SecondActivity.class);

        //set click event of login button
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Get values from EditText fields
                String Username = edit_text_name.getText().toString();
                String Password = edit_text_password.getText().toString();
                //Check if the password has the right length
                if(Username.matches("")|| Password.length() != 4) {
                    Toast.makeText(getApplicationContext(), "Unsuccessfull request, please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Create a new user to add the values of the editText
                    User currentUser = new User();
                    currentUser.setName(Username);
                    currentUser.setPassword(Integer.parseInt(Password));

                    //Check if this username exists or not
                    if (DatabaseHelper.checkPassword(Username, Password)) {

                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        intent=new Intent(getApplicationContext(), MapsActivity.class);
                        intent.putExtra("name", Username);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Name or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        //If the user want to register, he will be redirected to the register screen
        register_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }

            });

    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        sign_in_button = (Button)findViewById(R.id.login_sign_in_button);
        edit_text_name = (EditText)findViewById(R.id.login_name_editText);
        edit_text_password = (EditText)findViewById(R.id.login_password_editText);
        register_button = (Button)findViewById(R.id.login_register_button);


    }
}


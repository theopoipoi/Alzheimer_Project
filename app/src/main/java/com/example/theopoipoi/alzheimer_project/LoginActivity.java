package com.example.theopoipoi.alzheimer_project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText edit_text_username,edit_text_password;

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
                String Username = edit_text_username.getText().toString();
                String Password = edit_text_password.getText().toString();
                //Check if the password has the right length
                if(Username.matches("")|| Password.length() != 4) {
                    Toast.makeText(getApplicationContext(), "Unsuccessfull request, please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Create a new user to add the values of the editText
                    User currentUser = new User();
                    currentUser.setUsername(Username);
                    currentUser.setPassword(Integer.parseInt(Password));

                    //Check if this username exists or not
                    if (DatabaseHelper.checkUser(Username)) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Successfully Sign in!", Toast.LENGTH_SHORT).show();
                        DatabaseHelper.addUser(currentUser);
                    }
                    //Send the name to the next Activity
                    intent.putExtra("name", currentUser.getUsername());
                    startActivity(intent);
                }




            }
        });


    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        sign_in_button = (Button)findViewById(R.id.email_sign_in_button);
        edit_text_username = (EditText)findViewById(R.id.login_username);
        edit_text_password = (EditText)findViewById(R.id.login_password);
        register_button = (Button)findViewById(R.id.register_button);


    }
}


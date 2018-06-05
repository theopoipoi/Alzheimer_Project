package com.example.theopoipoi.alzheimer_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    //Declaration EditTexts
    EditText edit_text_name,edit_text_firstname, edit_text_phone, edit_text_address, edit_text_password;

    //Declaration Button
    Button register_button;

    //Declaration DatabaseHelper
    DatabaseHelper DatabaseHelper;

    //Declaration Intent
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        DatabaseHelper = new DatabaseHelper(this);
        initViews();

        //set click event of login button
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Get values from EditText fields
                String Name = edit_text_name.getText().toString();
                String Password = edit_text_password.getText().toString();
                String Firstname = edit_text_firstname.getText().toString();
                String address = edit_text_address.getText().toString();
                String phone = edit_text_phone.getText().toString();
                //Check if the password has the right length and all the fields are completed
                if(Name.matches("")|| Password.length() != 4 || Firstname.matches("") || address.matches("") || phone.matches("")) {
                    Toast.makeText(getApplicationContext(), "Unsuccessfull request, please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Create a new user to add the values of the editText
                    User currentUser = new User();
                    currentUser.setName(Name);
                    currentUser.setPassword(Integer.parseInt(Password));
                    currentUser.setFirstname(Firstname);
                    currentUser.setAddress(address);
                    currentUser.setPhone(phone);

                    //Check if this username exists or not
                    if (DatabaseHelper.checkUser(Name) ) {
                        Toast.makeText(getApplicationContext(), "This name is already taken", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Successfully Sign in!", Toast.LENGTH_SHORT).show();
                        DatabaseHelper.addUser(currentUser);
                        //Send the name to the next Activity (Login)
                        //intent.putExtra("name", currentUser.getName());
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                }




            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        edit_text_name = (EditText)findViewById(R.id.registration_editText_name);
        edit_text_firstname = (EditText)findViewById(R.id.editText_firstname);
        edit_text_address = (EditText)findViewById(R.id.editText_address);
        edit_text_password = (EditText)findViewById(R.id.registration_edit_password);
        edit_text_phone = (EditText)findViewById(R.id.editText_phone);
        register_button = (Button)findViewById(R.id.button_register);


    }

}

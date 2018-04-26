package com.example.firozsaifi.mysqldbapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText ET_NAME, ET_USER_NAME, ET_USER_PASS;
    String name,user_name,user_pass;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);

        ET_NAME=(EditText)findViewById(R.id.name);
        ET_USER_NAME=(EditText)findViewById(R.id.new_user_name);
        ET_USER_PASS=(EditText)findViewById(R.id.new_user_pass);

    }

    public void userReg(View v)
    {

        name=ET_NAME.getText().toString();
        user_name=ET_USER_NAME.getText().toString();
        user_pass=ET_USER_PASS.getText().toString();

        if(name.trim().equals("")||user_name.trim().equals("")||user_pass.trim().equals(""))
        {
            alertDialog=new android.app.AlertDialog.Builder(this).create();
            alertDialog.setTitle("Alert!");
            alertDialog.setMessage("Please fill all the fields");
            alertDialog.show();
        }
        else
        {
            String method="register";
            BackgroundTask backgroundTask=new BackgroundTask(this);
            backgroundTask.execute(method,name,user_name,user_pass);
            finish();
        }

    }

}

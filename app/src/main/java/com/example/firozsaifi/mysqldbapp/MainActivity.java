package com.example.firozsaifi.mysqldbapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ET_NAME, ET_PASS;
    String login_name, login_pass;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_NAME=(EditText)findViewById(R.id.user_name);
        ET_PASS=(EditText)findViewById(R.id.user_pass);
    }
    public void userReg(View view)
    {
        startActivity(new Intent(this,Register.class));
    }
    public void userLogin(View view)
    {

        login_name=ET_NAME.getText().toString();
        login_pass=ET_PASS.getText().toString();

        if(login_name.trim().equals("")||login_pass.trim().equals(""))
        {
            alertDialog=new android.app.AlertDialog.Builder(this).create();
            alertDialog.setTitle("Alert!");
            alertDialog.setMessage("Please fill all the fields");
            alertDialog.show();
        }
        else
        {
            String method="login";
            BackgroundTask backgroundTask=new BackgroundTask(this);
            backgroundTask.execute(method,login_name,login_pass);
        }


    }
}

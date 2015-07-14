package com.android.dango.day2ejcolores;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Act_Register extends ActionBarActivity implements View.OnClickListener {


    Button b_reg;
    EditText email;
    EditText password;
    DataBase cDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__register);
        b_reg = (Button) findViewById(R.id.b_reg);
        b_reg.setOnClickListener(this);

        email = (EditText)findViewById(R.id.editEmail);
        password =  (EditText) findViewById(R.id.editPassword);

        cDatabase = new DataBase(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act__register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.b_reg){
            if(cDatabase.userMailTaken(email.getText().toString()))
                Toast.makeText(getApplicationContext(), "Error: User Already Taken", Toast.LENGTH_SHORT).show();
            else {
                ContentValues valuesToStore = new ContentValues();
                valuesToStore.put("mail", email.getText().toString());
                valuesToStore.put("password", password.getText().toString());
                cDatabase.createUser(valuesToStore, "login");

                Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                email.setText("Email");
                password.setText("Password");
                finish();
            }
        }
    }

}

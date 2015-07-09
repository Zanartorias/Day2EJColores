package com.android.dango.day2ejcolores;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Act_Login extends ActionBarActivity implements View.OnClickListener{

    Button b_reg;
    Button b_log;
    EditText email;
    EditText password;
    int i = 0;

    DataBase cDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__login);
        /*b_reg = (Button) findViewById(R.id.b_reg);
        b_log = (Button) findViewById(R.id.b_log);
        b_reg.setOnClickListener(this);
        b_log.setOnClickListener(this);*/

        email = (EditText)findViewById(R.id.editEmail);
        password =  (EditText) findViewById(R.id.editPassword);

        cDatabase = new DataBase(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act__login, menu);
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
        switch(v.getId()){
            case R.id.b_reg:
                Intent intent = new Intent(getApplicationContext(), Act_Register.class);
                startActivity(intent);
                break;
            case R.id.b_log:
                break;
            case R.id.sb_checknest:
                if(i<3)
                    i++;
                else{
                    i = 0;
                    intent = new Intent(getApplicationContext(), Sact_Checknest.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    public void register(String mail, String password){
        DataBase userDb = new DataBase(this);
        SQLiteDatabase db = userDb.getWritableDatabase();
    }
}

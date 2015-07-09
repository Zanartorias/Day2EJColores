package com.android.dango.day2ejcolores;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Sact_Checknest extends ListActivity implements View.OnClickListener{

    EditText passphrase;
    Button b0;
    DataBase cDatabase;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sact__checknest);
        b0 = (Button) findViewById(R.id.b_pass);
        b0.setOnClickListener(this);
        passphrase = (EditText)findViewById(R.id.editText);


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);

        cDatabase = new DataBase(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sact__checknest, menu);
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
        if(v.getId() == R.id.b_pass){
            if(passphrase.equals(String.valueOf("raiz"))){
                /*adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
                setListAdapter(adapter);

                cDatabase = new DataBase(getApplicationContext());*/

                Cursor c = cDatabase.getAllUsers();
                if (c.moveToFirst()) {
                    do {
                        adapter.add(c.getString(c.getColumnIndex("mail")));
                        adapter.add(" // ");
                        adapter.add(c.getString(c.getColumnIndex("password")));
                    } while (c.moveToNext());
                }
            }
        }
    }

}

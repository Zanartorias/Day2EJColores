package com.android.dango.day2ejcolores;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class Sact_Checknest extends ListActivity implements View.OnClickListener{

    EditText passphrase;
    //Button b0;
    DataBase cDatabase;
    //ArrayList<String> listItems=new ArrayList<String>();
    //ArrayAdapter<String> adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sact__checknest);
        //b0 = (Button) findViewById(R.id.b_pass);
        //b0.setOnClickListener(this);
        passphrase = (EditText)findViewById(R.id.editText);


        //adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        //setListAdapter(adapter);

        cDatabase = new DataBase(getApplicationContext());

        ArrayList<String> mails = new ArrayList<String>();
        ArrayList<String> passws = new ArrayList<String>();

        if(cDatabase != null){
            Cursor c = cDatabase.getAllUsers();
            while(c.moveToNext()){
                //adapterViewHolder.icon.setImageDrawable(adapterViewHolder.v.getResources().getDrawable(R.drawable.ic_icon));
                //adapterViewHolder.name.setText(c.getString(c.getColumnIndex("mail")));
                mails.add(c.getString(c.getColumnIndex("mail")));
                passws.add(c.getString(c.getColumnIndex("password")));
            }
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mLinearLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mRecyclerView.setAdapter(new CrowAdapter(mails, passws));


        mRecyclerView.setVisibility(View.INVISIBLE);

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
        switch(v.getId()){
            case R.id.b_pass:
                if(passphrase.equals(String.valueOf("raiz"))){
                    /*adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
                    setListAdapter(adapter);

                    cDatabase = new DataBase(getApplicationContext());*/

                    /*Cursor c = cDatabase.getAllUsers();
                    if (c.moveToFirst()) {
                        do {
                            adapter.add(c.getString(c.getColumnIndex("mail")));
                            adapter.add(" // ");
                            adapter.add(c.getString(c.getColumnIndex("password")));
                        } while (c.moveToNext());
                    }*/
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

}

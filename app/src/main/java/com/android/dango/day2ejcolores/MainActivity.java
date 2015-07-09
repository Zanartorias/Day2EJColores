package com.android.dango.day2ejcolores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button b0;
    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = (Button) findViewById(R.id.b_gotoLog);
        b1 = (Button) findViewById(R.id.b_calc);
        b2 = (Button) findViewById(R.id.b_async);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        Intent intent;
        switch(v.getId()){
            case R.id.b_gotoLog:
                intent = new Intent(getApplicationContext(), Act_Login.class);
                startActivity(intent);
                break;
            case R.id.b_calc:
                intent = new Intent(getApplicationContext(), Calculator.class);
                startActivity(intent);
                break;
            case R.id.b_async:
                intent = new Intent(getApplicationContext(), AsyncTest.class);
                startActivity(intent);
                break;
            case R.id.b_music:
                intent = new Intent(getApplicationContext(), MusicPlayer.class);
                startActivity(intent);
                break;
            case R.id.b_gps:
                intent = new Intent(getApplicationContext(), Act_GPS.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

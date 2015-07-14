package com.android.dango.day2ejcolores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "k1hdzAmkoWHCgTOYjeenQUAJS";
    private static final String TWITTER_SECRET = "wrZqnlEzlftv5MZXNgkpmZuIOqlyTbXhFnkA1iurd75Y1iZyz1";


    /*Button b0;
    Button b1;
    Button b2;*/
    TextView logInfo;

    SharedPreferences user;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        /*b0 = (Button) findViewById(R.id.b_gotoLog);
        b1 = (Button) findViewById(R.id.b_calc);
        b2 = (Button) findViewById(R.id.b_async);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);*/

        logInfo = (TextView) findViewById(R.id.textLogInfo);

        user = getSharedPreferences(String.valueOf(R.string.USER_PREFS), Context.MODE_PRIVATE);
        username = user.getString("USERNAME", null);
        if (username != null)
            logInfo.setText("Logged in as "+username);
        else
            logInfo.setText("Not logged in");


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
        boolean logged = user.getBoolean("LOGGED", false);
        Intent intent;
        switch(v.getId()){
            case R.id.b_gotoLog:
                intent = new Intent(getApplicationContext(), Act_Login.class);
                startActivity(intent);
                break;
            case R.id.b_calc:
                if(logged){
                    intent = new Intent(getApplicationContext(), Calculator.class);
                    startActivity(intent);
                }else
                Toast.makeText(getApplicationContext(), "Not logged in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_async:
                if(logged){
                    intent = new Intent(getApplicationContext(), AsyncTest.class);
                    startActivity(intent);
                }else
                Toast.makeText(getApplicationContext(), "Not logged in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_music:
                if(logged){
                    intent = new Intent(getApplicationContext(), MusicPlayer.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Not logged in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_gps:
                if(logged){
                    intent = new Intent(getApplicationContext(), Act_GPS.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Not logged in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_refresh:
                username = user.getString("USERNAME", null);
                if (username != null)
                    logInfo.setText("Logged in as "+username);
                else
                    logInfo.setText("Not logged in");
                break;
            default:
                break;
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle inRestore){
        super.onRestoreInstanceState(inRestore);
        username = user.getString("USERNAME", null);
        if (username != null)
            logInfo.setText("Logged in as "+username);
        else
            logInfo.setText("Not logged in");
    }

}

package com.android.dango.day2ejcolores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class Act_Login extends ActionBarActivity implements View.OnClickListener{

    Button b_reg;
    Button b_log;
    EditText email;
    EditText password;
    int i = 0;

    DataBase cDatabase;
    private TwitterLoginButton loginButton;

    SharedPreferences user;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__login);
        /*b_reg = (Button) findViewById(R.id.b_reg);
        b_log = (Button) findViewById(R.id.b_log);
        b_reg.setOnClickListener(this);
        b_log.setOnClickListener(this);*/
        user = getSharedPreferences(String.valueOf(R.string.USER_PREFS), 0);
        editor = user.edit();



        email = (EditText)findViewById(R.id.editEmail);
        password =  (EditText) findViewById(R.id.editPassword);

        cDatabase = new DataBase(getApplicationContext());

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                TwitterAuthToken token = session.getAuthToken();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
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
                    if(cDatabase != null){
                    Cursor cursor = cDatabase.getPasswordByEmail(email.getText().toString());
                    if(cursor.moveToFirst()){
                        String pass = cursor.getString(cursor.getColumnIndex("password"));
                        Log.v("Queried PASS", pass);
                        if(pass.equals(password.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            editor.putBoolean("LOGGED", true);
                            editor.apply();
                        }
                    }

                    cDatabase.close();
                }

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

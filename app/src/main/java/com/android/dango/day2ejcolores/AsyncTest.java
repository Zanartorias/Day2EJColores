package com.android.dango.day2ejcolores;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;



public class AsyncTest extends ActionBarActivity implements View.OnClickListener {

    Button b0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_test);
        b0 = (Button) findViewById(R.id.b_cfm);
        b0.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async_test, menu);
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
        switch (v.getId()){
            case R.id.b_cfm:
                CountTest count = new CountTest();
                count.execute();
                break;
            default:
                break;
        }
    }

    private class CountTest extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("task", "on preExecute");
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("task", "on postExecute");
            Toast.makeText(getApplication(), "Finished counting", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            Log.v("async", "Progress Update: " + String.valueOf(values[0]));
            progressBar.setProgress(values[0]);
            if(values[0]%10 == 0){
                final Toast tost = Toast.makeText(getApplication(), String.valueOf(values[0]/10), Toast.LENGTH_SHORT);
                tost.setGravity(Gravity.TOP | Gravity.RIGHT, 0, values[0]*2);
                tost.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tost.cancel();
                    }
                }, 500);
            }else{
                if(values[0] == 99){
                    final Toast tost = Toast.makeText(getApplication(), String.valueOf((values[0]+1)/10), Toast.LENGTH_SHORT);
                    tost.setGravity(Gravity.TOP | Gravity.RIGHT, 0, values[0]*2);
                    tost.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tost.cancel();
                        }
                    }, 500);
                }
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {

            int total = 100;
            int i = 0;
            while (i < total) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.v("thread", "awake");
                i++;
            }
            return null;
        }
    }


}

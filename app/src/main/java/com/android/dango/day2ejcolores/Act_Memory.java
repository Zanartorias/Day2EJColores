package com.android.dango.day2ejcolores;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;


public class Act_Memory extends ActionBarActivity {

    SharedPreferences user;
    SharedPreferences.Editor editor;
    Random rand = new Random();
    int tries = 0;
    int[][] grid = new int [4][4];
    int[] pairUsage = new int[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__memory);
        user = getSharedPreferences(String.valueOf(R.string.USER_PREFS), 0);
        editor = user.edit();

        for (int i = 0; i < 8; i++) {
            pairUsage[i] = 0;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act__memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_logout:
                editor.putBoolean("LOGGED", false);
                editor.putString("USERNAME", null);
                editor.apply();
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public int random(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void iniGrid(){
        int temp;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                do {
                    temp = random(1, 8);
                    if (pairUsage[temp - 1] < 2)
                        grid[i][j] = temp;
                }while(grid[i][j] == 0);
            }
        }
    }
}

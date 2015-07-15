package com.android.dango.day2ejcolores;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Act_Memory extends ActionBarActivity implements View.OnClickListener {

    SharedPreferences user;
    SharedPreferences.Editor editor;
    Random rand = new Random();
    int misses = 0;
    int[][] grid = new int [4][4];
    int[] pairUsage = new int[8];
    int covered = 16;
    int lastImg = 0;
    int lastIv = 88;
    boolean first = true;
    boolean[][] fixed = new boolean[4][4];
    TextView triesTextView;
    ImageView imageView00;
    ImageView imageView01;
    ImageView imageView02;
    ImageView imageView03;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView30;
    ImageView imageView31;
    ImageView imageView32;
    ImageView imageView33;

    FrameLayout frame00;
    FrameLayout frame01;
    FrameLayout frame02;
    FrameLayout frame03;
    FrameLayout frame10;
    FrameLayout frame11;
    FrameLayout frame12;
    FrameLayout frame13;
    FrameLayout frame20;
    FrameLayout frame21;
    FrameLayout frame22;
    FrameLayout frame23;
    FrameLayout frame30;
    FrameLayout frame31;
    FrameLayout frame32;
    FrameLayout frame33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__memory);
        user = getSharedPreferences(String.valueOf(R.string.USER_PREFS), 0);
        editor = user.edit();

        triesTextView = (TextView) findViewById(R.id.triesTextView);

        for (int i = 0; i < 8; i++) {
            pairUsage[i] = 0;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
                fixed[i][j] = false;
            }
        }
        frame00 = (FrameLayout) findViewById(R.id.frame00);
        frame01 = (FrameLayout) findViewById(R.id.frame01);
        frame02 = (FrameLayout) findViewById(R.id.frame02);
        frame03 = (FrameLayout) findViewById(R.id.frame03);

        frame10 = (FrameLayout) findViewById(R.id.frame10);
        frame11 = (FrameLayout) findViewById(R.id.frame11);
        frame12 = (FrameLayout) findViewById(R.id.frame12);
        frame13 = (FrameLayout) findViewById(R.id.frame13);

        frame20 = (FrameLayout) findViewById(R.id.frame20);
        frame21 = (FrameLayout) findViewById(R.id.frame21);
        frame22 = (FrameLayout) findViewById(R.id.frame22);
        frame23 = (FrameLayout) findViewById(R.id.frame23);

        frame30 = (FrameLayout) findViewById(R.id.frame30);
        frame31 = (FrameLayout) findViewById(R.id.frame31);
        frame32 = (FrameLayout) findViewById(R.id.frame32);
        frame33 = (FrameLayout) findViewById(R.id.frame33);

        imageView00 = (ImageView) findViewById(R.id.imageView00);
        imageView00.setVisibility(View.INVISIBLE);
        imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView01.setVisibility(View.INVISIBLE);
        imageView02 = (ImageView) findViewById(R.id.imageView02);
        imageView02.setVisibility(View.INVISIBLE);
        imageView03 = (ImageView) findViewById(R.id.imageView03);
        imageView03.setVisibility(View.INVISIBLE);

        imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView10.setVisibility(View.INVISIBLE);
        imageView11 = (ImageView) findViewById(R.id.imageView11);
        imageView11.setVisibility(View.INVISIBLE);
        imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView12.setVisibility(View.INVISIBLE);
        imageView13 = (ImageView) findViewById(R.id.imageView13);
        imageView13.setVisibility(View.INVISIBLE);

        imageView20 = (ImageView) findViewById(R.id.imageView20);
        imageView20.setVisibility(View.INVISIBLE);
        imageView21 = (ImageView) findViewById(R.id.imageView21);
        imageView21.setVisibility(View.INVISIBLE);
        imageView22 = (ImageView) findViewById(R.id.imageView22);
        imageView22.setVisibility(View.INVISIBLE);
        imageView23 = (ImageView) findViewById(R.id.imageView23);
        imageView23.setVisibility(View.INVISIBLE);

        imageView30 = (ImageView) findViewById(R.id.imageView30);
        imageView30.setVisibility(View.INVISIBLE);
        imageView31 = (ImageView) findViewById(R.id.imageView31);
        imageView31.setVisibility(View.INVISIBLE);
        imageView32 = (ImageView) findViewById(R.id.imageView32);
        imageView32.setVisibility(View.INVISIBLE);
        imageView33 = (ImageView) findViewById(R.id.imageView33);
        imageView33.setVisibility(View.INVISIBLE);

        iniGrid();
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
        return (rand.nextInt((max - min) + 1) + min);
    }
    public void setImage(int iv, int id){
        //int ij = i*10 + j;
        int dw = 0;
        switch(id){
            case 1:
                dw = R.drawable.ic_grid1;
                break;
            case 2:
                dw = R.drawable.ic_grid2;
                break;
            case 3:
                dw = R.drawable.ic_grid3;
                break;
            case 4:
                dw = R.drawable.ic_grid4;
                break;
            case 5:
                dw = R.drawable.ic_grid5;
                break;
            case 6:
                dw = R.drawable.ic_grid6;
                break;
            case 7:
                dw = R.drawable.ic_grid7;
                break;
            case 8:
                dw = R.drawable.ic_grid8;
                break;
            default:
                break;
        }
        switch(iv){
            case 0:
                imageView00.setImageResource(dw);
                break;
            case 1:
                imageView01.setImageResource(dw);
                break;
            case 2:
                imageView02.setImageResource(dw);
                break;
            case 3:
                imageView03.setImageResource(dw);
                break;
            case 10:
                imageView10.setImageResource(dw);
                break;
            case 11:
                imageView11.setImageResource(dw);
                break;
            case 12:
                imageView12.setImageResource(dw);
                break;
            case 13:
                imageView13.setImageResource(dw);
                break;
            case 20:
                imageView20.setImageResource(dw);
                break;
            case 21:
                imageView21.setImageResource(dw);
                break;
            case 22:
                imageView22.setImageResource(dw);
                break;
            case 23:
                imageView23.setImageResource(dw);
                break;
            case 30:
                imageView30.setImageResource(dw);
                break;
            case 31:
                imageView31.setImageResource(dw);
                break;
            case 32:
                imageView32.setImageResource(dw);
                break;
            case 33:
                imageView33.setImageResource(dw);
                break;
            default:
                break;
        }

    }

    public void iniGrid(){
        int temp;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                do {
                    temp = random(1, 8);
                    if (pairUsage[temp - 1] < 2){
                        grid[i][j] = temp;
                        pairUsage[temp - 1]++;
                    }
                }while(grid[i][j] == 0);
                setImage((i*10 + j), grid[i][j]);
            }
        }
        //imageView00.setImageDrawable(ic_grid1);
        //imageView01.setImageDrawable(ic_grid1);
        //imageView02.setImageDrawable(ic_grid1);
        //imageView03.setImageDrawable(ic_grid1);
        //imageView10.setImageDrawable(ic_grid1);
        //imageView11.setImageDrawable(ic_grid1);
        //imageView12.setImageDrawable(ic_grid1);
        //imageView13.setImageDrawable(ic_grid1);
        //imageView20.setImageDrawable(ic_grid1);
        //imageView21.setImageDrawable(ic_grid1);
        //imageView22.setImageDrawable(ic_grid1);
        //imageView23.setImageDrawable(ic_grid1);
        //imageView30.setImageDrawable(ic_grid1);
        //imageView31.setImageDrawable(ic_grid1);
        //imageView32.setImageDrawable(ic_grid1);
        //imageView33.setImageDrawable(ic_grid1);
    }

    @Override
    public void onClick(View v) {
        Handler handler = new Handler();
        if(covered == 0)
            finish();
        switch(v.getId()){
            case R.id.frame00:
                frame00.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView00.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[0][0];                               //CELL DEP
                    lastIv = 0;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[0][0]){                          //CELL DEP
                        fixed[0][0] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);

                }
                break;
            case R.id.frame01:
                frame01.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView01.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[0][1];                               //CELL DEP
                    lastIv = 1;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[0][1]){                          //CELL DEP
                        fixed[0][1] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame02:
                frame02.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView02.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[0][2];                               //CELL DEP
                    lastIv = 2;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[0][2]){                          //CELL DEP
                        fixed[0][2] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame03:
                frame03.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView03.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[0][3];                               //CELL DEP
                    lastIv = 3;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[0][3]){                          //CELL DEP
                        fixed[0][3] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame10:
                frame10.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView10.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[1][0];                               //CELL DEP
                    lastIv = 10;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[1][0]){                          //CELL DEP
                        fixed[1][0] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame11:
                frame11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView11.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[1][1];                               //CELL DEP
                    lastIv = 11;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[1][1]){                          //CELL DEP
                        fixed[1][1] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame12:
                frame12.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView12.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[1][2];                                //CELL DEP
                    lastIv = 12;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[1][2]){                          //CELL DEP
                        fixed[1][2] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame13:
                frame13.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView13.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[1][3];                                //CELL DEP
                    lastIv = 13;                                         //CELL DEP
                    first = false;
                }else{
                    imageView13.setVisibility(View.VISIBLE);            //CELL DEP
                    if(lastImg == grid[1][3]){                          //CELL DEP
                        fixed[1][3] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame20:
                frame20.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView20.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[2][0];                               //CELL DEP
                    lastIv = 20;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[2][0]){                          //CELL DEP
                        fixed[2][0] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame21:
                frame21.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView21.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[2][1];                               //CELL DEP
                    lastIv = 21;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[2][1]){                          //CELL DEP
                        fixed[2][1] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame22:
                frame22.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView22.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[2][2];                               //CELL DEP
                    lastIv = 22;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[2][2]){                          //CELL DEP
                        fixed[2][2] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame23:
                frame23.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView23.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[2][3];                               //CELL DEP
                    lastIv = 23;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[2][3]){                          //CELL DEP
                        fixed[2][3] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame30:
                frame30.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView30.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[3][0];                               //CELL DEP
                    lastIv = 30;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[3][0]){                          //CELL DEP
                        fixed[3][0] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame31:
                frame31.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView31.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[3][1];                               //CELL DEP
                    lastIv = 31;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[3][1]){                          //CELL DEP
                        fixed[3][1] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame32:
                frame32.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView32.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[3][2];                               //CELL DEP
                    lastIv = 32;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[3][2]){                          //CELL DEP
                        fixed[3][2] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            case R.id.frame33:
                frame33.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageView33.setVisibility(View.VISIBLE);
                if(first){
                    lastImg = grid[3][3];                               //CELL DEP
                    lastIv = 33;                                         //CELL DEP
                    first = false;
                }else{
                    if(lastImg == grid[3][3]){                          //CELL DEP
                        fixed[3][3] = true;                             //CELL DEP
                        fixed[lastIv/10][lastIv % 10] = true;
                        first = true;
                        lastImg = 0;
                        lastIv = 88;
                        covered -= 2;
                    }else{
                        first = true;
                        lastIv = 88;
                        lastImg = 0;
                        misses++;
                    }
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            flipCards();// flip = new flipCards();
                            //flip.execute();
                        }
                    }, 1000);
                }
                break;
            default:
                break;
        }
        triesTextView.setText("Misses: "+misses);

        if(covered == 0) {
            if (misses == 1)
                Toast.makeText(getApplicationContext(), "You beat the memory game with " + misses + " miss", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "You beat the memory game with " + misses + " misses", Toast.LENGTH_SHORT).show();

            View.OnClickListener okOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            };
            Snackbar.make(v, "Click OK to go back to the Menu", Snackbar.LENGTH_LONG)
                    .setAction("OK", okOnClickListener)
                    .show();
        }
    }

    public void flipCards(){

        if ((imageView00.getVisibility() == View.VISIBLE)&&(!fixed[0][0])) {
            frame00.setBackgroundResource(R.drawable.ic_ygg);
            imageView00.setVisibility(View.INVISIBLE);
        }
        if((imageView01.getVisibility() == View.VISIBLE)&&(!fixed[0][1])) {
            frame01.setBackgroundResource(R.drawable.ic_ygg);
            imageView01.setVisibility(View.INVISIBLE);
        }
        if((imageView02.getVisibility() == View.VISIBLE)&&(!fixed[0][2])) {
            frame02.setBackgroundResource(R.drawable.ic_ygg);
            imageView02.setVisibility(View.INVISIBLE);
        }
        if((imageView03.getVisibility() == View.VISIBLE)&&(!fixed[0][3])) {
            frame03.setBackgroundResource(R.drawable.ic_ygg);
            imageView03.setVisibility(View.INVISIBLE);
        }

        if((imageView10.getVisibility() == View.VISIBLE)&&(!fixed[1][0])) {
            frame10.setBackgroundResource(R.drawable.ic_ygg);
            imageView10.setVisibility(View.INVISIBLE);
        }
        if((imageView11.getVisibility() == View.VISIBLE)&&(!fixed[1][1])) {
            frame11.setBackgroundResource(R.drawable.ic_ygg);
            imageView11.setVisibility(View.INVISIBLE);
        }
        if((imageView12.getVisibility() == View.VISIBLE)&&(!fixed[1][2])) {
            frame12.setBackgroundResource(R.drawable.ic_ygg);
            imageView12.setVisibility(View.INVISIBLE);
        }
        if((imageView13.getVisibility() == View.VISIBLE)&&(!fixed[1][3])) {
            frame13.setBackgroundResource(R.drawable.ic_ygg);
            imageView13.setVisibility(View.INVISIBLE);
        }

        if((imageView20.getVisibility() == View.VISIBLE)&&(!fixed[2][0])) {
            frame20.setBackgroundResource(R.drawable.ic_ygg);
            imageView20.setVisibility(View.INVISIBLE);
        }
        if((imageView21.getVisibility() == View.VISIBLE)&&(!fixed[2][1])) {
            frame21.setBackgroundResource(R.drawable.ic_ygg);
            imageView21.setVisibility(View.INVISIBLE);
        }
        if((imageView22.getVisibility() == View.VISIBLE)&&(!fixed[2][2])) {
            frame22.setBackgroundResource(R.drawable.ic_ygg);
            imageView22.setVisibility(View.INVISIBLE);
        }
        if((imageView23.getVisibility() == View.VISIBLE)&&(!fixed[2][3])) {
            frame23.setBackgroundResource(R.drawable.ic_ygg);
            imageView23.setVisibility(View.INVISIBLE);
        }

        if((imageView30.getVisibility() == View.VISIBLE)&&(!fixed[3][0])) {
            frame30.setBackgroundResource(R.drawable.ic_ygg);
            imageView30.setVisibility(View.INVISIBLE);
        }
        if((imageView31.getVisibility() == View.VISIBLE)&&(!fixed[3][1])) {
            frame31.setBackgroundResource(R.drawable.ic_ygg);
            imageView31.setVisibility(View.INVISIBLE);
        }
        if((imageView32.getVisibility() == View.VISIBLE)&&(!fixed[3][2])) {
            frame32.setBackgroundResource(R.drawable.ic_ygg);
            imageView32.setVisibility(View.INVISIBLE);
        }
        if((imageView33.getVisibility() == View.VISIBLE)&&(!fixed[3][3])) {
            frame33.setBackgroundResource(R.drawable.ic_ygg);
            imageView33.setVisibility(View.INVISIBLE);
        }
    }

    /*private class flipCards extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
                if((imageView00.getVisibility() == View.VISIBLE)&&(!fixed[0][0]))
                    imageView00.setVisibility(View.INVISIBLE);
                if((imageView01.getVisibility() == View.VISIBLE)&&(!fixed[0][1]))
                    imageView01.setVisibility(View.INVISIBLE);
                if((imageView02.getVisibility() == View.VISIBLE)&&(!fixed[0][2]))
                    imageView02.setVisibility(View.INVISIBLE);
                if((imageView03.getVisibility() == View.VISIBLE)&&(!fixed[0][3]))
                    imageView03.setVisibility(View.INVISIBLE);

                if((imageView10.getVisibility() == View.VISIBLE)&&(!fixed[1][0]))
                    imageView10.setVisibility(View.INVISIBLE);
                if((imageView11.getVisibility() == View.VISIBLE)&&(!fixed[1][1]))
                    imageView11.setVisibility(View.INVISIBLE);
                if((imageView12.getVisibility() == View.VISIBLE)&&(!fixed[1][2]))
                    imageView12.setVisibility(View.INVISIBLE);
                if((imageView13.getVisibility() == View.VISIBLE)&&(!fixed[1][3]))
                    imageView13.setVisibility(View.INVISIBLE);

                if((imageView20.getVisibility() == View.VISIBLE)&&(!fixed[2][0]))
                    imageView20.setVisibility(View.INVISIBLE);
                if((imageView21.getVisibility() == View.VISIBLE)&&(!fixed[2][1]))
                    imageView21.setVisibility(View.INVISIBLE);
                if((imageView22.getVisibility() == View.VISIBLE)&&(!fixed[2][2]))
                    imageView22.setVisibility(View.INVISIBLE);
                if((imageView23.getVisibility() == View.VISIBLE)&&(!fixed[2][3]))
                    imageView23.setVisibility(View.INVISIBLE);

                if((imageView30.getVisibility() == View.VISIBLE)&&(!fixed[3][0]))
                    imageView30.setVisibility(View.INVISIBLE);
                if((imageView31.getVisibility() == View.VISIBLE)&&(!fixed[3][1]))
                    imageView31.setVisibility(View.INVISIBLE);
                if((imageView32.getVisibility() == View.VISIBLE)&&(!fixed[3][2]))
                    imageView32.setVisibility(View.INVISIBLE);
                if((imageView33.getVisibility() == View.VISIBLE)&&(!fixed[3][3]))
                    imageView33.setVisibility(View.INVISIBLE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/
}

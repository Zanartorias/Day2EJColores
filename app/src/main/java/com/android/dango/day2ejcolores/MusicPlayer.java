package com.android.dango.day2ejcolores;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;


public class MusicPlayer extends ActionBarActivity implements View.OnClickListener{

    MediaPlayer nyaPlayer = new MediaPlayer();
    MediaPlayer mediaPlayer = new MediaPlayer();
    File sdCard = Environment.getExternalStorageDirectory();
    File nyaSong = new File(sdCard.getAbsolutePath() + "/Music/nyancat.mp3");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        nyaPlayer = MediaPlayer.create(MusicPlayer.this, R.raw.nyancat);
//        try {
//            nyaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_player, menu);
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
            case R.id.action_settings:
                return true;
            case R.id.action_nyaa:
                if(!nyaPlayer.isPlaying()) {
                    /*if (!started) {
                        try {
                            //nyaPlayer.setDataSource(@res/nyancat);//nyaSong.getAbsolutePath());
                            nyaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    nyaPlayer.start();
                }else{
                    nyaPlayer.pause();
                }
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        /*switch(v.getId()){
            case R.id.b_nyaa:
                if(!playing){
                    if(!started){
                        try{
                            mediaPlayer.setDataSource(nyaSong.getAbsolutePath());
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        playing = true;
                        started = true;
                    }else{
                        mediaPlayer.start();
                        playing = true;
                    }
                }else{
                    mediaPlayer.pause();
                    playing = false;
                }
                break;
            default:
                break;
        }*/
    }

    @Override
    protected void onStop() {
        nyaPlayer.stop();
        nyaPlayer.release();
        super.onStop();
    }
}

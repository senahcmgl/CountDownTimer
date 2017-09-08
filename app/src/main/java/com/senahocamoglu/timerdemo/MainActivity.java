package com.senahocamoglu.timerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView timerText;
    SeekBar timeSeekBar;
    Integer time;

    CountDownTimer countDownTimer;
    Boolean start=true;

    public void startTimer(View view){

        if(start) {
            countDownTimer=new CountDownTimer(timeSeekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTextview((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.tone);
                    ring.start();
                    timeSeekBar.setEnabled(true);
                    timeSeekBar.setProgress(30);
                    start=true;
                }
            }.start();
            start=false;
            timeSeekBar.setEnabled(false);
        }
        else
        {
            countDownTimer.cancel();
            timeSeekBar.setEnabled(true);
            start=true;
        }



    }
    public void updateTextview(int time){
        int minute=(int)time/60;
        int second=time-(minute*60);
        timerText.setText(String.valueOf(minute)+":"+String.valueOf(second));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText=(TextView)findViewById(R.id.timeText);
        timeSeekBar=(SeekBar) findViewById(R.id.timeSeekBar);
        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);



        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<1)
                {
                    timeSeekBar.setProgress(1);
                }
                if(i>0)
                {
                    time=i;
                }
                updateTextview(time);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}

package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer(int secondLeft){
        int minute = (int)secondLeft/60;
        int second = secondLeft - minute*60;

        String secondString = Integer.toString(second);
        if (secondString.matches("0")) {
            secondString = "00";
        }else if(second <= 9){
            secondString = "0" + secondString;
        }
        timerTextView.setText(Integer.toString(minute)+":"+secondString);

    }

    public void controlTimer(View view){
        new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                timerTextView.setText("0:00");
                MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.buzzer);
                mPlayer.start();
;             }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekBar = findViewById(R.id.timerSeekBar);
         timerTextView = findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
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

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Notificationn extends AppCompatActivity {
    TextView timer;
    TextView tv3;
    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds , minutes;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        timer = findViewById(R.id.textView4);
        tv3 = findViewById(R.id.textView3);


        Intent pi=getIntent();
        long i=pi.getLongExtra("time",00000);
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        Date arrivaltime = new Date(i);
        tv3.setText("Your pizza will arrive on "+ arrivaltime);
        Log.d("./././././././.", "onCreate: "+i);
        new CountDownTimer(i-timeInSecs, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

    }


}



package com.example.threadexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample extends AppCompatActivity implements View.OnClickListener {
    public int i = 0;
    TextView textViewTimer, textViewTimerResult;
    Button buttonTimerStart, buttonTimerStop;
    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_example);

        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        textViewTimerResult = (TextView) findViewById(R.id.textViewTimerResult);
        buttonTimerStart = (Button) findViewById(R.id.buttonTimerStart);
        buttonTimerStop = (Button) findViewById(R.id.buttonTimerStop);
        buttonTimerStart.setOnClickListener(this);
        buttonTimerStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTimerStart: {

                timer = new Timer();
                timerTask = new TimerTask() {
                    public void run() {
                        try {
                            i = i + 5;
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                textViewTimerResult.setText("Seconds =" + i);
                            }
                        });

                    }
                };
                timer.schedule(timerTask, 5000, 5000);
                break;
            }
            case R.id.buttonTimerStop: {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }


}
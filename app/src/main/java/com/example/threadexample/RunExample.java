package com.example.threadexample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RunExample extends AppCompatActivity implements View.OnClickListener {
    TextView textViewRun, textViewRunResult;
    Button buttonRunStart;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_example);

        textViewRun = (TextView) findViewById(R.id.textViewRun);
        textViewRunResult = (TextView) findViewById(R.id.textViewRunResult);
        buttonRunStart = (Button) findViewById(R.id.buttonRunStart);
        buttonRunStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RunTask();
    }

    private void RunTask() {
        progressDialog2 = new ProgressDialog(com.example.threadexample.RunExample.this);
        progressDialog2.setMax(10);
        progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog2.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i <= 10) {
                    int Progress = i;
                    try {
                        Thread.sleep(1500);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog2.setProgress(Progress);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewRunResult.setText("Download Complete");
                        progressDialog2.hide();
                    }
                });
            }
        }).start();
    }
}
package com.example.threadexample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerExample extends AppCompatActivity implements View.OnClickListener {
    TextView textViewHandler,textViewHandlerResult;
    Button buttonHandlerStart;
    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_example);

        textViewHandler=(TextView)findViewById(R.id.textViewHandler);
        textViewHandlerResult=(TextView)findViewById(R.id.textViewHandlerResult);
        buttonHandlerStart=(Button)findViewById(R.id.buttonHandlerStart);
        buttonHandlerStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        HandlerTask();
    }
    private void HandlerTask() {
        handler=new Handler();
        progressDialog= new ProgressDialog(com.example.threadexample.HandlerExample.this);
        progressDialog.setMax(10);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<=10) {
                    int Progress = i;
                    try {
                        Thread.sleep(1500);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setProgress(Progress);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewHandlerResult.setText("Download Complete");
                        progressDialog.hide();
                    }
                });
            }
        }).start();
    }
}
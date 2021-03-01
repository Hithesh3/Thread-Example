package com.example.threadexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncExample extends AppCompatActivity implements View.OnClickListener {
    TextView textViewAsync,textViewAsyncResult;
    Button buttonAsyncStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);
        textViewAsync = (TextView) findViewById(R.id.textViewAsync);
        textViewAsyncResult = (TextView) findViewById(R.id.textViewAsyncResult);
        buttonAsyncStart = (Button) findViewById(R.id.buttonAsyncStart);
        buttonAsyncStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Async async=new Async(com.example.threadexample.AsyncExample.this,textViewAsyncResult,buttonAsyncStart);
        async.execute();
        buttonAsyncStart.setEnabled(false);
    }
}
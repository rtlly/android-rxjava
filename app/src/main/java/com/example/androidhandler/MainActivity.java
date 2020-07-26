package com.example.androidhandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button handler = findViewById(R.id.handler_activity);
        Button rxJava = findViewById(R.id.rx_java);

        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHandlerActivity();
            }
        });

        rxJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRxActivity();
            }
        });
    }

    private void startHandlerActivity() {
        Intent intent = new Intent(this, HandlerActivity.class);
        startActivity(intent);
    }

    private void startRxActivity() {
        Intent intent = new Intent(this, RxActivity.class);
        startActivity(intent);
    }
}

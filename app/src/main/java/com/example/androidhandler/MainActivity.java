package com.example.androidhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button handler = findViewById(R.id.handler_activity);
        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHandlerActivity();
            }
        });
    }

    private void startHandlerActivity() {
        Intent intent = new Intent(this, HandlerActivity.class);
        startActivity(intent);
    }
}

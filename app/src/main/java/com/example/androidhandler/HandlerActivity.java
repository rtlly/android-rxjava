package com.example.androidhandler;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.androidhandler.MessageHandler.MESSAGE_TYPE_A;
import static com.example.androidhandler.MessageHandler.MESSAGE_TYPE_B;

public class HandlerActivity extends AppCompatActivity {
    private LooperThread looperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Button buttonA = findViewById(R.id.button_A);
        Button buttonB = findViewById(R.id.button_B);
        looperThread = new LooperThread(getApplicationContext());
        looperThread.start();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(MESSAGE_TYPE_A);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(MESSAGE_TYPE_B);
            }
        });
    }


    private void sendMessage(int messageType) {
        Message message = Message.obtain();
        message.what = messageType;
        looperThread.getHandler().sendMessage(message);
    }
}

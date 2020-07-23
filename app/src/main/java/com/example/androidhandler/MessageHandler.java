package com.example.androidhandler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MessageHandler extends Handler {
    public static final int MESSAGE_TYPE_A = 1;
    public static final int MESSAGE_TYPE_B = 2;
    private Context context;

    public MessageHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message message) {
        Toast toast;
        switch (message.what) {
            case MESSAGE_TYPE_A:
                toast = Toast.makeText(context,"button a pressed", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case MESSAGE_TYPE_B:
                toast = Toast.makeText(context,"button b pressed", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}

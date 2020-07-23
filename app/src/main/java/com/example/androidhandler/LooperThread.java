package com.example.androidhandler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class LooperThread extends Thread {
    private Looper looper;
    private Handler handler;
    private Context context;

    public LooperThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new MessageHandler(context);

        Looper.loop();
    }

    public Looper getLooper() {
        return looper;
    }

    public Handler getHandler() {
        return handler;
    }
}

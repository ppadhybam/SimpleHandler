package com.priyabrat.simplehandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Thread thread;
    private Handler handler;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
            }
        };
    }

    public void startThread(View view) {
        thread = new Thread(new MyThread());
        thread.start();
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i=0;i<100;i++) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.example.administrator.asynctaskdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                //Thread
                thread();
                break;
            case R.id.btn2:
                //Runnable
                runnable();
                break;
            case R.id.btn3:
                //AsyncTask
                asyncTask();
                break;
            case R.id.btn4:
                //Handler
                handler();
                break;
        }
    }

    private void asyncTask() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(((int)objects[0]));
                    toMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(2000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            toMain();
        }
    };
    private void handler() {
        handler.sendEmptyMessageDelayed(0,2000);
    }

    private void runnable() {
        MyRunnable runnable = new MyRunnable();
        runnable.run();
        //new Thread(runnable).start();
    }
     class  MyRunnable implements Runnable{
         @Override
         public void run() {
             try {
                 Thread.sleep(2000);
                 toMain();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }

    private void thread() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    toMain();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void toMain() {
        startActivity(new Intent(this,NewMainActivity.class));
    }
}

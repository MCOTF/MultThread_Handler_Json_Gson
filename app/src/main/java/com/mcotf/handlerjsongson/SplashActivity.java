package com.mcotf.handlerjsongson;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //启动页
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        Thread mthread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//开启异步线程，做启动页的等待效果
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //catch不会写XD，不过原则上不会报错，所以没细看这一块的内容，大不了alt+enter补充
                }
            }
        };
        mthread.start();
    }
}

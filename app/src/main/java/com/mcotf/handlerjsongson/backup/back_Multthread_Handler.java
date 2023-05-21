package com.mcotf.handlerjsongson.backup;

public class back_Multthread_Handler {
    //此处仅弄完成多线程与handler通信部分，方便复习查看用
    /*
package com.mcotf.handlerjsongson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String appid;// "57912913"
    private String appsecret;// "9du91Hkb"

    private Handler handler = new Handler(Looper.myLooper()){//Handler来源android.os.Handler;，looper必须是主线程自己的looper

        @Override
        public void handleMessage(@NonNull Message msg) {//生成handlemessage，在主线程处理消息
            super.handleMessage(msg);

            if(msg.what == 0){//what校验
                String strData = (String) msg.obj;//取出数据,并强制转换
                //此上消息已经流转到了主线程中，之后就可以在主线程中显示了（strData）
                temperature.setText(strData);
            }
        }
    };

    private TextView temperature;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("天气页");

        Button button = this.findViewById(R.id.button);
        temperature = this.findViewById(R.id.temperature);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);//故意卡5秒，方便看主线程和子线程运行速度
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        String stringFromNet = getStringFromNet();
                        //把子线程的字符串传给主线程
                        Message message = new Message();//声明message对象
                        message.what = 0;//可理解为校验码
                        message.obj = stringFromNet;//也可以用.obtain，这里当了懒狗直接输出1

                        handler.sendMessage(message);//在子线程用handler给主线程发消息，经过队列处理后会调用主线程的handleMessage进行处理
                    }
                }).start();

                Toast.makeText(MainActivity.this, "弹出", Toast.LENGTH_SHORT).show();
                Log.d("tag","弹出消息");
            }
        });
    }
    private String getStringFromNet(){
        //从网络获取字符串,但有懒狗啥都没设置
        return "1";
    }
}*/
}

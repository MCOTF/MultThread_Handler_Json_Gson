package com.mcotf.handlerjsongson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mcotf.handlerjsongson.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String appid;// "57912913"
    private String appsecret;// "9du91Hkb"


    private Handler handler = new Handler(Looper.myLooper()){//Handler来源android.os.Handler;，looper必须是主线程自己的looper，（此处不知道原理）

        @Override
        public void handleMessage(@NonNull Message msg) {//生成handlemessage，在主线程处理消息
            super.handleMessage(msg);

            if(msg.what == 0){//what校验
                String strData = (String) msg.obj;//取出数据,并强制转换
                //此上消息已经流转到了主线程中，之后就可以在主线程中显示了（strData）
//                temperature.setText(strData);
                time.setText(strData);
                parseJsonDataAndShow(strData);

                button.setText("查询完成");
                Log.d("tag","查询完成");
                getSupportActionBar().setTitle("查询时间："+simpleDateFormat.format(new Date()));

            }
        }
    };

    //变量集
    private TextView temperature;
    private TextView time;
    private TextView dayNightTemperature;
    private TextView weather;
    private Button button;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(simpleDateFormat.format(new Date()));

        //控件初始化
        button = this.findViewById(R.id.button);
        temperature = this.findViewById(R.id.temperature);
        dayNightTemperature = findViewById(R.id.day_night_temperature);
        weather = findViewById(R.id.weather);
        time = this.findViewById(R.id.time);

        //按钮底下的时间
        time.setText(simpleDateFormat.format(new Date()));

        //查询天气的按钮机制
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);//故意卡2秒，方便看主线程和子线程运行速度
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
                button.setText("查询中");
                Toast.makeText(MainActivity.this, "开始查询，看到此信息说明主线程正常响应了", Toast.LENGTH_SHORT).show();
                Log.d("tag","开始查询，看到此信息说明主线程正常响应了");
            }
        });
    }
    private String getStringFromNet(){
        //从网络获取字符串,但有懒狗啥都没设置
        Log.d("tag","已获取字符串");
        return NetUtil.doGet();
//        return "1";//备用
    }

    public void parseJsonDataAndShow(String jsonStr){
        //Json数据解析
        /*{"nums":0,"cityid":"101121001","city":"菏泽","date":"2023-05-21","week":"星期日","update_time":"23:42","wea":"多云","wea_img":"yun","tem":"14","tem_day":"17","tem_night":"11","win":"西北风","win_speed":"3级","win_meter":"13km\/h","air":"73","pressure":"1011","humidity":"89%"}*/

        try {//有可能传的json字符串不符合要求，比如少了逗号或者多了逗号
            JSONObject jsonObject = new JSONObject(jsonStr);
//            jsonObject.getString("cityid","0");//不推荐，玩错了容易崩
            jsonObject.optString("nums");
            jsonObject.optString("cityid","101121001");//fallback：默认值（备选）,就算不传也有默认值，默认值为空
            String wea = jsonObject.optString("wea");
            String tem = jsonObject.optString("tem");
            String dayT = jsonObject.optString("tem_day");
            String nightT = jsonObject.optString("tem_night");

            //显示json数据
            temperature.setText(tem);
            weather.setText(wea);
            dayNightTemperature.setText(String.format("%s/%s", dayT, nightT));

            //更简便的方法：GSON
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(jsonStr, WeatherBean.class);//转换为WeatherBean.class
            //这样能直接给WeatherBean赋值，键与变量名对应，值为属性对应
            /*否则就需要
            WeatherBean weatherBean1 = new WeatherBean();
            weatherBean1.set.................各种set，city，id等等
            * */
            Log.d("tag",weatherBean.toString());//如果有null说明变量名不对应，可用注解@SerializedName
            String jsonWeather = gson.toJson(weatherBean);//重新转回Json
            Log.d("tag", jsonWeather);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
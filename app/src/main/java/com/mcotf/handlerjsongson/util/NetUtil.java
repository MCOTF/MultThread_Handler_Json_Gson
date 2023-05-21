package com.mcotf.handlerjsongson.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {//写这个方便理解okhttp的基础

    public static String doGet(){//是静态方法，方便直接调用

        String result = "";
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        String bookJSONString = null;

        try {
            //1.建立连接
            HttpURLConnection httpUrlConnection = null;
            String url = "https://www.yiketianqi.com/free/day?appid=57912913&appsecret=9du91Hkb&cityid=101121001&&unescape=1";//此处为懒狗行为，结果需要Gson解析
            URL requestUrl = new URL(url);//有可能会有异常
            httpUrlConnection = (HttpURLConnection) requestUrl.openConnection();//也可能会有异常
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setConnectTimeout(500000);//5秒后未响应为超时
            //最后用connect方法打开连接
            httpUrlConnection.connect();


            //2.获取二进制流
            InputStream inputStream = httpUrlConnection.getInputStream();

            //3.将二进制流包装
            reader = new BufferedReader((new InputStreamReader(inputStream)));//可以理解为管道接一个管道

            //4.从BufferedReader中读取string字符串
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");//换行
            }

            if(builder.length() == 0){//如果啥也没读到就返回空
                return null;
            }
            result = builder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return result;//最后返回出去

    }
}

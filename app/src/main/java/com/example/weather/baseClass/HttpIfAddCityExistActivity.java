package com.example.weather.baseClass;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpIfAddCityExistActivity extends AppCompatActivity {
    private final Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what==10){
                String response=(String) msg.obj;
                onSuccess(response);

            }
            onError();
            return false;
        }
    });
    StringBuilder response;
    public void loadHttpData(String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                InputStream is=null;
                try {
                    URL url1=new URL(url);
                    connection=(HttpURLConnection)url1.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    is= connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(is));
                    response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message=new Message();
                    message.what=10;
                    message.obj=response.toString();
                    handler.sendMessage(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(is!=null){
                        try {
                            is.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        });
    }
    public void onSuccess(final String response){}
    public void onError(){}
    public void onCancelled(){}
    public void onFinished(){}
}


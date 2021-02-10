package com.example.weather;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//网络请求获取数据
public class LoadDataFragment extends Fragment {
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

        onSuccess(response.toString());

    }
    public void onSuccess(final String response){}
    public void onError(){}
    public void onCancelled(){}
    public void onFinished(){}
}


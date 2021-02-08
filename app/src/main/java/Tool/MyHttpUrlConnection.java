package Tool;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpUrlConnection {

    private static InputStream inputStream;
    private static BufferedReader reader;
    private static StringBuilder response;
    private static HttpURLConnection connection;

    public static void getDataFromServer(URL url, Handler handler){
        try {
            connection = (HttpURLConnection)url.openConnection();
            if(connection.getResponseCode()==200){
                inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                response = new StringBuilder();
                for(String line;(line= reader.readLine())!=null;){
                    response.append(line);
                }
                String[] output=GetJSONData.parseWithJSON(response.toString());
                Message message=new Message();
                message.what=10;
                message.obj=output;
                handler.sendMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
            if(null!=inputStream)inputStream.close();
            if(null!=reader)reader.close();
            if(null!=connection)connection.disconnect();
        }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}

package bwie.com.day2_test.http;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection  {
    public static String getHttp(Context context ,String url,String fangfa)
    {
        String message="";
        try {
            URL uri= new URL(url);
           HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
           connection.setRequestMethod(fangfa);
           connection.setConnectTimeout(5000);
           connection.setReadTimeout(4000);
           connection.connect();
            InputStream inputStream = connection.getInputStream();
            byte[] b = new byte[512 * 1024];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len =0;
            while ((len = inputStream.read(b))!=-1)
            {
                outputStream.write(b,0,len);
            }
            message =outputStream.toString();
            inputStream.close();
            connection.disconnect();

        }catch (Exception e){

        }
        return message;
    }
}

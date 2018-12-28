package bwie.com.app1.network;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpUrlUtils {
    //请求获取网络请求
      public  static   String  getHttpCon(String mPath,String mRam){
          String messages = "";
          try {
                  URL murl = new URL(mPath);
                  Log.i("shop", "doInBackground: " + mPath);
                  HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
                  //设置请求发送
                  connection.setRequestMethod(mRam);
                  //请求超时
                  connection.setConnectTimeout(5 * 1000);
                  connection.setReadTimeout(5 * 1000);
                  //数据流处理
                  if (connection.getResponseCode() == 200) {
                      //获取输入流
                      InputStream inputStream = connection.getInputStream();
                      //读取输入流
                      byte[] b = new byte[1024 * 1024]; //定义一个byte数组读取输入流
                      ByteArrayOutputStream baos = new ByteArrayOutputStream(); //定义缓存流来保存输入流的数据
                      int len = 0;
                      while ((len = inputStream.read(b)) > -1) {  //每次读的len>-1 说明是是有数据的
                          baos.write(b, 0, len);  //三个参数  输入流byte数组   读取起始位置  读取终止位置
                      }
                      messages = baos.toString();
                      inputStream.close();
                      connection.disconnect();
                  }
              } catch(Exception e){
                  e.printStackTrace();
              }

          return messages;
       }
    /**
     * post    请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String post(String url,Map<String, Object> param) {
        StringBuilder builder=new StringBuilder();
        try {
            StringBuilder params=new StringBuilder();
            for(Map.Entry<String, Object> entry:param.entrySet()){
                params.append(entry.getKey());
                params.append("=");
                params.append(entry.getValue().toString());
                params.append("&");
            }
            if(params.length()>0){
                params.deleteCharAt(params.lastIndexOf("&"));
            }
            URL restServiceURL = new URL(url+(params.length()>0 ? "?"+params.toString() : ""));
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Accept", "application/json");
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpConnection.setDoInput(true);
            // Post 请求不能使用缓存
            httpConnection.setUseCaches(false);
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            InputStream inStrm = httpConnection.getInputStream();
            byte []b=new byte[1024*512];
            int length=-1;
            while((length=inStrm.read(b))!=-1){
                builder.append(new String(b,0,length));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }







}

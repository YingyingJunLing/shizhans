package bwie.com.zhaoyingdi190102.myapp;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        //新建一个img对象
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).build();
        //全局初始化imageloader对象
        ImageLoader.getInstance().init(build);

    }
}

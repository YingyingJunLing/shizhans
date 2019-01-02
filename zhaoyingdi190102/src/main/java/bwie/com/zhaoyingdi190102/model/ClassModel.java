package bwie.com.zhaoyingdi190102.model;

import android.util.Log;

import java.io.IOException;

import bwie.com.zhaoyingdi190102.http.okHttp3;
import bwie.com.zhaoyingdi190102.presenter.ClassPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClassModel implements InterModel
{

    ModelCallBack modelCallBack;
    public ClassModel(ModelCallBack modelCallBack)
    {
        this.modelCallBack =modelCallBack;
    }

    @Override
    public void getModelData(String url) {
       okHttp3.okHttpGet(url, new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               modelCallBack.loadSuccess(response.body().string());
             //  Log.i("onResponse",""+response.body().string());

           }
       });

    }
    //定义一个成功失败回调的接口

    public interface  ModelCallBack
    {
        void loadSuccess(String data);
        void loadFail();
    }


}

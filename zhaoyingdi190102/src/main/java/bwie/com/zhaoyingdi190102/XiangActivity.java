package bwie.com.zhaoyingdi190102;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import bwie.com.zhaoyingdi190102.adapter.Adapter;
import bwie.com.zhaoyingdi190102.adapter.MyAdapter;
import bwie.com.zhaoyingdi190102.bean.Bean;
import bwie.com.zhaoyingdi190102.bean.JsonBean;
import bwie.com.zhaoyingdi190102.model.ClassXiangModel;
import bwie.com.zhaoyingdi190102.presenter.ClassXiangPresenter;
import bwie.com.zhaoyingdi190102.view.InterView;

public class XiangActivity  extends AppCompatActivity implements InterView
{
    private JsonBean bean;
    private JsonBean.DataBean data1;
    Adapter mAdapter;
    private ListView grid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        grid = findViewById(R.id.list);
        ClassXiangPresenter classPresenter = new ClassXiangPresenter(this);
        classPresenter.getPresenterData();
    }

    @Override
    public void getViewData(final String data) {
        //开始解析数据
        new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                bean = gson.fromJson(data, JsonBean.class);
                data1 = bean.getData();
                Message message = new Message();
                message.obj= bean;
                message.what=0;
                handler.sendMessage(message);
            }
        }.run();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mAdapter = new Adapter(XiangActivity.this, data1);
                    grid.setAdapter(mAdapter);
                    break;
            }
        }
    };
}

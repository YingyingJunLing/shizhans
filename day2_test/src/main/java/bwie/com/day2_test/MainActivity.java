package bwie.com.day2_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bwie.com.day2_test.base.BaseActivity;
import bwie.com.day2_test.bean.Bean;
import bwie.com.day2_test.http.HttpConnection;
import me.maxwin.view.XListView;

public class MainActivity extends BaseActivity {

    private XListView xlv;
    private int type=1;
    private int page=1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                type=1;
                page=1;
                new MyAsy().execute();
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                type=2;
                page++;
                new MyAsy().execute("http://120.27.23.105/product/getProducts?pscid=39&page="+page);
                myAdapter.notifyDataSetChanged();
            }
        });
        new MyAsy().execute();
    }

    @Override
    protected void initView() {
        xlv = findViewById(R.id.xlv);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
    class MyAsy extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String url="http://120.27.23.105/product/getProducts?pscid=39&page=1";
            return HttpConnection.getHttp(MainActivity.this,url,"GET");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("onPostExecute","这是我解析的"+s);
            if(s!=null)
            {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                if(data!=null)
                {
                    if(type==1)
                    {
                        myAdapter = new MyAdapter(MainActivity.this,data);
                        xlv.setAdapter(myAdapter);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Date date = new Date(System.currentTimeMillis());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                                String ss = format.format(date);
                                xlv.setRefreshTime(ss);
                                xlv.stopRefresh();
                            }
                        },1000);
                    }
                    if(type ==2)
                    {
                        myAdapter = new MyAdapter(MainActivity.this,data);
                        xlv.setAdapter(myAdapter);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               xlv.stopLoadMore();
                            }
                        },1000);
                    }
                }

            }
        }
    }
}

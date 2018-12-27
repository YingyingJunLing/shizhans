package bwie.com.day2_test;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bwie.com.day2_test.bean.Bean;

public class MyAdapter extends BaseAdapter
{
    Context context;
    List<Bean.DataBean> data;
    public MyAdapter(Context context, List<Bean.DataBean> data) {
        this.context =context;
        this.data=data;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type)
        {
            case 0:
                convertView =  View.inflate(context,R.layout.list1,null);
                ImageView img = convertView.findViewById(R.id.img);
                TextView name = convertView.findViewById(R.id.name);
                ImageLoader.getInstance().displayImage(data.get(position).getImages(),img);
                name.setText(data.get(position).getTitle());

                break;
            case 1:
                convertView = View.inflate(context, R.layout.list2, null);
               TextView title =  convertView.findViewById(R.id.name);
                title.setText(data.get(position).getTitle());


                break;
        }
        return convertView;
    }
}

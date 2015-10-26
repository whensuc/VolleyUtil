package com.dyg.health.work.volleytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.dyg.health.work.volleytest.R;
import com.dyg.health.work.volleytest.modle.Info;
import com.dyg.health.work.volleytest.utils.VolleyUtil;

import java.util.List;

/**
 * Created by wzy on 2015-10-10.
 */
public class InfoAdapter extends ArrayAdapter<Info>  {
    private int resourceId;
    private VolleyUtil volleyUtil;
    private List<Info> listdata;


    public InfoAdapter(Context context,int resourceId,List<Info> object){
        super(context,resourceId,object);
        this.resourceId = resourceId;
        volleyUtil = VolleyUtil.getInstance(context);
        listdata = object;

    }

    public void refresh(List<Info> object){
        listdata = (object);
        notifyDataSetChanged();
    }

    public void loadmore(List<Info> object){
        listdata.addAll(object);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Info getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info = getItem(position);
        View view;
        ViewHolder viewHolder;
       if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
           viewHolder = new ViewHolder();
           viewHolder.networkImageView = (NetworkImageView)view.findViewById(R.id.iv_image);
           viewHolder.networkImageView.setDefaultImageResId(R.drawable.loading);
           viewHolder.networkImageView.setErrorImageResId(R.drawable.errorimg);
           viewHolder.textView = (TextView)view.findViewById(R.id.tv_text);
           view.setTag(viewHolder);
        }else{
            view = convertView;
           viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(info.getText());
        volleyUtil.loadImage(info.getImgurl(),viewHolder.networkImageView);
        return view;
    }

    private class ViewHolder{
        NetworkImageView networkImageView;
        TextView textView;
    }

}

package com.bkrc.listview.video;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bkrc.listview.R;

import java.util.ArrayList;

/**
 * 作者： 小白攻城狮 on 2017/8/15.
 */

public class VideoAdapter extends BaseAdapter {

    private final boolean isVideo;
    private final Context context;
    private final ArrayList<MediaItem> mediaItems;
    private Utils utils;

    public VideoAdapter (Context context, ArrayList<MediaItem> mediaItems, boolean isVideo){
        this.context = context;
        this.mediaItems = mediaItems;
        this.isVideo = isVideo;
        utils = new Utils();

    }

    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler viewHodler;
        if(view == null){
            view = View.inflate(context, R.layout.item_lv_customer,null);
            viewHodler = new ViewHodler();
            viewHodler.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            viewHodler.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewHodler.tv_time = (TextView) view.findViewById(R.id.tv_time);
            viewHodler.tv_size = (TextView) view.findViewById(R.id.tv_size);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }

        // 根据 position 得到列表中对应的数据
        MediaItem item = mediaItems.get(i);
        viewHodler.tv_name.setText(item.getName());
        viewHodler.tv_time.setText(new Utils().stringForTime((int) item.getDuration()));
        viewHodler.tv_size.setText(Formatter.formatFileSize(context, item.getSize()));


        return view;
    }

    static class ViewHodler{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_time;
        TextView tv_size;

    }
}

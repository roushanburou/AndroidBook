package com.bkrc.listview.video;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bkrc.listview.R;

import java.util.ArrayList;

public class VideoLVActivity extends AppCompatActivity {

    private ListView video_lv;
    private TextView video_tv_no;
    private ProgressBar video_pb;
    private ArrayList<MediaItem> mediaItems;// 装数据的集合

    // 显示视频数据
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mediaItems != null && mediaItems.size()>0){
                // 有数据
                // 设置适配器
                video_lv.setAdapter(new VideoAdapter(VideoLVActivity.this, mediaItems,true));
                // 把文本隐藏
                video_tv_no.setVisibility(View.GONE);
            } else {
                // 没有数据
                // 显示文本
                video_tv_no.setVisibility(View.VISIBLE);
            }
            //  隐藏进度条
            video_pb.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader_lv);
        video_lv = (ListView) findViewById(R.id.video_lv);
        video_tv_no = (TextView) findViewById(R.id.video_tv_no);
        video_pb = (ProgressBar) findViewById(R.id.video_pb);
        // 设置ListView点击事件
        video_lv.setOnItemClickListener(new mOnClickListener());
        getDataFromLocal();
    }

    /**
     * 获取视频数据
     */
    private void getDataFromLocal() {
        mediaItems = new ArrayList<>();
        // 解决权限问题
        isGrantExternalRW(this);

        // 1.遍历sdcard，后缀名
        // 2.从内容提供者面获取视频
        new Thread(){
            @Override
            public void run() {
                super.run();
                ContentResolver resolver = VideoLVActivity.this.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME, // 获取视频文件名称
                        MediaStore.Video.Media.DURATION, // 获取视频时长
                        MediaStore.Video.Media.SIZE, // 获取文件大小
                        MediaStore.Video.Media.DATA, // 获取视频的绝对地址
                        MediaStore.Video.Media.ARTIST // 获取演唱者
                };
                Cursor cursor = resolver.query(uri,objs,null,null,null);
                if(cursor != null){
                    while(cursor.moveToNext()){

                        MediaItem item = new MediaItem();

                        mediaItems.add(item);

                        String name = cursor.getString(0);// 获取视频文件名称
                        item.setName(name);

                        long duration = cursor.getLong(1);// 获取视频时长
                        item.setDuration(duration);

                        long size = cursor.getLong(2);// 获取文件大小
                        item.setSize(size);

                        String data = cursor.getString(3);// 获取视频的绝对地址
                        item.setData(data);

                        String artist = cursor.getString(4);// 获取演唱者
                        item.setArtist(artist);

                    }
                    cursor.close();
                }
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }.start();
    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
    }

    private class mOnClickListener implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MediaItem mediaItem = mediaItems.get(i);
            Toast.makeText(VideoLVActivity.this, "当前选中的视频："+mediaItem.getName()
                    , Toast.LENGTH_SHORT).show();
        }
    }
}

package com.brkc.mediacontroller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private VideoView videoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},6);
        }
        else{
            initVideoView();
        }
    }

    private void initVideoView(){
        //本地的视频
        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/u-boot.mp4" ;
        //网络视频
//           String videoUrl2 = Utils.videoUrl ;

        Uri uri = Uri.parse( videoUrl1 );

        videoView = (VideoView)this.findViewById(R.id.videoView );
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        //设置视频路径
        videoView.setVideoURI(uri);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //启动视频播放
        videoView.start();
        //设置获取焦点
        videoView.setFocusable(true);
    }


    private  int intPositionWhenPause =0;
    /* 页面暂停效果处理*/
    @Override
    protected  void onPause() {
        super.onPause();
        //如果当前页面暂停则保存当前播放位置，全局变量保存
        intPositionWhenPause=videoView.getCurrentPosition();
        //停止回放视频文件
        videoView.stopPlayback();
    }

    /**
     * 页面从暂停中恢复
     */
    @Override
    protected void onResume() {
        super.onResume();
        //跳转到暂停时保存的位置
        if(intPositionWhenPause>=0){
            videoView.seekTo(intPositionWhenPause);
            //初始播放位置
            intPositionWhenPause=-1;
        }
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( MainActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 6:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoView();
                }else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

package com.brkc.video;

import java.io.File;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
    private Button btn_play, btn_pause, btn_replay, btn_stop;
    private VideoView vv_video;
    private boolean isPlaying;
    private String videoUrl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = (Button) findViewById(R.id.bt_play);
        btn_pause = (Button) findViewById(R.id.bt_pause);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        btn_stop = (Button) findViewById(R.id.bt_stop);

        btn_play.setOnClickListener(click);
        btn_pause.setOnClickListener(click);
        btn_replay.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

        vv_video = (VideoView) findViewById(R.id.videoView);
        if (Build.VERSION.SDK_INT >= 23 &&ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},6);
        }
        else {
            load_data();
        }
    }

    void load_data()
    {
        //本地的视频
         videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/u-boot.mp4" ;
        File file = new File(videoUrl1);
        if (!file.exists()) {
            Toast.makeText(this, "视频文件路径错误", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.bt_play:
                    play(0);
                    break;
                case R.id.bt_pause:
                    pause();
                    break;
                case R.id.btn_replay:
                    replay();
                    break;
                case R.id.bt_stop:
                    stop();
                    break;
                default:
                    break;
            }
        }
    };

    protected void play(int msec) {

        Log.i(TAG, "指定视频源路径");
        vv_video.setVideoPath(videoUrl1);

        Log.i(TAG, "开始播放");
        vv_video.start();
        // 按照初始位置播放
        vv_video.seekTo(msec);
        // 播放之后设置播放按钮不可用
        btn_play.setEnabled(false);

        vv_video.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 在播放完毕被回调
                btn_play.setEnabled(true);
            }
        });

        vv_video.setOnErrorListener(new OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // 发生错误重新播放
                play(0);
                isPlaying = false;
                return false;
            }
        });

    }

    /**
     * 重新开始播放
     */
    protected void replay() {
        if (vv_video != null && vv_video.isPlaying()) {
            vv_video.seekTo(0);
            Toast.makeText(this, "重新播放", Toast.LENGTH_SHORT).show();
            btn_pause.setText("暂停");
            return;
        }
        isPlaying = false;
        play(0);
    }
    /**
     * 暂停或继续
     */
    protected void pause() {
        if (btn_pause.getText().toString().trim().equals("继续")) {
            btn_pause.setText("暂停");
            vv_video.start();
            Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
            return;
        }
        if (vv_video != null && vv_video.isPlaying()) {
            vv_video.pause();
            btn_pause.setText("继续");
            Toast.makeText(this, "暂停播放", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * 停止播放
     */
    protected void stop() {
        if (vv_video != null && vv_video.isPlaying()) {
            vv_video.stopPlayback();
            btn_play.setEnabled(true);
            isPlaying = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 6:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    load_data();
                }else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

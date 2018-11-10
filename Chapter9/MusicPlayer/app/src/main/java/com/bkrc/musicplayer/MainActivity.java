package com.bkrc.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {
	
	private Button play,pause,stop;
	private TextView song;
	private MediaPlayer mPlay;
	private Boolean stopflag = false;  //是否停止标志位
    private  int duration;  //音频的总长度
    private SeekBar seekbar;
    private Switch loopswitch = null;
    private boolean islooping;  //是否循环播放的标志位
    private boolean isstop_seekbar =true;  //seekbar控件是否更新标志位

    //通过 Handler 实现 seekbar控件的实时更新，1500毫秒更新一次
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what ==22 ) {

                    seekbar.setProgress(mPlay.getCurrentPosition());  //更新进度条
                    handler.sendEmptyMessageDelayed(22, 1500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
    	play=(Button) findViewById(R.id.bt_play);
    	pause=(Button) findViewById(R.id.bt_pause);
    	stop=(Button) findViewById(R.id.bt_stop);
    	song= (TextView) findViewById(R.id.tv_song);
        seekbar = findViewById(R.id.sb1);
        loopswitch =findViewById(R.id.looping);

    	play.setOnClickListener(new MyOnClickListener());
    	pause.setOnClickListener(new MyOnClickListener());
    	stop.setOnClickListener(new MyOnClickListener());

        loopswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    islooping =true;   //是否循环播放的标志位
                else
                    islooping =false;

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               if(!stopflag && fromUser) {
                   mPlay.seekTo(progress);  //更改播放进度
               }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        init_media();
    }

    void init_media()  //加载音频数据，
    {
        mPlay = MediaPlayer.create(MainActivity.this, R.raw.guanghuisuiyue);
        duration = mPlay.getDuration();  //获取音频总长度
        seekbar.setMax(duration);    //设置音频总长度
        mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(islooping) {
                    mp.start();
                }
            }

        });
        mPlay.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                init_media();
                return false;
            }
        });
    }

    public class MyOnClickListener implements OnClickListener{
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.bt_play:   //开始播放
			try {
			    if(stopflag) {
                    mPlay.prepare();
                    stopflag =false;
                }
				mPlay.start();
				song.setText("正在播放");
                isstop_seekbar = false;
                handler.sendEmptyMessageDelayed(22,1000); //实现 seekbar 的实时更新
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.bt_pause:   //暂停播放
		    isstop_seekbar = true;
		    if(mPlay.isPlaying()) {
                mPlay.pause();
            }
				song.setText("暂停播放");
			break;
		case R.id.bt_stop:   //停止播放
		    isstop_seekbar = true;
			mPlay.stop();
			song.setText("停止播放");
			stopflag =true;
			break;
		default:
			break;
		}
	}
}
    @Override
    protected void onRestart() {   //退出界面后，重新回到界面，重新播放
        super.onRestart();
        if(stopflag) {
            try {
                mPlay.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopflag =false;
        }
        mPlay.start();
        song.setText("正在播放");
        isstop_seekbar = false;
        handler.sendEmptyMessageDelayed(22,1000);

    }

    @Override
    protected void onStop() {   ////退出界面，暂停播放
        super.onStop();
        isstop_seekbar = true;
        mPlay.pause();
        song.setText("暂停播放");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlay.stop();
    }
}

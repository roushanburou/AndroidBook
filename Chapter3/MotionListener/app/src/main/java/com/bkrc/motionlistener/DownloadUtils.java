package com.bkrc.motionlistener;

/**
 * 作者： 小白攻城狮 on 2018/9/21.
 * 作用：
 * 来源：
 */

public class DownloadUtils {
    // 单例模式
    private static DownloadUtils instance = null;
    private boolean isDownloading = true;
    private int progress = 0;

    private DownloadUtils(){
    }
    public static synchronized DownloadUtils getInstance(){
        if (instance == null)
            instance = new DownloadUtils();
        return instance;
    }

    // 实际开发中如果要实现“下载”功能，需要传入 url 参数。
    public void download(DownloadListener listener) throws InterruptedException {
        // 模拟“下载”功能
        while ( isDownloading ){
            // 下载中
            listener.onDownloading(progress);
            Thread.sleep(1000);
            progress += 10;
            if (progress >= 100){
                isDownloading = false;
            }
        }
        // 下载完成
        listener.onDownloaded();
    }
}

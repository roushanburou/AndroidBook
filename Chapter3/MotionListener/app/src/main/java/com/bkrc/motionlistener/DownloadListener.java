package com.bkrc.motionlistener;

/**
 * 作者： 小白攻城狮 on 2018/9/21.
 * 作用：
 * 来源：
 */

public interface DownloadListener {

    public void onDownloading(int progress); // 下载进度
    public void onDownloaded();              // 下载完成
}



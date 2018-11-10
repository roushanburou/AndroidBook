package com.bkrc.bdlbs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.bkrc.bdlbs.service.Utils;

import java.util.LinkedList;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/2
 * @update 添加更新的内容
 */
public class BdMapMainActivity extends MainActivity{

    private BaiduMap baiduMap;
    private Overlay overlay;// 当前仅当只有一个覆盖物
    private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>(); // 存放历史定位结果的链表，最大存放当前结果的前5次定位结果

    @Override
    protected void onStart() {
        super.onStart();
        initMap();
        addMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMap.getText().toString().equals("地图定位")) {
                    locationService.start();// 定位SDK
                    // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                    addMap.setText("停止定位");
                    isMapUpdate = true;
                } else {
                    locationService.stop();
                    addMap.setText("地图定位");
                    isMapUpdate = false;
                }
            }
        });
    }

    @Override
    boolean isMapUpdate(BDLocation location) {
        if (!isMapUpdate) return false;
        if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {
            Message locMsg = locHandler.obtainMessage();
            Bundle locData;
            locData = Algorithm(location);
            if (locData != null) {
                locData.putParcelable("loc", location);
                locMsg.setData(locData);
                locHandler.sendMessage(locMsg);
            }
        }
        return true;
    }

    protected void initMap() {
        // 普通地图 ,mBaiduMap是地图控制器对象
        baiduMap = bdMap.getMap();
        // 当不需要定位图层时关闭定位图层
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 改变地图状态
        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        baiduMap.setMyLocationEnabled(true);
    }

    @SuppressLint("HandlerLeak")
    private Handler locHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            BDLocation location = msg.getData().getParcelable("loc");
            if (location != null) {
                Log.e("TAG", " 精度: " + location.getRadius()
                        + " 方向: " + location.getDirection()
                        + " 维度: " + location.getLatitude()
                        + " 经度: " + location.getLongitude());
                drawMarker(location.getLatitude(), location.getLongitude(), R.drawable.icon_openmap_mark);
            }
        }
    };

    /***
     * 平滑策略代码实现方法，主要通过对新定位和历史定位结果进行速度评分，
     * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处理,若速度过快，
     * 则推测有可能是由于运动速度本身造成的，则不进行低速平滑处理 ╭(●｀∀´●)╯ (＾－＾)V
     * @param location
     * @return Bundle
     */
    private Bundle Algorithm(BDLocation location) {
        Bundle locData = new Bundle();
        double curSpeed;
        if (locationList.isEmpty() || locationList.size() < 2) {
            LocationEntity temp = new LocationEntity();
            temp.location = location;
            temp.time = System.currentTimeMillis();
            locData.putInt("iscalculate", 0);
            locationList.add(temp);
        } else {
            if (locationList.size() > 5)
                locationList.removeFirst();
            double score = 0;
            for (int i = 0; i < locationList.size(); ++i) {
                LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
                        locationList.get(i).location.getLongitude());
                LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                double distance = getDistance(lastPoint, curPoint);
                curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
                score += curSpeed * Utils.EARTH_WEIGHT[i];
            }
            if (score > 0.00000999 && score < 0.00005) { // 经验值,开发者可根据业务自行调整，也可以不使用这种算法
                location.setLongitude(
                        (locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
                                / 2);
                location.setLatitude(
                        (locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
                                / 2);
                locData.putInt("iscalculate", 1);
            } else {
                locData.putInt("iscalculate", 0);
            }
            LocationEntity newLocation = new LocationEntity();
            newLocation.location = location;
            newLocation.time = System.currentTimeMillis();
            locationList.add(newLocation);

        }
        return locData;
    }

    double getDistance(LatLng var0, LatLng var1) {
        if (var0 != null && var1 != null) {
            Point var2 = CoordUtil.ll2point(var0);
            Point var3 = CoordUtil.ll2point(var1);
            return var2 != null && var3 != null ? CoordUtil.getDistance(var2, var3) : -1.0D;
        } else {
            return -1.0D;
        }
    }

    /**
     * 绘制图标
     * @param latitude 维度
     * @param longitude 经度
     * @param drawable 图标
     */
    private void drawMarker(double latitude, double longitude,int drawable) {
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(drawable);

        LatLng point = new LatLng(latitude, longitude);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        // 连续定位时启用(清除上一帧覆盖物)
        if (overlay != null)
            overlay.remove();
        // 在地图上添加Marker，并显示
        overlay = baiduMap.addOverlay(option);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
    }

    /**
     * 封装定位结果和时间的实体类
     * @author baidu
     */
    class LocationEntity {
        BDLocation location;
        long time;
    }

}

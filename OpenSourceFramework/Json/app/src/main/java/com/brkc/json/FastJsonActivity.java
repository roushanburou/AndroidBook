package com.brkc.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.brkc.json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @作者 小白攻城狮
 *
 * @描述 FastJson解析页面
 * @日期  
 * @更新内容 
 **/

public class FastJsonActivity extends AppCompatActivity implements View.OnClickListener {
    
    private TextView tv_title;
    private Button bt_fj_tojavaobject;
    private Button bt_fj_tojavalist;
    private Button bt_fj_javatojsonobject;
    private Button bt_fj_javatojsonarray;

    private TextView tv_fj_orignal;
    private TextView tv_fj_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fast_json);

        // 初始化view
        initView();

        // 初始化监听
        initListener();
    }

    private void initListener() {
        bt_fj_tojavaobject.setOnClickListener(this);
        bt_fj_tojavalist.setOnClickListener(this);
        bt_fj_javatojsonobject.setOnClickListener(this);
        bt_fj_javatojsonarray.setOnClickListener(this);
    }

    private void initView() {

        // 获取标题对象
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("FastJson解析");

        // 获取4个button对象
        bt_fj_tojavaobject = (Button)findViewById(R.id.bt_fj_tojavaobject);
        bt_fj_tojavalist = (Button)findViewById(R.id.bt_fj_tojavalist);
        bt_fj_javatojsonobject = (Button)findViewById(R.id.bt_fj_javatojsonobject);
        bt_fj_javatojsonarray = (Button)findViewById(R.id.bt_fj_javatojsonarray);

        // 获取显示数据的textView对象
        tv_fj_orignal = (TextView)findViewById(R.id.tv_fj_orignal);
        tv_fj_last = (TextView)findViewById(R.id.tv_fj_last);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // （1）将json格式的字符串{}转换为Java对象
            case R.id.bt_fj_tojavaobject:
                jsonToJavaObjectByFastJson();
                break;

            // （2）将json格式的字符串[]转换为Java对象的List
            case R.id.bt_fj_tojavalist:
                jsonToJavaListByFastJson();
                break;

            // （3）将Java对象转换为json字符串{}
            case R.id.bt_fj_javatojsonobject:
                javaToJsonObjectByFastJson();
                break;

            // （4）将Java对象的List转换为json字符串[]
            case R.id.bt_fj_javatojsonarray:
                javaToJsonArrayByFastJson();
                break;
        }
    }

    // （4）将Java对象的List转换为json字符串[]
    private void javaToJsonArrayByFastJson() {

        // 1 创建一个Java集合
        List<ShopInfo> shops = new ArrayList<>();

        ShopInfo baoyu = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");
        ShopInfo longxia = new ShopInfo(2, "龙虾", 251.0, "longxia");

        shops.add(baoyu);
        shops.add(longxia);

        // 2 生成JSON数据
        String json = JSON.toJSONString(shops);

        // 3 显示JSON数据
        tv_fj_orignal.setText(shops.toString());
        tv_fj_last.setText(json);

    }

    // （3）将Java对象转换为json字符串{}
    private void javaToJsonObjectByFastJson() {

        // 1 创建一个Java对象
        ShopInfo shopInfo = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");

        // 2 生成JSON数据
        String json = JSON.toJSONString(shopInfo);

        // 3 显示数据
        tv_fj_orignal.setText(shopInfo.toString());
        tv_fj_last.setText(json);
    }


    // （2）将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByFastJson() {

        // 1 获取或创建json数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://马赛克/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://马赛克/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";

        // 2 解析JSON数据
        List<ShopInfo> shopInfos = JSON.parseArray(json, ShopInfo.class);

        // 3 显示数据
        tv_fj_orignal.setText(json);
        tv_fj_last.setText(shopInfos.toString());
    }

    // （1）将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByFastJson() {

        // 1 获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://马赛克/images/f1.jpg\"\n" +
                "}\n";

        // 2 解析JSON数据
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);

        // 3 显示数据
        tv_fj_orignal.setText(json);
        tv_fj_last.setText(shopInfo.toString());
    }

}

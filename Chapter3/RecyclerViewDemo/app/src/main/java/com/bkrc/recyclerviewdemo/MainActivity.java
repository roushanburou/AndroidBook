package com.bkrc.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerAdapter rAdapter;

    private ArrayList<FuLian> fuLians;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

//        GridLayoutManager manager = new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false);
//        //自定义item占据的小格大小时需要重写 getSpanSize()，返回值就是占据的小格数量
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
////                以下代码仅为上图示例为写，具体场景中应该根据需求具体编写
//                if (position == 3) {
//                    return 2;
//                }
//                if (position == 7) {
//                    return 3;
//                }
//                return 1;
//            }
//
//            //这个方法也很重要，但我还没搞清楚它的具体效果，从注释上来看，该方法是用于指定 item 在该行或该列上具体哪个位置，比如将GridLayoutManager设置为3行水平样式，那么第1个卡位就是在第一列的 0 位置，第2个卡位 1，一次类推。但该方法具体被调用的场景还没理清
//            @Override
//            public int getSpanIndex(int position, int spanCount) {
//                return super.getSpanIndex(position, spanCount);
//            }
//        });
//        //官方建议说，如果延用默认的 getSpanIndxe() 的实现逻辑的话，那么建议调用下述方法来进行优化，否则每次布局计算时会很耗性能。
//        manager.getSpanSizeLookup().setSpanIndexCacheEnabled(true);

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        setData();
        rAdapter = new RecyclerAdapter(fuLians);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(rAdapter);
    }

    private void setData() {
        fuLians = new ArrayList<>();
        fuLians.add(new FuLian("猩红女巫",R.drawable.fulian1_copy));
        fuLians.add(new FuLian("黑豹",R.drawable.fulian4_copy));
        fuLians.add(new FuLian("黑寡妇",R.drawable.fulian5));
        fuLians.add(new FuLian("绿巨人",R.drawable.fulian6));
        fuLians.add(new FuLian("德拉克斯",R.drawable.fulian7));
        fuLians.add(new FuLian("星爵",R.drawable.fulian8));
        fuLians.add(new FuLian("幻世",R.drawable.fulian9));
        fuLians.add(new FuLian("唐",R.drawable.fulian11));
        fuLians.add(new FuLian("灭霸",R.drawable.fulian12));
        fuLians.add(new FuLian("雷神",R.drawable.fulian13));
        fuLians.add(new FuLian("阿凡达。",R.drawable.fulian16));
        fuLians.add(new FuLian("格鲁特",R.drawable.fulian19));
    }
}

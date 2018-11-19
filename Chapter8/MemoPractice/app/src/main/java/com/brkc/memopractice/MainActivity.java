package com.brkc.memopractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.brkc.memopractice.dao.ContentDatabase;
import com.brkc.memopractice.edittext.EditTextTools;
import com.brkc.memopractice.edittext.UpdateListener;
import com.brkc.memopractice.dao.Content;
import com.brkc.memopractice.listview.DidAdapter;
import com.brkc.memopractice.listview.DoingAdapter;
import com.brkc.memopractice.listview.NotifyListChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private EditText etContent;
    private TextView tvDoingNumber;
    private TextView tvDidNumber;

    private List<Content> doingContents;
    private List<Content> didContents;

    private DoingAdapter doingAdapter;
    private DidAdapter didAdapter;

    private void findViews() {
        etContent = (EditText) findViewById(R.id.et_content);
        Button btnDel = (Button) findViewById(R.id.btn_del);
        ListView lvDoing = (ListView) findViewById(R.id.lv_doing);
        tvDoingNumber = (TextView) findViewById(R.id.tv_doing_number);
        tvDidNumber = (TextView) findViewById(R.id.tv_did_number);
        ListView lvDid = (ListView) findViewById(R.id.lv_did);
        TextView tvClear = findViewById(R.id.tv_clear);

        doingAdapter = new DoingAdapter(this, doingContents, new NotifyDoingListListener());
        lvDoing.setAdapter(doingAdapter);
        didAdapter = new DidAdapter(this,didContents, new NotifyDidListListener());
        lvDid.setAdapter(didAdapter);

        // 加入添加/清除/输入监听
        EditTextTools.addClearListener(etContent, btnDel);
        EditTextTools.addChangeDataListener(this, new MyUpdateListener(), etContent);
        tvClear.setOnClickListener(new ClearListener());
        changeListNumber();
    }

    // 通知UI更新数据
    private void changeListNumber() {
        tvDoingNumber.setText(Integer.toString(doingContents.size()));
        doingAdapter.notifyDataSetChanged();
        tvDidNumber.setText(Integer.toString(didContents.size()));
        didAdapter.notifyDataSetChanged();
    }

    // 初始化数据，本章重点！！！
    private void initData() {
        doingContents = new ArrayList<>();
        didContents = new ArrayList<>();
        List<Content> list = query();
        for (Content c : list){
            if (!c.isCheck())
                doingContents.add(c);
            else
                didContents.add(c);
        }
    }

    // 增
    private void insert(Content content) {
        ContentDatabase.getInstance(MainActivity.this).getContentDao().insert(content);
    }
    // 删
    private void delete(Content content) {
        ContentDatabase.getInstance(MainActivity.this).getContentDao().delete(content);
    }
    // 改
    private void update(Content content) {
        ContentDatabase.getInstance(MainActivity.this).getContentDao().update(content);
    }
    // 查
    private List query() {
        return ContentDatabase
                .getInstance(MainActivity.this)
                .getContentDao()
                .getAllContents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        findViews();
    }

    // 保存修改后/编辑中的内容
    @Override
    protected void onStop() {
        super.onStop();
        List<Content> list = query();
        List<Content> list2 = doingContents;
        // 合并两个集合
        for (Content c : didContents){
            for (int i = 0; i < doingContents.size(); i++){
                if (i+1 == doingContents.size()){
                    list2.add(c);
                    break;
                }
                if (list2.get(i).getId() < c.getId()
                        && list2.get(i+1).getId() > c.getId()){
                    list2.add(i,c); // 插入指定的位置是插在当前位置的后一位。
                    break;
                }
            }
        }
        // 合并后的集合与数据库集合进行比较，如果内容不一致就修改
        for (int i = 0; i < list.size(); i++){
            if (!list.get(i).getContent().equals(list2.get(i).getContent()))
                update(list2.get(i));
        }
    }

    // 正在进行列表UI更新
    public class NotifyDoingListListener implements NotifyListChangeListener {
        @Override
        public void notifyRemove(int position) {
            delete(doingContents.get(position));
            doingContents.remove(position);
            changeListNumber();
        }

        @Override
        public void notifyInsert(int position) {
            Content c = doingContents.get(position);
            c.setCheck(true);
            didContents.add(c);
            doingContents.remove(position);
            changeListNumber();
            update(c);
        }

        @Override
        public void notifyUpdate(int position,String s) {
            Content c = doingContents.get(position);
            c.setContent(s);
            doingContents.set(position,c);
        }
    }

    // 已经完成列表UI更新
    public class NotifyDidListListener implements NotifyListChangeListener {

        @Override
        public void notifyRemove(int position) {
            delete(doingContents.get(position));
            didContents.remove(position);
            changeListNumber();
        }

        @Override
        public void notifyInsert(int position) {
            Content c = didContents.get(position);
            c.setCheck(false);
            doingContents.add(c);
            didContents.remove(position);
            changeListNumber();
            update(c);
        }

        @Override
        public void notifyUpdate(int position,String s) { }
    }

    // 一键清除
    private class ClearListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            doingContents.clear();
            didContents.clear();
            changeListNumber();
            // 一键清除数据库表
            ContentDatabase.getInstance(MainActivity.this).getContentDao().deleteAll();
        }
    }

    private class MyUpdateListener implements UpdateListener {

        @Override
        public void etUpdate(String s) {
            Content c = new Content(false, s);
            insert(c);
            doingContents.add(c);
            changeListNumber();
        }
    }
}

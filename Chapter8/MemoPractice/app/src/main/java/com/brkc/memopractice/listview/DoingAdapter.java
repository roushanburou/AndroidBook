package com.brkc.memopractice.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.brkc.memopractice.R;
import com.brkc.memopractice.dao.Content;
import com.brkc.memopractice.edittext.EditTextTools;
import com.brkc.memopractice.edittext.UpdateListener;

import java.util.List;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/13
 * @update 添加更新的内容
 */
public class DoingAdapter extends BaseAdapter {

    private final NotifyListChangeListener changeListener;
    private List<Content> listItems;
    private Context context = null;

    public DoingAdapter(Context context, List<Content> listItems, NotifyListChangeListener changeListener) {
        this.context = context;
        this.listItems = listItems;
        this.changeListener = changeListener;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Content item = (Content) getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_doing, parent, false);//LayoutInflater是用来找layout下xml布局文件，并且实例化
            viewHolder = new ViewHolder();
            //实例化
            viewHolder.cb_do = view.findViewById(R.id.cb_do);
            viewHolder.et_content = view.findViewById(R.id.et_content);
            viewHolder.btn_del = view.findViewById(R.id.btn_del);
            viewHolder.btn_remove = view.findViewById(R.id.btn_remove);
            view.setTag(viewHolder);
        } else {
            //这里复用了 ViewHolder
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.cb_do.setChecked(item.isCheck());
        viewHolder.cb_do.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 任务完成（改变位置）
                if (isChecked)
                    changeListener.notifyInsert(position);
            }
        });
        viewHolder.et_content.setText(item.getContent());
        EditTextTools.addClearListener(viewHolder.et_content, viewHolder.btn_del);
        EditTextTools.addChangeDataListener(context, null, viewHolder.et_content);
        viewHolder.et_content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String s = ((EditText) v).getText().toString();
                    changeListener.notifyUpdate(position, s);
                }
            }
        });
        viewHolder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除任务
                changeListener.notifyRemove(position);
            }
        });
        return view;
    }

    class ViewHolder {
        private CheckBox cb_do;
        private EditText et_content;
        private Button btn_del;
        private Button btn_remove;
    }
}

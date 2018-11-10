package com.brkc.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 普通对话框
     */
    public void dialog_1(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("我是对话框")//设置对话框的标题
                .setMessage("我是对话框的内容")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("帮助", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 列表对话框
     */
    public void dialog_2(View view) {
        final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("列表对话框")//设置对话框的标题
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 单选列表对话框
     */
    public void dialog_3(View view) {
        final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("单选列表对话框")//设置对话框的标题
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 多选列表对话框
     */
    public void dialog_4(View view) {
        final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
        final boolean checkedItems[] = {true, false, true, false};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("多选对话框")//设置对话框的标题
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                Toast.makeText(MainActivity.this, "选中了" + i, Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialog.dismiss();
                    }

                }).create();

        dialog.show();
    }

    /**
     * 圆形进度条对话框
     */
    public void dialog_5(View view) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载中");
        dialog.show();
    }

    /**
     * 水平进度条对话框
     */
    public void dialog_6(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("正在加载中");
        dialog.setMax(100);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                dialog.setProgress(progress += 5);
                if (progress == 100) {
                    timer.cancel();
                    dialog.dismiss();
                }
            }
        }, 0, 1000);
        dialog.show();
    }

    /**
     * 半自定义对话框
     */
    public void dialog_7(View view) {
        View v = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) v.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("半自定义对话框")//设置对话框的标题
                .setView(v)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 自定义对话框
     */
    public void dialog_8(View view) {
        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View v = getLayoutInflater().inflate(R.layout.dialog_normal, null);
        TextView cancel = (TextView) v.findViewById(R.id.cancel);
        TextView confirm = (TextView) v.findViewById(R.id.confirm);
        dialog.setContentView(v);
        //设置对话框的大小
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.75f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 常用自定义对话框
     */
    public void dialog_9(View view) {
        Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View v = View.inflate(this, R.layout.dialog_bottom, null);
        dialog.setContentView(v);
        // 点击对话框外部消失
        dialog.setCanceledOnTouchOutside(true);
        v.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /**
     * BottomSheetDialog
     */
    public void dialog_10(View view) {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View v = getLayoutInflater().inflate(R.layout.activity_main, null);
        dialog.setContentView(v);//设置显示的内容，我这为了方便就直接把主布局设置进去了。
        dialog.show();

        /**
         * 默认展开对话框
         */
        final FrameLayout frameLayout = (FrameLayout) dialog.findViewById(android.support.design.R.id.design_bottom_sheet);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得BottomSheetBehavior
                BottomSheetBehavior behavior = BottomSheetBehavior.from(frameLayout);
                //设置对话框的的状态
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN)
                            dialog.dismiss();
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    }
                });
            }
        });

    }
}

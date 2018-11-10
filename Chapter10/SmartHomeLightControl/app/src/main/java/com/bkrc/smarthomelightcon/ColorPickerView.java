package com.bkrc.smarthomelightcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ColorPickerView extends ImageView {
    Context context;//获取当前对象
    private Bitmap iconBitMap;//加载的吸管图片
    float iconRadius;// 吸管圆的半径
    PointF iconPoint;// 点击位置坐标
    Paint mBitmapPaint;//画笔
    Bitmap imageBitmap;//颜色图片
    float viewRadius;// 整个view半径
    float radius;// 图片半径
    Canvas canvas;//画布
    private OnColorChangedListener mChangedListener;//内部接口
    boolean isMove;//移动标志
    //一般在直接New一个View的时候调用。
    public ColorPickerView(Context context) {
        this(context, null);
    }
    //暂不使用
    public ColorPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }
    //一般在layout文件中使用的时候会调用，关于它的所有属性(包括自定义属性)都会包含在attrs中传递进来。
    public ColorPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }
    /**
     * 要想绘制内容，首先需要先初始化一个画笔
     */
    private void init() {
        iconBitMap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.pickup);// 吸管的图片
        iconRadius = iconBitMap.getWidth() / 2;// 吸管的图片一半
        mBitmapPaint = new Paint();
        iconPoint = new PointF();//x,y坐标是float类型
        imageBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        radius = imageBitmap.getHeight() / 2;// 图片半径

        //初始化点击坐标为图片的中心位置
        iconPoint.x = radius;
        iconPoint.y = radius;
    }
    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 测量View大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    /**
     * 绘制内容
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.canvas = canvas;
        viewRadius = this.getWidth() / 2;// 整个view半径
        //绘制位图
        canvas.drawBitmap(iconBitMap, iconPoint.x - iconRadius, iconPoint.y
                - iconRadius, mBitmapPaint);
    }
    /**
     *按下事件监听
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();//获取按下时候的x坐标
        float y = event.getY();//获取按下时候的y坐标
        int pixel;
        int r;
        int g;
        int b;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE://按下移动
                proofLeft(x, y);
                pixel = getImagePixel(iconPoint.x, iconPoint.y);//获取当前点击位置的像素点
                r = Color.red(pixel);//获取当前像素颜色的红色分量
                g = Color.green(pixel);//获取当前像素颜色的绿色分量
                b = Color.blue(pixel);//获取当前像素颜色的蓝色分量
                if (mChangedListener != null) {
                    mChangedListener.onMoveColor(r, g, b);
                }
                if (isMove) {
                    isMove = !isMove;
                    invalidate();//刷新界面
                }
                break;
            case MotionEvent.ACTION_UP://按下松开
                pixel = getImagePixel(iconPoint.x, iconPoint.y);
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);
                if (mChangedListener != null) {
                    mChangedListener.onColorChanged(r, g, b);
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 获取当前位置的像素点
     * @param x
     * @param y
     * @return
     */
    public int getImagePixel(float x, float y) {
        Bitmap bitmap = imageBitmap;
        // 为了防止越界
        int intX = (int) x;
        int intY = (int) y;
        if (intX < 0)
            intX = 0;
        if (intY < 0)
            intY = 0;
        if (intX >= bitmap.getWidth()) {
            intX = bitmap.getWidth() - 1;
        }
        if (intY >= bitmap.getHeight()) {
            intY = bitmap.getHeight() - 1;
        }
        int pixel = bitmap.getPixel(intX, intY);
        return pixel;

    }
    /**
     * R = sqrt(x * x + y * y)
     * point.x = x * r / R + r
     * point.y = y * r / R + r
     */
    private void proofLeft(float x, float y) {
        float h = x - viewRadius; // 取xy点和圆点 的三角形宽
        float w = y - viewRadius;// 取xy点和圆点 的三角形长
        float h2 = h * h;
        float w2 = w * w;
        float distance = (float) Math.sqrt((h2 + w2)); // 勾股定理求 斜边距离
        if (distance > radius) { // 如果斜边距离大于半径，则取点和圆最近的一个点为x,y
            float maxX = x - viewRadius;
            float maxY = y - viewRadius;
            x = ((radius * maxX) / distance) + viewRadius; // 通过三角形一边平行原理求出x,y
            y = ((radius * maxY) / distance) + viewRadius;
        }
        iconPoint.x = x;
        iconPoint.y = y;
        isMove = true;
    }
    public void setOnColorChangedListenner(OnColorChangedListener l) {
        this.mChangedListener = l;
    }
    // 内部接口 回调颜色 rgb值
    public interface OnColorChangedListener {
        // 手指抬起，确定颜色回调
        void onColorChanged(int r, int g, int b);

        // 移动时颜色回调
        void onMoveColor(int r, int g, int b);
    }
}

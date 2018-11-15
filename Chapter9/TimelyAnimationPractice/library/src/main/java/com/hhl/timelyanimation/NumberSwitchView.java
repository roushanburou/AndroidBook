package com.hhl.timelyanimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.hhl.timelyanimation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NumberSwitchView extends View {
    private static final String TAG = "NumberSwitchView";
    //
    private int WIDTH_PRE = 120;   // px  120
    private int HEIGHT_PRE = 180;  // px  180
    //
    private int numberWrapSpace = 1; //dp
    private int numberAnimationDuration = 500; // ms
    private int numberColor = 0XFF1A2634;   // 数字颜色
    private int numberBGColor = 0xFFABCDEF; // 控件背景色
    private float numberStrokeWidth = 4; // px
    //
    private float widthRatio = 1;
    private float heightRatio = 1;
    private Paint mPaint;
    private NumberSwitchAnimation switchAnimation;

    private List<float[]> numbers = new ArrayList<float[]>();
    private float[] currentNumberPoints;

    public NumberSwitchView(Context context) {
        this(context, null);
    }

    public NumberSwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public void setChangeNumber(int WIDTH_PRE, int HEIGHT_PRE) {
        this.WIDTH_PRE = WIDTH_PRE;
        this.HEIGHT_PRE = HEIGHT_PRE;

    }

    public NumberSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 配置自定义控件的属性
        numberWrapSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, numberWrapSpace, context.getResources().getDisplayMetrics());
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberSwitchView);
        numberAnimationDuration = typedArray.getInt(R.styleable.NumberSwitchView_ta_number_animation_duration, numberAnimationDuration);
        numberBGColor = typedArray.getColor(R.styleable.NumberSwitchView_ta_number_bg_color, numberBGColor);
        numberColor = typedArray.getColor(R.styleable.NumberSwitchView_ta_number_color, numberColor);
        numberStrokeWidth = typedArray.getDimension(R.styleable.NumberSwitchView_ta_number_stroke_width, numberStrokeWidth);
        Log.i(TAG, "numberAnimationDuration = " + numberAnimationDuration);
        Log.i(TAG, "numberBGColor = " + numberBGColor + "");
        Log.i(TAG, "numberColor = " + numberColor + "");
        Log.i(TAG, "numberStrokeWidth = " + numberStrokeWidth);
        // 回收，便于复用
        typedArray.recycle();
        initialize();
    }

    // 初始化
    private void initialize() {
        Log.i(TAG, "init");
        setData();
        // 准备画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(numberColor);
        mPaint.setStrokeWidth(numberStrokeWidth);
        mPaint.setPathEffect(new CornerPathEffect(200));
        // 配置动画特效
        Interpolator interc = AnimationUtils.loadInterpolator(getContext(), android.R.interpolator.accelerate_decelerate);
        switchAnimation = new NumberSwitchAnimation();
        // 设置动画持续时间
        switchAnimation.setDuration(numberAnimationDuration);
        // 先加速后减速的插值器
        switchAnimation.setInterpolator(interc);
        // 在动画完成后暂停在最后一帧
        switchAnimation.setFillAfter(true);
    }

    private void setData() {
        numbers.add(NumberData.NUMBER_0);
        numbers.add(NumberData.NUMBER_1);
        numbers.add(NumberData.NUMBER_2);
        numbers.add(NumberData.NUMBER_3);
        numbers.add(NumberData.NUMBER_4);
        numbers.add(NumberData.NUMBER_5);
        numbers.add(NumberData.NUMBER_6);
        numbers.add(NumberData.NUMBER_7);
        numbers.add(NumberData.NUMBER_8);
        numbers.add(NumberData.NUMBER_9);
        // 初始化
        currentNumberPoints = Arrays.copyOf(NumberData.NUMBER_0, NumberData.NUMBER_0.length);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        // 动画过程的绘制
        canvas.translate(numberStrokeWidth / 2, numberStrokeWidth / 2);
        canvas.scale(this.widthRatio, this.heightRatio);
        canvas.drawColor(numberBGColor);
        canvas.drawPath(makeNumberPath(currentNumberPoints), mPaint);

        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.widthRatio = (width + this.numberStrokeWidth) / WIDTH_PRE;
        this.heightRatio = (height + this.numberStrokeWidth) / HEIGHT_PRE;
        setMeasuredDimension((int) (width + this.numberStrokeWidth), (int) (height + this.numberStrokeWidth));
        Log.i(TAG, "width = " + width + ", height = " + height);
    }


    // 0 - 9
    public void animateTo(int number) {
        synchronized (this) {
            if (number >= 0 && number <= 9) {
                Log.i(TAG, numbers.get(number).length + "");
                switchAnimation.setData(currentNumberPoints, numbers.get(number));
                startAnimation(switchAnimation);
            } else {
                // 可以扩展出字母、中文等等。
            }
        }
    }


    class NumberSwitchAnimation extends Animation {
        private float[] to;
        private float[] from;

        public void setData(float[] from, float[] to) {
            this.from = Arrays.copyOf(from, from.length);
            this.to = to;
        }

        // 动画特效关键！
        // 特效本质：点平移动画。
        // 实现思路：在此之前我们已经定义了每个数字的108点，将108个点用线连起来就是我们的数字。
        //           因为每个不同数字间的点分布是不同的，所以我们可以通过startAnimation()启动点
        //           的平移动画，平移过程点的分布在重写的 applyTransformation 中实现。然后，每
        //           改变一次分布就通过 invalidate() 方法刷新界面，通过 onDraw() 重新绘制。
        // 思考：
        //           留一个思考题，我们可以看到 TranslateAnimation 是通过
        //           t.getMatrix().setTranslate(dx, dy) 方法实现平移特效的，显然这种方法更加高效。
        //          那么我们能否也效仿此举，改写如今使用 invalidate() 以达刷新的方法。
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            if (interpolatedTime != 1) {
                // 这样写 for 减少计算量
                for (int i = 0, N = from.length; i < N; i++) {
                    // 通过该算法能计算出平移过程中的数字点的分布位置，也就是实现平移的过程。
                    // 该算法可以抄 TranslateAnimation 源码下的 applyTransformation 算法实现。
                    currentNumberPoints[i] = from[i] + (to[i] - from[i]) * interpolatedTime;
                }
                invalidate();
            }
        }
    }

    // 绘制数字经过的整条路径
    private Path makeNumberPath(float[] points) {
        Path p = new Path();
        p.moveTo(points[0], points[1]);
        for (int i = 2, N = points.length; i < N; i += 2) {
            // 画出两点之间的线段
            p.lineTo(points[i], points[i + 1]);
        }
        return p;
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public int getNumberColor() {
        return numberColor;
    }

    public void setNumberColor(int numberColor) {
        this.numberColor = numberColor;
        mPaint.setColor(numberColor);
        invalidateView();
    }

    public void setNumberBGColor(int numberBGColor) {
        this.numberBGColor = numberBGColor;
        invalidateView();
    }

    public int getNumberBGColor() {
        return numberBGColor;
    }


}

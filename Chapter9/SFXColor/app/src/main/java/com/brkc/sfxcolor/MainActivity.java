package com.brkc.sfxcolor;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity implements View.OnClickListener{
    private ImageView iv;
    private Button retrobt,discolorbt,graybt,reversalbt;
    private Canvas canvas;
    private Bitmap copyBitmap;
    private Bitmap baseBitmap;
    private Paint paint;
    //怀旧效果
    private float[] retro_colorArray=new float[]{
            0.393f,0.769f,0.189f,0,0,
            0.349f,0.686f,0.168f,0,0,
            0.272f,0.543f,0.131f,0,0,
            0,0,0,1,0
    };
    //去色效果
    private float[] discolor_colorArray=new float[]{
            1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            1.5f,1.5f,1.5f,0,-1,
            0,0,0,1,0
    };

    //灰度效果
    private float[] gray_colorArray=new float[]{
            0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0.33f,0.59f,0.11f,0,0,
            0,0,0,1,0
    };
    //图片反转效果
    private float[] reversal_colorArray=new float[]{
            -1,0,0,1,1,
            0,-1,0,1,1,
            0,0,-1,1,1,
            0,0,0,1,0
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        iv= findViewById(R.id.image);
        retrobt =findViewById(R.id.retro);
        discolorbt =findViewById(R.id.discolor);
        graybt =findViewById(R.id.gray);
        reversalbt =findViewById(R.id.reversal);

        retrobt.setOnClickListener(this);
        discolorbt.setOnClickListener(this);
        graybt.setOnClickListener(this);
        reversalbt.setOnClickListener(this);

    }
    private float[] colorArray_use;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.retro:   //怀旧效果
                colorArray_use =retro_colorArray;
                break;
            case R.id.discolor:   //去色效果
                colorArray_use =discolor_colorArray;
                break;
            case R.id.gray:   //灰度效果
                colorArray_use =gray_colorArray;
                break;
            case R.id.reversal: //反转效果
                colorArray_use =reversal_colorArray;
                break;
            default:
                break;
        }
        initBitmap();
    }

    private void initBitmap() {

        //先加载出一张原图(baseBitmap)，然后复制出来新的图片(copyBitmap)来，因为andorid不允许对原图进行修改.
        baseBitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.sights);
        //既然是复制一张与原图一模一样的图片那么这张复制图片的画纸的宽度和高度以及分辨率都要与原图一样,copyBitmap就为一张与原图相同尺寸分辨率的空白画纸
        copyBitmap=Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());
        canvas=new Canvas(copyBitmap);//将画纸固定在画布上

        paint=new Paint();//实例画笔对象
        ColorMatrix colorMatrix=new ColorMatrix(colorArray_use);//将保存的颜色矩阵的数组作为参数传入
        ColorMatrixColorFilter colorFilter=new ColorMatrixColorFilter(colorMatrix);//再把该colorMatrix作为参数传入来实例化ColorMatrixColorFilter
        paint.setColorFilter(colorFilter);//并把该过滤器设置给画笔

        canvas.drawBitmap(baseBitmap, new Matrix(), paint);//传如baseBitmap表示按照原图样式开始绘制，将得到复制后的图片
        iv.setImageBitmap(copyBitmap);
    }
}


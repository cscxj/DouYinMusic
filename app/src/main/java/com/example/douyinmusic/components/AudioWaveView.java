package com.example.douyinmusic.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.douyinmusic.R;

import java.util.Random;

public class AudioWaveView extends View {
    private Paint paint;
    private RectF rectF1;
    private RectF rectF2;
    private RectF rectF3;
    private RectF rectF4;
    private RectF rectF5;
    private int viewWidth;
    private int viewHeight;
    /**
     * 每个条的宽度
     */
    private int rectWidth;
    /**
     * 条数
     */
    private int columnCount = 5;
    /**
     * 条间距
     */
    private int space = 6;
    /**
     * 条随机高度
     */

    private int randomHeight;
    /**
     * 指示器颜色
     */
    private boolean shouldDraw = true;
    private int indicatorColor;
    private Random random;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

    public AudioWaveView(Context context) {
        super(context);
        init();
    }

    public AudioWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AudioWaveView);
        indicatorColor = typedArray.getColor(R.styleable.AudioWaveView_color, Color.BLACK);
        space = typedArray.getDimensionPixelSize(R.styleable.AudioWaveView_sliverSpace, 6);
        init();
    }

    public void pause(){
        this.shouldDraw = false;
    }
    public void play(){
        this.shouldDraw = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectWidth = (viewWidth - space * (columnCount - 1)) / columnCount;
    }

    private void init() {
        paint = new Paint();
        paint.setColor(indicatorColor);
        paint.setStyle(Paint.Style.FILL);
        random = new Random();
        initRect();
    }

    private void initRect() {
        rectF1 = new RectF();
        rectF2 = new RectF();
        rectF3 = new RectF();
        rectF4 = new RectF();
        rectF5 = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!shouldDraw) return;
        int left = rectWidth + space;

        randomHeight = random.nextInt(viewHeight);
        rectF1.set(left * 0, randomHeight, left * 0 + rectWidth, viewHeight);
        randomHeight = random.nextInt(viewHeight);
        rectF2.set(left * 1, randomHeight, left * 1 + rectWidth, viewHeight);
        randomHeight = random.nextInt(viewHeight);
        rectF3.set(left * 2, randomHeight, left * 2 + rectWidth, viewHeight);
        randomHeight = random.nextInt(viewHeight);
        rectF4.set(left * 3, randomHeight, left * 3 + rectWidth, viewHeight);
        randomHeight = random.nextInt(viewHeight);
        rectF5.set(left * 4, randomHeight, left * 4 + rectWidth, viewHeight);

        canvas.drawRect(rectF1, paint);
        canvas.drawRect(rectF2, paint);
        canvas.drawRect(rectF3, paint);
        canvas.drawRect(rectF4, paint);
        canvas.drawRect(rectF5, paint);

        handler.sendEmptyMessageDelayed(0, 100); //每间隔200毫秒发送消息刷新
    }

}
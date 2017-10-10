package chart.muke.com.mukechart.actualchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import chart.muke.com.mukechart.utils.MukeUtils;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/10  18:55
 * @Version v1.0.0
 * @Annotation 可左右滚动查看的24小时天气预报图
 */
public class WeatherView extends View {
    private Context mContext;

    /**
     * 24小时空气中，每小格的宽度
     **/
    private int mAirCellWidth = 76;//76px
    private int mAirCellHeight = 26;//26px

    /**
     * 空气每小格之间的间隔
     **/
    private int mAirPadding = 4;//4px

    /***各画笔**/
    private Paint mAirPaint;
    private Paint mWindPaint;
    private Paint mWeatherPaint;


    /**
     * View的宽高
     **/
    private int mViewWidth;
    private int mViewHeight;

    /**
     * 空气部分的起点坐标
     **/
    private int mAirOffestX;
    private int mAirOffestY;

    private int mPadding = 15;//view边距

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {

        mAirPaint = new Paint();
        mAirPaint.setColor(0xFF28A77F);
        mAirPaint.setDither(true);
        mAirPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = mAirCellWidth * 24 + mAirPadding * 23 + mPadding * 2;

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int[] screenSize = MukeUtils.getScreenWidth_Height(mContext);

        if (heightMode == MeasureSpec.EXACTLY) {
            mViewHeight = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            mViewHeight = screenSize[1];
        }
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFF4F4A4F);
        drawAir(canvas);

        Paint paint = new Paint();
        paint.setColor(0xFF28A77F);

        RectF rectF = new RectF();
        rectF.left = 100;
        rectF.right = 400;
        rectF.top = 100;
        rectF.bottom = 400;

        canvas.drawRect(rectF, paint);//DST
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.RED);
        canvas.drawRoundRect(rectF, 50, 50, paint);//SRC
        paint.setXfermode(null);
        int sc = canvas.saveLayer(0, 0, mViewWidth, mViewHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.restoreToCount(sc);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 画空气部分的值
     *
     * @param canvas
     */
    private void drawAir(Canvas canvas) {
        mAirOffestX = mPadding;
        mAirOffestY = getHeight() - mPadding;

        for (int i = 0; i < 24; i++) {

            //先画矩形
            RectF rectF = new RectF();
            rectF.left = mAirOffestX + (mAirCellWidth + mAirPadding) * i;
            rectF.top = mAirOffestY;
            rectF.right = mAirCellWidth * (i + 1) + mAirPadding * i;
            rectF.bottom = mAirOffestY + mAirCellHeight;
            canvas.drawRect(rectF, mAirPaint);
            mAirPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawRoundRect(rectF, 5, 5, mAirPaint);
            //再画圆角矩形
            mAirPaint.setXfermode(null);
            //最后进行合并
        }
    }
}

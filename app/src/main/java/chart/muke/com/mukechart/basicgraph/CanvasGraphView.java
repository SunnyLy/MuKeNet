package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import chart.muke.com.mukechart.constant.MukeConstant;
import chart.muke.com.mukechart.utils.MukeUtils;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/9/28  14:21
 * @Version v1.0.0
 * @Annotation
 */
public class CanvasGraphView extends View {

    private Context mContext;

    Paint mPaint;

    int mPaintColor = Color.BLACK;

    Paint.Style mStyle = Paint.Style.STROKE;

    int mViewStyle = MukeConstant.ViewStyle.NONE;

    int offesetX = 100;
    int offesetY = 100;
    int padding = 35;

    int width,height;

    public CanvasGraphView setmPaintColor(int mPaintColor) {
        this.mPaintColor = mPaintColor;
        return this;
    }

    public CanvasGraphView setStyle(Paint.Style mStyle) {
        this.mStyle = mStyle;
        return this;
    }

    public CanvasGraphView setViewStyle(int mViewStyle) {
        this.mViewStyle = mViewStyle;
        return this;
    }

    public void build() {
        if (mPaint != null){
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
        }
        invalidate();
    }

    public CanvasGraphView(Context context) {
        this(context, null);
    }

    public CanvasGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CanvasGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int[] screenSize = MukeUtils.getScreenWidth_Height(mContext);
        if (widthMode == MeasureSpec.EXACTLY){
            //如果父布局已经给定宽度大小
            //表现形式为：match_parent或具体的值
            width = widthSize;
        }else if (widthMode == MeasureSpec.UNSPECIFIED){
            //当父控件对子控件无任何约束条件时，子控件可以想要任何大小
            width = screenSize[0];//赋屏幕宽度
        }else if (widthMode == MeasureSpec.AT_MOST){
            width = screenSize[0];//最多不超过屏幕宽度
        }


        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST){
            height = screenSize[1];
        }

        Log.e(",,,,", "widthMode:" + widthMode + ",heightMode:" + heightMode + ",widthSize:" + widthSize + ",heightSize:" + heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        canvas.drawColor(Color.GRAY);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        offesetX = width/2;
        offesetY = getHeight()/2;
        mPaint.setColor(mPaintColor);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(mStyle);
        int radius = offesetX/2;
        switch (mViewStyle) {
            case MukeConstant.ViewStyle.ROUND_RECT:
                RectF rect = new RectF();
                rect.left = offesetX - radius;
                rect.top = offesetY - radius/2;
                rect.bottom = offesetY + radius/2;
                rect.right = offesetX + radius;
                canvas.drawRoundRect(rect,20,20,mPaint);
                break;

            case MukeConstant.ViewStyle.TRIANGLE:
                //A点
                int verAx = offesetX - radius;
                int verAy = offesetY + radius;

                //B点
                int verBx = verAx + radius*2;
                int verBy = verAy;

                //C点
                int verCx = offesetX;
                int verCy = offesetY - radius;

                Path trianglePath = new Path();
                trianglePath.moveTo(verAx,verAy);
                trianglePath.lineTo(verBx,verBy);
                trianglePath.lineTo(verCx,verCy);
                trianglePath.close();

                canvas.drawPath(trianglePath,mPaint);
                break;
            case MukeConstant.ViewStyle.BEZIER:
                //画多元贝塞尔曲线
                Path bezierPath = new Path();
                PointF startP = new PointF();
                startP.set(offesetX - radius,offesetY+radius/2);
                PointF controlP1 = new PointF();
                controlP1.set(offesetX - radius/2,offesetY + radius/4);
                PointF controlP2 = new PointF();
                controlP2.set(offesetX+radius/2,offesetY-radius);
                PointF endP = new PointF();
                endP.set(offesetX+radius,offesetY+radius/2);
                bezierPath.moveTo(startP.x,startP.y);
                bezierPath.cubicTo(controlP1.x,controlP1.y,controlP2.x,controlP2.y,endP.x,endP.y);
                canvas.drawPath(bezierPath,mPaint);

                break;
            case MukeConstant.ViewStyle.ARC:
                RectF rectF = new RectF();
                rectF.left = offesetX - radius;
                rectF.right = offesetX + radius;
                rectF.top = offesetY - radius;
                rectF.bottom = offesetY+radius;
                canvas.drawArc(rectF,150,240,true,mPaint);
                break;
            case MukeConstant.ViewStyle.NONE:
            case MukeConstant.ViewStyle.CIRCLE:
                Log.e(",,,,,", "offesetX:" + offesetX + ",offsetY:" + offesetY + ",top:" + getTop());
                canvas.drawCircle(offesetX, offesetY, offesetX / 2, mPaint);
            default:
                break;
        }
    }
}

package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/9/27  14:26
 * @Version v1.0.0
 * @Annotation 基础曲线图
 */
public class MukeCurveView extends BaseGraphView {

    // 坐标单位
    private String[] xLabel;
    private String[] yLabel;
    // 曲线数据
    private List<Integer> dataList;

    // 默认边距
    private int margin = 20;
    // 距离左边偏移量
    private int marginX = 30;
    // 原点坐标
    private int xPoint;
    private int yPoint;
    // X,Y轴的单位长度
    private int xScale;
    private int yScale;

    private boolean fill = false;

    public void setFill(boolean fill) {
        this.fill = fill;
        invalidate();
    }

    public void setDataList(List<Integer> dataList) {
        this.dataList = dataList;
        invalidate();
    }

    public MukeCurveView(Context context) {
        this(context, null);
    }

    public MukeCurveView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MukeCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        xPoint = margin + marginX;
        yPoint = this.getHeight() - margin;
    }

    @Override
    protected void drawLabel(Canvas canvas, Paint paint) {

    }

    @Override
    protected void drawColumnCurve(Canvas canvas, Paint paint) {

        if (paint != null) {
            paint.reset();
            //默认是不封闭
            if (fill)
            paint.setStyle(Paint.Style.FILL);
            else{
                paint.setStyle(Paint.Style.STROKE);
            }
            paint.setColor(ColorTemplate.COLORFUL_COLORS[3]);
            //因为是画曲线，用CornerPathEffect()会更圆滑
            CornerPathEffect pathEffect = new CornerPathEffect(25);
            paint.setPathEffect(pathEffect);
            paint.setStrokeWidth(3);
            paint.setDither(true);
            paint.setAntiAlias(true);

            drawCurves(canvas, paint);
        }
    }

    private void drawCurves(Canvas canvas, Paint paint) {
        if (dataList != null && dataList.size() > 0) {
            xScale = (width - originX - padding * 2) / (axisDivideSizeX - 1);
            yScale = getCellWidth();
            Path path = new Path();
            int xAxisMax = width - originX - padding;
            Log.e(",,,,,", "size:" + dataList.size() + ",xScale:" + xScale + ",yScale:" + yScale);
            //最大值的X,Y坐标
            int maxVertexX = originX;
            int maxVertexY = originY;
            for (int i = 0; i < dataList.size() - 1; i++) {
                if (maxAxisValueY == dataList.get(i) && i != 0){
                    //最大值，用来计算渐变的顶点
                    maxVertexX = (originX + i * xScale) > xAxisMax?(originX+i*xScale):xAxisMax;
                    maxVertexY = dataList.get(i);
                }
                if (i == 0) {
                    path.moveTo(originX, toY(dataList.get(0)));
                } else {
                    if (originX + i*xScale > xAxisMax){
                        path.lineTo(xAxisMax, toY(dataList.get(i)));
                    }else{
                        path.lineTo(originX + i * xScale, toY(dataList.get(i)));
                    }
                }

                if (i == axisDivideSizeX - 1) {
                    if (originX+i*xScale > xAxisMax){
                        path.lineTo(xAxisMax, toY(dataList.get(i)));
                    }else{
                        path.lineTo(originX + i * xScale, toY(dataList.get(i)));
                    }
                }
            }
            path.lineTo(originX+(axisDivideSizeX - 1)*xScale,originY);
            path.lineTo(originX,originY);

            /**
             * x0,y0:渐变的起点坐标
             * x1,y1:渐变的终点坐标
             * 用来控制，水平/垂直渐变
             */
            LinearGradient mShader = new LinearGradient(0,0,originX,originY,
                    new int[] {paint.getColor(),Color.TRANSPARENT},null, Shader.TileMode.CLAMP);
            paint.setShader(mShader);
            canvas.drawPath(path, paint);
        } else {
            Log.e(",,,,", "000000000");
        }
    }

    /**
     * 数据按比例转坐标
     */
    private float toY(int num) {
        float y;
        try {
            float a = (float) num / 100.0f;
            y = originY - a * yScale;
        } catch (Exception e) {
            return 0;
        }
        return y;
    }

}

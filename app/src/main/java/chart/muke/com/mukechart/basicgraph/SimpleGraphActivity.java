package chart.muke.com.mukechart.basicgraph;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.github.mikephil.charting.utils.ColorTemplate;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;
import chart.muke.com.mukechart.constant.MukeConstant;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/9/28  10:08
 * @Version v1.0.0
 * @Annotation 基础图形的绘制
 */
public class SimpleGraphActivity extends MukeBaseActivity {
    private CanvasGraphView mGraphView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_graph;
    }

    @Override
    protected void initParams() {

        setTitle(R.string.title_simple_graph);

        mGraphView = (CanvasGraphView) findViewById(R.id.basic_graph);
    }

    /**
     * 画圆
     *
     * @param view
     */
    public void drawCircle(View view) {

        mGraphView.setStyle(Paint.Style.FILL)
                .setmPaintColor(ColorTemplate.JOYFUL_COLORS[3])
                .setViewStyle(MukeConstant.ViewStyle.CIRCLE)
                .build();
    }

    /**
     * 画三角形
     *
     * @param view
     */
    public void drawTriangle(View view) {

        mGraphView.setViewStyle(MukeConstant.ViewStyle.TRIANGLE)
                .setStyle(Paint.Style.STROKE)
                .setmPaintColor(ColorTemplate.getHoloBlue())
                .build();
    }

    /**
     * 画圆角矩形
     *
     * @param view
     */
    public void drawRect(View view) {
        mGraphView.setViewStyle(MukeConstant.ViewStyle.ROUND_RECT)
                .setStyle(Paint.Style.FILL_AND_STROKE)
                .setmPaintColor(ColorTemplate.MATERIAL_COLORS[0])
                .build();
    }

    /**
     * 画弧形
     *
     * @param view
     */
    public void drawArc(View view) {
        mGraphView.setViewStyle(MukeConstant.ViewStyle.ARC)
                .setStyle(Paint.Style.FILL)
                .setmPaintColor(ColorTemplate.VORDIPLOM_COLORS[0])
                .build();
    }

    /**
     * 画贝塞尔曲线
     *
     * @param view
     */
    public void drawBezier(View view) {
        mGraphView.setViewStyle(MukeConstant.ViewStyle.BEZIER)
                .setStyle(Paint.Style.STROKE)
                .setmPaintColor(ColorTemplate.PASTEL_COLORS[0])
                .build();
    }

}

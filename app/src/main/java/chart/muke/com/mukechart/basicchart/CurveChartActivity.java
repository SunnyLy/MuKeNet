package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * @Annotation <p>曲线图界面:
 *                我们都知道，曲线是一条条的线段连接成的，那么，曲线图其实
 *                它也就是直线图
 *              在智能硬件相关APP上比较常见，比如：实时展示心率等</p>
 * @Auth Sunny
 * @date 2017/9/10
 * @Version V1.0.0
 */

public class CurveChartActivity extends MukeBaseActivity {

    private LineChart mCurveChart;
    @Override
    protected void initParams() {
        setTitle(R.string.title_curve_chart);

        //初始化直线图相关参数
        initLineChart();
    }

    private void initLineChart() {
        //设置展示区域背景色
        mCurveChart.setBackgroundColor(Color.rgb(104, 241, 175));
        // 可触摸
        mCurveChart.setTouchEnabled(true);

        // 可绽放跟拖拽
        mCurveChart.setDragEnabled(true);
        mCurveChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mCurveChart.setPinchZoom(false);

        mCurveChart.setDrawGridBackground(true);

        XAxis x = mCurveChart.getXAxis();
        x.setEnabled(false);//不使用常规的X轴

        YAxis y = mCurveChart.getAxisLeft();
      //  y.setTypeface(mTLeftType);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        mCurveChart.getAxisRight().setEnabled(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_curve_chart;
    }

    @Override
    public void onContentChanged() {
        mCurveChart = (LineChart) findViewById(R.id.bc_line_chart);
    }

    @Override
    public void onClick(View v) {

    }
}

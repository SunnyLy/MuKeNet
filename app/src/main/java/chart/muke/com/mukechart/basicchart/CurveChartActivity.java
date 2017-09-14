package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

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

        //1.初始化直线图相关参数
        initLineChart();
        //2.获取图表数据
        setChartData(45,100);
        //3.进行绘制
        mCurveChart.invalidate();

    }

    private void setChartData(int count,float range) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 20;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mCurveChart.getData() != null &&
                mCurveChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mCurveChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mCurveChart.getData().notifyDataChanged();
            mCurveChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
          //  data.setValueTypeface(mTfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mCurveChart.setData(data);
        }
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

        mCurveChart.setDrawGridBackground(false);//是否画网格背景

        //设置附加信息
        Description description = new Description();
        description.setText("简单曲线图");
        mCurveChart.setDescription(description);

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

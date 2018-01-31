package chart.muke.com.mukechart.basicchart;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;
import chart.muke.com.mukechart.formatter.DayAxisValueFormatter;
import chart.muke.com.mukechart.formatter.MyAxisValueFormatter;

/**
 * @Annotation <p>用MPAndroidChart库来实现直方图</p>
 * @Auth Sunny
 * @date 2017/9/4
 * @Version V1.0.0
 */

public class BarChartActivity extends MukeBaseActivity {

    private BarChart mBarChart;
    private BarData mBarDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_bar_chart);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
        }
    }

    @Override
    protected void initParams() {
        if (mBarDatas != null){
            mBarDatas.setBarWidth(25);//设置柱条的宽度
            mBarDatas.setDrawValues(true);//是否显示值
            mBarDatas.setHighlightEnabled(true);//是否高亮
            mBarDatas.setValueTextSize(16);
        }

        //初始化X軸的值
        initXAxisValues();
        //初始化Y軸的值
        initYAxisValues();
        initLegend();
        setData(12,60);

    }

    private void initLegend() {
        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }

    private void initYAxisValues() {
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = mBarChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
    }

    private void initXAxisValues() {
        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mBarChart);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//設置X軸的方向
       // xAxis.setTypeface(mTfLight);//設置字體
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);//設置X軸的值轉換器
    }

    private void setData(int count, float range) {
        float start = 1f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.mipmap.ic_launcher)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");
            set1.setDrawIcons(false);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mBarChart.setData(data);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bar_chart;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onContentChanged() {
        mBarChart = findView(R.id.bc_barchart);
//        mBarChart.setData(mBarDatas);
        mBarChart.setMaxVisibleValueCount(60);//最多可見數
    }
}

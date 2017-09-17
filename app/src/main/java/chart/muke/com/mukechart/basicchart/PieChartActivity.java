package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * @Annotation <p>饼状图</p>
 * @Auth Sunny
 * @date 2017/9/17
 * @Version V1.0.0
 */

public class PieChartActivity extends MukeBaseActivity {
    private static final String LABEL1 = "PieChartLabel";

    private String[] mParties = {"Java","JavaScript","Python","ObjectC","C","Swift","C++",".Net"};

    //每一部分的颜色值
    private int[] mColors = {  Color.rgb(192, 255, 140),
            Color.rgb(255, 247, 140),
            Color.rgb(255, 208, 140),
            Color.rgb(140, 234, 255),
            Color.rgb(255, 140, 157),
            Color.rgb(217, 80, 138),
            Color.rgb(254, 149, 7),
            Color.rgb(254, 247, 120)
    };

    private PieChart mPieChart;
    @Override
    public int getLayoutId() {
        setTitle(R.string.title_pie_chart);
        return R.layout.activity_pie_chart;
    }

    @Override
    public void onContentChanged() {
        mPieChart = (PieChart) findViewById(R.id.pie_chart);
    }

    @Override
    protected void initParams() {

        initPieChart();
        setPieChartData(5,100);
        mPieChart.invalidate();
    }

    private void initPieChart() {
        mPieChart.setNoDataText(getResources().getString(R.string.no_data));
        mPieChart.setCenterText(getResources().getString(R.string.pie_center));
        mPieChart.setDrawCenterText(true);

        //设置饼状图最大角度，圆形：360，半圆形：180
        mPieChart.setMaxAngle(360);

        //是否用百分比来显示值
        mPieChart.setUsePercentValues(true);

        mPieChart.setDrawEntryLabels(true);//相當于X軸座標

        mPieChart.setTouchEnabled(true);

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        //设置标记显示的位置
        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setEnabled(true);
        l.setDrawInside(true);
    }

    private void setPieChartData(int count,float range) {

        List<PieEntry> values = new ArrayList<>();
        for (int i =0;i<count;i++){
            float val = (float) (Math.random()*range + range/5);
            PieEntry pieEntry = new PieEntry(val,mParties[i%mParties.length],null);
            values.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(values,LABEL1);
        pieDataSet.setSelectionShift(10);
        pieDataSet.setDrawValues(true);

        //设置值的字体颜色大小
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(16);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
       for (int c : mColors){
           colors.add(c);
       }
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        mPieChart.setData(pieData);
    }
}

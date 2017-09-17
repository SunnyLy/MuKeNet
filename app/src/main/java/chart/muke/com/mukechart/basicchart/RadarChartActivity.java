package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * @Annotation <p>雷达图</p>
 * @Auth Sunny
 * @date 2017/9/17
 * @Version V1.0.0
 */

public class RadarChartActivity extends MukeBaseActivity {

    private RadarChart mRadarChart;

    private String[] mDemissions = {"身份特质","履约能力","信用历史","人脉关系","行为偏好"};
    private Drawable[] mLabelIcons = new Drawable[5];
    @Override
    public int getLayoutId() {
        setTitle(R.string.title_randar_chart);
        return R.layout.activity_randar_chart;
    }

    @Override
    public void onContentChanged() {
        mRadarChart = (RadarChart) findViewById(R.id.radar_chart);
    }

    @Override
    protected void initParams() {
            mLabelIcons[0] = getResources().getDrawable(R.mipmap.ic_identity);
        mLabelIcons[1] = getResources().getDrawable(R.mipmap.ic_performance);
        mLabelIcons[2] = getResources().getDrawable(R.mipmap.ic_history);
        mLabelIcons[3] = getResources().getDrawable(R.mipmap.ic_contacts);
        mLabelIcons[4] = getResources().getDrawable(R.mipmap.ic_predilection);
        initRadarChart();
        setData(mDemissions.length,100);
        mRadarChart.invalidate();
    }

    private void setData(int count,float range) {
        List<RadarEntry> values = new ArrayList<>();
        for (int i=0;i<count;i++){
            float val = (float) (Math.random()*range +10);
            RadarEntry radarEntry = new RadarEntry(val);
            values.add(radarEntry);
        }

        RadarDataSet radarDataSet = new RadarDataSet(values,"RadarDataSet");
        radarDataSet.setDrawFilled(true);//封闭图形
        radarDataSet.setLineWidth(1);//线宽
        radarDataSet.setDrawHighlightCircleEnabled(true);
        radarDataSet.setDrawValues(false);//不显示值
        radarDataSet.setDrawIcons(false);

        RadarData radarData = new RadarData(radarDataSet);

        mRadarChart.setData(radarData);

    }

    private void initRadarChart() {

        mRadarChart.setNoDataText(getResources().getString(R.string.no_data));
        mRadarChart.setDrawWeb(true);
        mRadarChart.setWebInnerEnable(false);
        mRadarChart.setWebColorInner(Color.WHITE);//各頂點連線
        mRadarChart.setWebColor(Color.WHITE);//頂點到中點的連線
        mRadarChart.setDrawCenterValue(true);//是否在中間畫值
        mRadarChart.setDrawLabelIcon(true);//是否在Label上画Icon
        mRadarChart.setLabelIcons(mLabelIcons);
        mRadarChart.setBackgroundColor(0xFF1196EE);
        mRadarChart.setSkipWebLineCount(0);
        mRadarChart.setRotationEnabled(false);
        mRadarChart.setDescription(null);

        //X轴上画出维度
        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mDemissions[(int) value % mDemissions.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);


        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);
    }
}

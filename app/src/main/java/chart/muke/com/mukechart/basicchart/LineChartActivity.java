package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * @Annotation <p>简单折线图</p>
 * @Auth Sunny
 * @date 2017/9/16
 * @Version V1.0.0
 */

public class LineChartActivity extends MukeBaseActivity {

    private LineChart mLineChart;

    private Button mBtnShadow,mBtnShowCircle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_line_chart;
    }

    @Override
    public void onContentChanged() {
        mLineChart = (LineChart) findViewById(R.id.simple_line_chart);
        mBtnShadow = (Button) findViewById(R.id.btn_linechart_shadow);
        mBtnShowCircle = (Button) findViewById(R.id.btn_linechart_circle);

        setOnViewClickListeners(mBtnShadow,mBtnShowCircle);
    }

    @Override
    protected void initParams() {

        initLineChart();
        setData(10,100);

        mLineChart.invalidate();
    }

    /**
     * 设置值
     * @param count 拐点个数
     * @param range Y轴上值区间
     */
    private void setData(int count,float range) {

        //1.组装假数据
        List<Entry> lineValues = new ArrayList<>();
        for (int i=0;i<count;i++){
            float val = (float) (Math.random()*range + 5);
            Entry entry = new Entry(i,val,null);
            lineValues.add(entry);
        }

        //2.把假数据设进去
        LineDataSet lineDataSet;
        LineData lineData = mLineChart.getData();
        if (lineData != null && lineData.getDataSetCount() > 0){
            //有数据集合，则直接往里面set数据
            lineDataSet = (LineDataSet) lineData.getDataSetByIndex(0);
            lineDataSet.setValues(lineValues);
            lineData.notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        }else{
            //没有数据集合，自己创建对象，再设值
            lineDataSet = new LineDataSet(lineValues,"DataSet1");
            lineDataSet.setDrawFilled(false);//不画阴影
            lineDataSet.setCircleColor(android.R.color.holo_red_light);//设置圆点颜色
            lineDataSet.setCircleRadius(5);
            lineDataSet.setCubicIntensity(1.0f);//设置曲线的亮度，最大为1，最小为0.05，默认为0.2f
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(true);

            lineData = new LineData(lineDataSet);
            mLineChart.setData(lineData);
            mLineChart.notifyDataSetChanged();
        }

    }

    private void initLineChart() {
        /**1.为图表添加描述**/
        Description description = new Description();
        description.setText("简单折线图");//默认在图表右下角
        mLineChart.setDescription(description);
    }

    @Override
    public void onClick(View v) {
        LineDataSet lineDataSet = (LineDataSet) mLineChart.getData().getDataSetByLabel("DataSet1",false);
        switch (v.getId()){
            case R.id.btn_linechart_shadow:
               if (lineDataSet != null){
                   boolean fill = lineDataSet.isDrawFilledEnabled();
                   if (fill){
                       lineDataSet.setDrawFilled(false);
                   }else{
                       lineDataSet.setDrawFilled(true);
                   }

                   lineDataSet.notifyDataSetChanged();
                   mLineChart.notifyDataSetChanged();
                   mLineChart.invalidate();
               }
                break;

            case R.id.btn_linechart_circle:
                if (lineDataSet != null){
                    lineDataSet.setDrawCircles(lineDataSet.isDrawCirclesEnabled()?false:true);
                    lineDataSet.setCircleColorHole(Color.RED);
                    lineDataSet.notifyDataSetChanged();
                    mLineChart.invalidate();
                }
                break;
                default:break;
        }
    }
}

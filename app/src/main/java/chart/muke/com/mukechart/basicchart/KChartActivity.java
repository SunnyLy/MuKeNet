package chart.muke.com.mukechart.basicchart;

import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * @Annotation <p>简单K线图:
 *              首先要知道什么是K线图，它有什么特征：
 *              第一：K线图是由开盘价、收盘价、最高价、最低价组成
 *                   其中开盘价低于收盘价时，称之为阳线（用红色表示），
 *                   反之称之为阴线（用绿色表示）
 *              第二：K线分为日K线，周K线、月K线，分（1分钟/5分钟/15分钟/30分钟/60分钟）
 *              第三：特征
 *                    中间为实心或空心柱体，上下有影线
 *                   </p>
 * @Auth Sunny
 * @date 2017/9/16
 * @Version V1.0.0
 */

public class KChartActivity extends MukeBaseActivity {

    private static final String CANDLE_DATA_SET_LABEL1 = "CandleDataSetLab1";

    private CandleStickChart mKChart;
    @Override
    public int getLayoutId() {
        setTitle(R.string.title_k_bar);
        return R.layout.activity_k_chart;
    }

    @Override
    public void onContentChanged() {
        mKChart = (CandleStickChart) findViewById(R.id.k_chart);
    }

    @Override
    protected void initParams() {

        initKChart();
        setData(40,100);
        mKChart.invalidate();


    }

    private void initKChart() {
        mKChart.setTouchEnabled(true);//可触摸
        mKChart.setDoubleTapToZoomEnabled(true);//双击放大
        mKChart.setNoDataText("暂无数据");
        mKChart.getDescription().setEnabled(false);

        //X轴
        XAxis xAxis = mKChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);

        //Y轴
        YAxis yAxis = mKChart.getAxisLeft();
        yAxis.setDrawZeroLine(true);
        yAxis.setLabelCount(7,false);

        mKChart.getLegend().setEnabled(true);
    }

    private void setData(int count,float range) {

        //CandleEntry继承自Entry
        List<CandleEntry> values = new ArrayList<>();
        for (int i=0;i<count;i++){
            float val = (float) (Math.random()*40 + 10);
            //最高价
            float hightPrice = (float) (Math.random()*9 + 8f);
            //最低价
            float lowPrice = (float) (Math.random()*9) + 8f;
            //开盘价
            float openPrice = (float) (Math.random()*6) + 1f;
            //收盘价
            float closePrice = (float) (Math.random()*6) + 1f;

            //手动让其在奇数位上画阴线
            boolean even = i % 2 == 0;
            CandleEntry entry = new CandleEntry(i,//X轴上的位置
                    val+hightPrice,//最高价
                    val - lowPrice,//最低价
                    even?val+openPrice:val-openPrice,//开盘价
                    even?val-closePrice:val+closePrice,//收盘价
                    null);//是否画图标

            values.add(entry);
        }

        //K线图获取的实例 对象为CandleData
        //CandleData:与LineData一样继承自BarLineScatterCandleBubbleData
        //只是实现的接口不一样
        //CandleData实现接口：ICandleDataSet
        //LineData实现接口:ILineDataSet
        CandleData candleData = mKChart.getCandleData();
        CandleDataSet candleDataSet = null;
        if (candleData != null && candleData.getDataSetCount() > 0){
            candleDataSet = (CandleDataSet) candleData.getDataSetByIndex(0);
            if (candleDataSet != null){
                candleDataSet.setValues(values);
                candleDataSet.notifyDataSetChanged();
                mKChart.notifyDataSetChanged();
            }
        }else{
            candleDataSet = new CandleDataSet(values,CANDLE_DATA_SET_LABEL1);
            candleDataSet.setDrawIcons(false);
//            candleDataSet.setBarSpace(5);

            candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            candleDataSet.setShadowColor(Color.DKGRAY);
            candleDataSet.setShadowWidth(0.7f);

            //阳线
            candleDataSet.setDecreasingColor(Color.RED);
            candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);

            //阴线
            candleDataSet.setIncreasingColor(Color.rgb(122, 242, 84));
            candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE);
            candleDataSet.setNeutralColor(Color.BLUE);

            candleData = new CandleData(candleDataSet);
            mKChart.setData(candleData);
            mKChart.notifyDataSetChanged();
        }

    }
}

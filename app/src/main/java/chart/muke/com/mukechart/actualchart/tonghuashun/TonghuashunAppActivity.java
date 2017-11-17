package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.actualchart.tonghuashun.demo.KLineBean;
import chart.muke.com.mukechart.base.MukeBaseActivity;
import chart.muke.com.mukechart.utils.DataCenter;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/30  15:14
 * @Version v1.0.0
 * @Annotation 仿同花顺 K 线图
 */
public class TonghuashunAppActivity extends MukeBaseActivity {

    private MukeCombindedChart mChartKline;

    private DataCenter mData;
    //K线图数据
    private ArrayList<KLineBean2> kLineDatas;

    //X轴标签的类
    protected XAxis xAxisKline, xAxisVolume, xAxisCharts;

    //Y轴左侧的线
    protected YAxis axisLeftKline, axisLeftVolume, axisLeftCharts;

    //Y轴右侧的线
    protected YAxis axisRightKline, axisRightVolume, axisRightCharts;
    private boolean isRefresh = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tonghuashun;
    }

    @Override
    public void onContentChanged() {
        mChartKline = (MukeCombindedChart) findViewById(R.id.kline_chart_k);
    }

    @Override
    protected void initParams() {
        setTitle(R.string.title_tonghuashun);

        mData = new DataCenter();
        mData.parseKLine();
        initChartKline();
        initChartsParams();
        kLineDatas = mData.getKLineDatas();
        new Thread(){
            @Override
            public void run() {
                super.run();
//                setKLineDatas();
                setKLineByChart(mChartKline);
                mChartKline.invalidate();
            }
        }.start();


    }

    private void setKLineDatas() {
        List<KLineBean2> kLineBeans = mData.getKLineDatas();
        if (kLineBeans != null && kLineBeans.size() > 0){

            kLineDatas.clear();
            kLineDatas.addAll(kLineBeans);
        }
        mData.initLineDatas(mData.getKLineDatas());
    }

    /**
     * 初始化图表的相关配置
     */
    private void initChartsParams() {
        mChartKline.setScaleEnabled(true);//启用图表缩放事件
        mChartKline.setDrawBorders(true);//是否绘制边线
        mChartKline.setBorderWidth(1);//边线宽度，单位dp
        mChartKline.setDragEnabled(true);//启用图表拖拽事件
        mChartKline.setScaleYEnabled(false);//启用Y轴上的缩放
        mChartKline.setBorderColor(getResources().getColor(R.color.border_color));//边线颜色
//        mChartCharts.setDescription("");//右下角对图表的描述信息
        mChartKline.setMinOffset(0f);
        mChartKline.setExtraOffsets(0f, 0f, 0f, 3f);

        Legend lineChartLegend = mChartKline.getLegend();
        lineChartLegend.setEnabled(false);//是否绘制 Legend 图例

//        //bar x y轴
        xAxisCharts = mChartKline.getXAxis();
        xAxisCharts.setEnabled(true);

        //左边Y轴
        axisLeftCharts = mChartKline.getAxisLeft();
        axisLeftCharts.setDrawGridLines(false);
        axisLeftCharts.setDrawAxisLine(true);
        axisLeftCharts.setDrawLabels(true);
        axisLeftCharts.enableGridDashedLine(10f, 10f, 0f);
//        axisLeftCharts.setTextColor(getResources().getColor(R.color.text_color_common));
        axisLeftCharts.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//Y轴刻度显示在坐标轴左侧
//        axisLeftCharts.setLabelCount(1, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布

        //右边Y轴
        axisRightCharts = mChartKline.getAxisRight();
        axisRightCharts.setDrawLabels(false);
        axisRightCharts.setDrawGridLines(false);
        axisRightCharts.setDrawAxisLine(true);

        mChartKline.setDragDecelerationEnabled(true);
        mChartKline.setDragDecelerationFrictionCoef(0.2f);

        mChartKline.animateXY(2000, 2000);
    }

    private void setKLineByChart(MukeCombindedChart combinedChart) {
        Logger.e("CandleData:"+mData.getCandleDatas().size());

        mData.initKLineMA(mData.getKLineDatas());
        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(generateLineDatas());
        combinedData.setData(generateCandleDatas());
        combinedChart.setData(combinedData);
        setHandler(combinedChart);
    }

    /**
     * 生成曲线数据
     * @return
     */
    private LineData generateLineDatas() {

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
        sets.add(setMaLine(5, mData.getXVals(), mData.getMa5DataL()));
        sets.add(setMaLine(10, mData.getXVals(), mData.getMa10DataL()));
        sets.add(setMaLine(20, mData.getXVals(), mData.getMa20DataL()));
        sets.add(setMaLine(30, mData.getXVals(), mData.getMa30DataL()));

        LineData lineData = new LineData(sets);
//        LineData lineData = new LineData();
//
//        //五分钟曲线
//        LineDataSet ma5DS = new LineDataSet(mData.getMa5DataL(),"ma5LineDataSet");
//        ma5DS.setColor(getResources().getColor(R.color.ma5));
//        ma5DS.setLineWidth(1.5f);
//        ma5DS.setDrawCircles(false);
//        ma5DS.setFillColor(Color.GRAY);
//        ma5DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        ma5DS.setDrawValues(true);
//        ma5DS.setValueTextSize(10f);
//        ma5DS.setValueTextColor(Color.rgb(240, 238, 70));
//        ma5DS.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        //十分钟曲线
//        LineDataSet ma10DS = new LineDataSet(mData.getMa10DataL(),"ma10LineDataSet");
//        ma10DS.setColor(getResources().getColor(R.color.ma10));
//        ma10DS.setLineWidth(1.5f);
//        ma10DS.setFillColor(Color.BLUE);
//        ma10DS.setDrawCircles(false);
//        ma10DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        ma10DS.setDrawValues(true);
//        ma10DS.setValueTextSize(10f);
//        ma10DS.setValueTextColor(Color.rgb(240, 238, 70));
//        ma10DS.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        //20分钟曲线
//        LineDataSet ma20DS = new LineDataSet(mData.getMa20DataL(),"ma20LineDataSet");
//        ma20DS.setColor(getResources().getColor(R.color.ma20));
//        ma20DS.setLineWidth(1.5f);
//        ma20DS.setFillColor(Color.BLACK);
//        ma20DS.setDrawCircles(false);
//        ma20DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        ma20DS.setDrawValues(true);
//        ma20DS.setValueTextSize(10f);
//        ma20DS.setValueTextColor(Color.rgb(240, 238, 70));
//        ma20DS.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        //30分钟曲线
//        LineDataSet ma30DS = new LineDataSet(mData.getMa30DataL(),"ma30LineDataSet");
//        ma30DS.setColor(getResources().getColor(R.color.ma30));
//        ma30DS.setLineWidth(1.5f);
//        ma30DS.setFillColor(Color.RED);
//        ma30DS.setDrawCircles(false);
//        ma30DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        ma30DS.setDrawValues(true);
//        ma30DS.setValueTextSize(10f);
//        ma30DS.setValueTextColor(Color.rgb(240, 238, 70));
//        ma30DS.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        lineData.addDataSet(ma5DS);
//        lineData.addDataSet(ma10DS);
//        lineData.addDataSet(ma20DS);
//        lineData.addDataSet(ma30DS);
        return lineData;
    }

    //生成阴线与阳线数据
    private CandleData generateCandleDatas() {
        CandleDataSet set = new CandleDataSet(mData.getCandleEntries(), "");
        set.setDrawHorizontalHighlightIndicator(false);
        set.setHighlightEnabled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(1f);
        set.setValueTextSize(10f);
        set.setDecreasingColor(getResources().getColor(R.color.decreasing_color));//设置开盘价高于收盘价的颜色
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(getResources().getColor(R.color.increasing_color));//设置开盘价低于收盘价的颜色
        set.setIncreasingPaintStyle(Paint.Style.STROKE);
        set.setNeutralColor(Color.WHITE);//设置开盘价等于收盘价的颜色
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(1f);
        set.setHighLightColor(getResources().getColor(R.color.marker_line_bg));
        set.setDrawValues(true);
        set.setValueTextColor(getResources().getColor(R.color.marker_text_bg));
        CandleData candleData = new CandleData();
        candleData.addDataSet(set);
        return candleData;
    }

    private void setHandler(MukeCombindedChart combinedChart) {
        final ViewPortHandler viewPortHandlerBar = combinedChart.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(mData.getXVals().size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);
    }

    private float culcMaxscale(int count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    @NonNull
    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
        if (ma == 5) {
            lineDataSetMa.setHighlightEnabled(true);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(getResources().getColor(R.color.marker_line_bg));
        } else {/*此处必须得写*/
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setDrawValues(false);
        if (ma == 5) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma5));
        } else if (ma == 10) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma10));
        } else if (ma == 20) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma20));
        } else {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma30));
        }
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineDataSetMa.setHighlightEnabled(false);
        return lineDataSetMa;
    }

    private void initChartKline() {
        mChartKline.setScaleEnabled(true);//启用图表缩放事件
        mChartKline.setDrawBorders(true);//是否绘制边线
        mChartKline.setBorderWidth(1);//边线宽度，单位dp
        mChartKline.setDragEnabled(true);//启用图表拖拽事件
        mChartKline.setScaleYEnabled(false);//启用Y轴上的缩放
        mChartKline.setBorderColor(ColorTemplate.JOYFUL_COLORS[0]);//边线颜色
        Description description = new Description();
        description.setText("仿同花顺K线图");
        description.setTextColor(ColorTemplate.COLOR_SKIP);
        mChartKline.setDescription(description);//右下角对图表的描述信息
        mChartKline.setMinOffset(0f);
        mChartKline.setExtraOffsets(0f, 0f, 0f, 3f);

        Legend lineChartLegend = mChartKline.getLegend();
        lineChartLegend.setEnabled(false);//是否绘制 Legend 图例
        lineChartLegend.setForm(Legend.LegendForm.CIRCLE);

        //底部X轴
        xAxisKline = mChartKline.getXAxis();
        xAxisKline.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxisKline.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        xAxisKline.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxisKline.enableGridDashedLine(10f, 10f, 0f);//虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxisKline.setTextColor(ColorTemplate.MATERIAL_COLORS[0]);//设置字的颜色
        xAxisKline.setPosition(XAxis.XAxisPosition.BOTTOM);//设置值显示在什么位置
        xAxisKline.setAvoidFirstLastClipping(true);//设置首尾的值是否自动调整，避免被遮挡

        //左边Y轴
        axisLeftKline = mChartKline.getAxisLeft();
        axisLeftKline.setDrawGridLines(true);
        axisLeftKline.setDrawAxisLine(false);
        axisLeftKline.setDrawZeroLine(false);
        axisLeftKline.setDrawLabels(true);
        axisLeftKline.enableGridDashedLine(10f, 10f, 0f);
        axisLeftKline.setTextColor(ColorTemplate.MATERIAL_COLORS[0]);
//        axisLeftKline.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftKline.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisLeftKline.setSpaceTop(10);//距离顶部留白


        //右边Y轴
        axisRightKline = mChartKline.getAxisRight();
        axisRightKline.setDrawLabels(false);
        axisRightKline.setDrawGridLines(false);
        axisRightKline.setDrawAxisLine(false);
        axisRightKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布

        mChartKline.setDragDecelerationEnabled(true);
        mChartKline.setDragDecelerationFrictionCoef(0.2f);

        mChartKline.animateXY(2000, 2000);
    }

}

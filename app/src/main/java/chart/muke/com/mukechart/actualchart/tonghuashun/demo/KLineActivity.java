package chart.muke.com.mukechart.actualchart.tonghuashun.demo;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.constant.MukeConstant;

/**
 * Created by loro on 2017/3/4.
 */

public class KLineActivity extends AppCompatActivity {

    //开，收，高，低，量，换，额，查，比
    protected TextView mTvOpen, mTvClose, mTvMax, mTvMin, mTvNum, mTvExchange, mTvAmount;
    private TextView mTvSub;//相对开盘价的涨跌价
    private TextView mTvPercent;//相对开盘价的涨跌比
    protected TextView mTvKMa5, mTvKMa10, mTvKMa20, mTvKMa30;

    protected MyCombinedChart mChartKline;

    //X轴标签的类
    protected XAxis xAxisKline, xAxisVolume, xAxisCharts;
    //Y轴左侧的线
    protected YAxis axisLeftKline, axisLeftVolume, axisLeftCharts;
    //Y轴右侧的线
    protected YAxis axisRightKline, axisRightVolume, axisRightCharts;

    //解析数据
    private DataParse mData;
    //K线图数据
    private ArrayList<KLineBean> kLineDatas = new ArrayList<>();
    //MACD数据
    private ArrayList<Float> macdDatas;
    private ArrayList<Float> kdjDatas;

    private DataParse mCacheData;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mChartKline.setAutoScaleMinMaxEnabled(true);
            mChartKline.notifyDataSetChanged();
            mChartKline.invalidate();
        }
    };

    private Handler handlerAdd = new Handler();
    private Runnable runnable;

    protected int chartType = 1;
    protected int chartTypes = 7;

    boolean isRefresh = true;
    boolean isAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kline);
        initViews();

        initChartKline();
        initChartCharts();
        setChartListener();
        initCharData();

        setKLineByChart(mChartKline);
        isRefresh = false;

        mChartKline.moveViewToX(kLineDatas.size() - 1);


/****************************************************************************************
 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug
 ****************************************************************************************/

        handler.sendEmptyMessageDelayed(0, 300);

        runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情，这里再次调用此Runnable对象，以实现每两秒实现一次的定时器操作
                addData();
                addKlineData();

//                switch (chartType) {
//                    case 1:
//                        setMACDByChart(mChartCharts);
//                        break;
//                    case 2:
//                        setKDJByChart(mChartCharts);
//                        break;
//                    case 3:
//                        setWRByChart(mChartCharts);
//                        break;
//                    case 4:
//                        setRSIByChart(mChartCharts);
//                        break;
//                    case 5:
//                        setBOLLByChart(mChartCharts);
//                        break;
//                    case 6:
//                        setEXPMAByChart(mChartCharts);
//                        break;
//                    case 7:
//                        setDMIByChart(mChartCharts);
//                        break;
//                }

                mChartKline.setAutoScaleMinMaxEnabled(true);
                mChartKline.notifyDataSetChanged();
                mChartKline.invalidate();

                handlerAdd.postDelayed(this, 10000);
            }
        };
        handlerAdd.postDelayed(runnable, 10000);
    }

    private void initViews() {
        mTvOpen = (TextView) findViewById(R.id.kline_tv_open);
        mTvClose = (TextView) findViewById(R.id.kline_tv_close);
        mTvMax = (TextView) findViewById(R.id.kline_tv_max);
        mTvMin = (TextView) findViewById(R.id.kline_tv_min);
        mTvNum = (TextView) findViewById(R.id.kline_tv_num);
        mTvExchange = (TextView) findViewById(R.id.kline_tv_exchange);
        mTvAmount = (TextView) findViewById(R.id.kline_tv_amount);
        mTvSub = (TextView) findViewById(R.id.kline_tv_sub);
        mTvPercent = (TextView) findViewById(R.id.kline_tv_percent);

        mChartKline = (MyCombinedChart) findViewById(R.id.kline_chart_k);
        mTvKMa5 = (TextView) findViewById(R.id.view_kline_tv_ma5);
        mTvKMa10 = (TextView) findViewById(R.id.view_kline_tv_ma10);
        mTvKMa20 = (TextView) findViewById(R.id.view_kline_tv_ma20);
        mTvKMa30 = (TextView) findViewById(R.id.view_kline_tv_ma30);

    }

    /**
     * 初始化上面的chart公共属性
     */
    private void initChartKline() {
        mChartKline.setScaleEnabled(true);//启用图表缩放事件
        mChartKline.setDrawBorders(true);//是否绘制边线
        mChartKline.setBorderWidth(1);//边线宽度，单位dp
        mChartKline.setDragEnabled(true);//启用图表拖拽事件
        mChartKline.setScaleYEnabled(false);//启用Y轴上的缩放
        mChartKline.setBorderColor(getResources().getColor(R.color.border_color));//边线颜色
        Description description = new Description();
        description.setText("仿同花顺K线图");
        mChartKline.setDescription(description);//右下角对图表的描述信息
        mChartKline.setMinOffset(0f);
        mChartKline.setExtraOffsets(0f, 0f, 0f, 3f);

        Legend lineChartLegend = mChartKline.getLegend();
        lineChartLegend.setEnabled(false);//是否绘制 Legend 图例
        lineChartLegend.setForm(Legend.LegendForm.CIRCLE);

        //bar x y轴
        xAxisKline = mChartKline.getXAxis();
        xAxisKline.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxisKline.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        xAxisKline.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxisKline.enableGridDashedLine(10f, 10f, 0f);//虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxisKline.setTextColor(getResources().getColor(R.color.text_color_common));//设置字的颜色
        xAxisKline.setPosition(XAxis.XAxisPosition.BOTTOM);//设置值显示在什么位置
        xAxisKline.setAvoidFirstLastClipping(true);//设置首尾的值是否自动调整，避免被遮挡

        axisLeftKline = mChartKline.getAxisLeft();
        axisLeftKline.setDrawGridLines(true);
        axisLeftKline.setDrawAxisLine(false);
        axisLeftKline.setDrawZeroLine(false);
        axisLeftKline.setDrawLabels(true);
        axisLeftKline.enableGridDashedLine(10f, 10f, 0f);
        axisLeftKline.setTextColor(getResources().getColor(R.color.text_color_common));
//        axisLeftKline.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftKline.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisLeftKline.setSpaceTop(10);//距离顶部留白

        axisRightKline = mChartKline.getAxisRight();
        axisRightKline.setDrawLabels(false);
        axisRightKline.setDrawGridLines(false);
        axisRightKline.setDrawAxisLine(false);
        axisRightKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布

        mChartKline.setDragDecelerationEnabled(true);
        mChartKline.setDragDecelerationFrictionCoef(0.2f);

        mChartKline.animateXY(2000, 2000);
    }

    /**
     * 初始化下面的chart公共属性
     */
    private void initChartCharts() {
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

        //bar x y轴
        xAxisCharts = mChartKline.getXAxis();
        xAxisCharts.setEnabled(false);

        axisLeftCharts = mChartKline.getAxisLeft();
        axisLeftCharts.setDrawGridLines(true);
        axisLeftCharts.setDrawAxisLine(false);
        axisLeftCharts.setDrawLabels(true);
        axisLeftCharts.enableGridDashedLine(10f, 10f, 0f);
        axisLeftCharts.setTextColor(getResources().getColor(R.color.text_color_common));
        axisLeftCharts.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftCharts.setLabelCount(1, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布


        axisRightCharts = mChartKline.getAxisRight();
        axisRightCharts.setDrawLabels(false);
        axisRightCharts.setDrawGridLines(false);
        axisRightCharts.setDrawAxisLine(false);

        mChartKline.setDragDecelerationEnabled(true);
        mChartKline.setDragDecelerationFrictionCoef(0.2f);

        mChartKline.animateXY(2000, 2000);
    }

    private void setChartListener() {
        // 将K线控的滑动事件传递给交易量控件
        mChartKline.setOnChartGestureListener(new CoupleChartGestureListener(mChartKline, null));

        mChartKline.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                Highlight highlight = new Highlight(h.getXIndex(), h.getY(), h.getDataIndex(), h.getDataSetIndex());
//
//                float touchY = h.getTouchY() - mChartKline.getHeight();
//                Highlight h1 = mChartVolume.getHighlightByTouchPoint(h.getXIndex(), touchY);
//                highlight.setTouchY(touchY);
//                if (null == h1) {
//                    highlight.setTouchYValue(0);
//                } else {
//                    highlight.setTouchYValue(h1.getTouchYValue());
//                }
//                mChartVolume.highlightValues(new Highlight[]{highlight});
//
                Highlight highlight2 = new Highlight(h.getX(),h.getY(),h.getXPx(),h.getYPx(),h.getDataSetIndex(), YAxis.AxisDependency.LEFT);

                float touchY2 = h.getTouchY() - mChartKline.getHeight();
                Highlight h2 = mChartKline.getHighlightByTouchPoint(h.getXIndex(), touchY2);
                highlight2.setTouchY(touchY2);
                if (null == h2) {
                    highlight2.setTouchYValue(0);
                } else {
                    highlight2.setTouchYValue(h2.getTouchYValue());
                }
                mChartKline.highlightValues(new Highlight[]{highlight2});

                updateText((int) e.getX());
            }

            @Override
            public void onNothingSelected() {
            }
        });

    }

    /**
     * 初始化公共数据
     */
    private void initCharData() {
        getOffLineData();
        setKLineDatas();
        setMarkerViewButtom(mData, mChartKline);
    }


    /**
     * 加载测试数据
     */
    private void getOffLineData() {
        /*方便测试，加入假数据*/
        mData = new DataParse();
        JSONObject object = null;
        try {
            object = new JSONObject(MukeConstant.KLINEURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mData.parseKLine(object);

        mCacheData = new DataParse();
        mCacheData.parseKLine(object);
    }

    private void setKLineDatas() {
        List<KLineBean> kLineBeans = mData.getKLineDatas();
        if (kLineBeans != null && kLineBeans.size() > 0){

            kLineDatas.clear();
            kLineDatas.addAll(kLineBeans);
        }
        mData.initLineDatas(mData.getKLineDatas());
    }

    private void setMarkerViewButtom(DataParse mData, MyCombinedChart combinedChart) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(KLineActivity.this, R.layout.mymarkerview);
        MyHMarkerView hMarkerView = new MyHMarkerView(KLineActivity.this, R.layout.mymarkerview_line);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(KLineActivity.this, R.layout.mymarkerview);
        combinedChart.setMarker(leftMarkerView, bottomMarkerView, hMarkerView, mData);
    }

    private void setMarkerView(DataParse mData, MyCombinedChart combinedChart) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(KLineActivity.this, R.layout.mymarkerview);
        MyHMarkerView hMarkerView = new MyHMarkerView(KLineActivity.this, R.layout.mymarkerview_line);
        combinedChart.setMarker(leftMarkerView, hMarkerView, mData);
    }

    private void setKLineByChart(MyCombinedChart combinedChart) {

        mData.initKLineMA(mCacheData.getKLineDatas());

        //组合数据
        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(getnerateLineData());
        combinedData.setData(generateCandleData());

        combinedChart.setData(combinedData);
        setHandler(combinedChart);
    }

    /**
     * 生成阴线阳线数据
     * @return
     */
    private CandleData generateCandleData() {
        CandleDataSet set = new CandleDataSet(mData.getCandleEntries(), "");
        set.setDrawHorizontalHighlightIndicator(false);
        set.setHighlightEnabled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(1f);
        set.setValueTextSize(10f);
        set.setDecreasingColor(getResources().getColor(R.color.decreasing_color));//设置开盘价高于收盘价的颜色
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(getResources().getColor(R.color.increasing_color));//设置开盘价地狱收盘价的颜色
        set.setIncreasingPaintStyle(Paint.Style.STROKE);
        set.setNeutralColor(getResources().getColor(R.color.decreasing_color));//设置开盘价等于收盘价的颜色
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(1f);
        set.setHighLightColor(getResources().getColor(R.color.marker_line_bg));
        set.setDrawValues(true);
        set.setValueTextColor(getResources().getColor(R.color.marker_text_bg));

        //蜡烛数据
        CandleData candleData = new CandleData();
        candleData.addDataSet(set);
        return candleData;
    }

    /**
     * 生成曲线数据
     * @return
     */
    private LineData getnerateLineData() {
        LineData lineData = new LineData();

        //五分钟曲线
        LineDataSet ma5DS = new LineDataSet(mData.getMa5DataL(),"ma5LineDataSet");
        ma5DS.setColor(getResources().getColor(R.color.ma5));
        ma5DS.setLineWidth(1.5f);
        ma5DS.setDrawCircles(false);
        ma5DS.setFillColor(Color.GRAY);
        ma5DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ma5DS.setDrawValues(true);
        ma5DS.setValueTextSize(10f);
        ma5DS.setValueTextColor(Color.rgb(240, 238, 70));
        ma5DS.setAxisDependency(YAxis.AxisDependency.LEFT);

        //十分钟曲线
        LineDataSet ma10DS = new LineDataSet(mData.getMa10DataL(),"ma10LineDataSet");
        ma10DS.setColor(getResources().getColor(R.color.ma10));
        ma10DS.setLineWidth(1.5f);
        ma10DS.setFillColor(Color.BLUE);
        ma10DS.setDrawCircles(false);
        ma10DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ma10DS.setDrawValues(true);
        ma10DS.setValueTextSize(10f);
        ma10DS.setValueTextColor(Color.rgb(240, 238, 70));
        ma10DS.setAxisDependency(YAxis.AxisDependency.LEFT);

        //20分钟曲线
        LineDataSet ma20DS = new LineDataSet(mData.getMa20DataL(),"ma20LineDataSet");
        ma20DS.setColor(getResources().getColor(R.color.ma20));
        ma20DS.setLineWidth(1.5f);
        ma20DS.setFillColor(Color.BLACK);
        ma20DS.setDrawCircles(false);
        ma20DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ma20DS.setDrawValues(true);
        ma20DS.setValueTextSize(10f);
        ma20DS.setValueTextColor(Color.rgb(240, 238, 70));
        ma20DS.setAxisDependency(YAxis.AxisDependency.LEFT);

        //30分钟曲线
        LineDataSet ma30DS = new LineDataSet(mData.getMa30DataL(),"ma30LineDataSet");
        ma30DS.setColor(getResources().getColor(R.color.ma30));
        ma30DS.setLineWidth(1.5f);
        ma30DS.setFillColor(Color.RED);
        ma30DS.setDrawCircles(false);
        ma30DS.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ma30DS.setDrawValues(true);
        ma30DS.setValueTextSize(10f);
        ma30DS.setValueTextColor(Color.rgb(240, 238, 70));
        ma30DS.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineData.addDataSet(ma5DS);
        lineData.addDataSet(ma10DS);
        lineData.addDataSet(ma20DS);
        lineData.addDataSet(ma30DS);
        return lineData;
    }

    private void setVolumeByChart(MyCombinedChart combinedChart) {
        String unit = MyUtils.getVolUnit(mData.getVolmax());
        String wan = getString(R.string.wan_unit);
        String yi = getString(R.string.yi_unit);
        int u = 1;
        if (wan.equals(unit)) {
            u = 4;
        } else if (yi.equals(unit)) {
            u = 8;
        }
        combinedChart.getAxisLeft().setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
//        combinedChart.getAxisLeft().setAxisMaxValue(mData.getVolmax());
        Logger.e("@@@"+mData.getVolmax() + "da");

        BarDataSet set = new BarDataSet(mData.getBarEntries(), "成交量");
//        set.setBarSpacePercent(20); //bar空隙
        set.setHighlightEnabled(true);
        set.setHighLightAlpha(255);
        set.setHighLightColor(getResources().getColor(R.color.marker_line_bg));
        set.setDrawValues(false);

        List<Integer> list = new ArrayList<>();
        list.add(getResources().getColor(R.color.increasing_color));
        list.add(getResources().getColor(R.color.decreasing_color));
        set.setColors(list);
        BarData barData = new BarData(set);

        mData.initVlumeMA(kLineDatas);
        ArrayList<ILineDataSet> sets = new ArrayList<>();

        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
        sets.add(setMaLine(5, mData.getXVals(), mData.getMa5DataV()));
        sets.add(setMaLine(10, mData.getXVals(), mData.getMa10DataV()));
        sets.add(setMaLine(20, mData.getXVals(), mData.getMa20DataV()));
        sets.add(setMaLine(30, mData.getXVals(), mData.getMa30DataV()));

        LineData lineData = new LineData(sets);

        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(barData);
        combinedData.setData(lineData);
        combinedChart.setData(combinedData);

        setHandler(combinedChart);
    }

    private void setMACDByChart(MyCombinedChart combinedChart) {
        mData.initMACD(kLineDatas);

        BarDataSet set = new BarDataSet(mData.getMacdData(), "BarDataSet");
//        set.setBarSpacePercent(20); //bar空隙
        set.setHighlightEnabled(true);
        set.setHighLightAlpha(255);
        set.setHighLightColor(getResources().getColor(R.color.marker_line_bg));
        set.setDrawValues(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        List<Integer> list = new ArrayList<>();
        list.add(getResources().getColor(R.color.increasing_color));
        list.add(getResources().getColor(R.color.decreasing_color));
        set.setColors(list);

        BarData barData = new BarData(set);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(setMACDMaLine(0, mData.getXVals(), (ArrayList<Entry>) mData.getDeaData()));
        sets.add(setMACDMaLine(1, mData.getXVals(), (ArrayList<Entry>) mData.getDifData()));
        LineData lineData = new LineData(sets);

        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(barData);
        combinedData.setData(lineData);
        combinedChart.setData(combinedData);

        if (isRefresh)
            setHandler(combinedChart);
    }

    private void setHandler(MyCombinedChart combinedChart) {
        final ViewPortHandler viewPortHandlerBar = combinedChart.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(mData.getXVals().size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);
    }


    private void addKlineData() {
        CandleData combinedData = mChartKline.getCandleData();
        LineData lineData = mChartKline.getLineData();

        int count = 0;
        int i = kLineDatas.size() - 1;
        if (i < 0 || kLineDatas.size() == 0){
            return;
        }
        String xVals = mData.getXVals().get(mData.getXVals().size() - 1);
        if (combinedData != null) {
            int indexLast = getLastDataSetIndex(combinedData);
            CandleDataSet lastSet = (CandleDataSet) combinedData.getDataSetByIndex(indexLast);

            if (lastSet == null) {
                lastSet = createCandleDataSet();
                combinedData.addDataSet(lastSet);
            }
            count = lastSet.getEntryCount();

//            combinedData.addXValue(xVals);
            // 位最后一个DataSet添加entry
            combinedData.addEntry(new CandleEntry(count, kLineDatas.get(i).high, kLineDatas.get(i).low, kLineDatas.get(i).open, kLineDatas.get(i).close), indexLast);
        }

        if (lineData != null) {
            int index = getDataSetIndexCount(lineData);
            LineDataSet lineDataSet5 = (LineDataSet) lineData.getDataSetByIndex(0);//五日均线;
            LineDataSet lineDataSet10 = (LineDataSet) lineData.getDataSetByIndex(1);//十日均线;
            LineDataSet lineDataSet20 = (LineDataSet) lineData.getDataSetByIndex(2);//二十日均线;
            LineDataSet lineDataSet30 = (LineDataSet) lineData.getDataSetByIndex(3);//三十日均线;

            if (lineDataSet5 != null) {
                mData.getMa5DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 5), count));
                lineData.addEntry(mData.getMa5DataL().get(mData.getMa5DataL().size() - 1), 0);
            }

            if (lineDataSet10 != null) {
                mData.getMa10DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 10), count));
                lineData.addEntry(mData.getMa10DataL().get(mData.getMa10DataL().size() - 1), 1);
            }

            if (lineDataSet20 != null) {
                mData.getMa20DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 20), count));
                lineData.addEntry(mData.getMa20DataL().get(mData.getMa20DataL().size() - 1), 2);
            }

            if (lineDataSet30 != null) {
                mData.getMa30DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 30), count));
                lineData.addEntry(mData.getMa30DataL().get(mData.getMa30DataL().size() - 1), 3);
            }
        }
    }

    int i = 0;

    private void addData() {
        int i = getRandom(mCacheData.getKLineDatas().size() - 1, 0);
        if (i >0 && i<= kLineDatas.size()){
            kLineDatas.add(kLineDatas.get(i));
            mData.getXVals().add(kLineDatas.get(i).date);
        }


    }

    private int getRandom(int max, int min) {
        int index = i;
        i++;
        if (index > max) {
            i = 0;
            index = i;
        }
        return index;
    }

    private float getSum(Integer a, Integer b, ArrayList<KLineBean> datas) {
        float sum = 0;
        for (int i = a; i <= b; i++) {
            sum += datas.get(i).close;
        }
        return sum;
    }

    private float getJSum(Integer a, Integer b, ArrayList<KLineBean> datas) {
        float sum = 0;
        for (int i = a; i <= b; i++) {
            sum += datas.get(i).vol;
        }
        return sum;
    }

    /**
     * 获取最后一个CandleDataSet的索引
     */
    private int getLastDataSetIndex(CandleData candleData) {
        int dataSetCount = candleData.getDataSetCount();
        return dataSetCount > 0 ? (dataSetCount - 1) : 0;
    }

    /**
     * 获取最后一个LineDataSet的索引
     */
    private int getDataSetIndexCount(LineData lineData) {
        int dataSetCount = lineData.getDataSetCount();
        return dataSetCount;
    }

    /**
     * 获取最后一个CandleDataSet的索引
     */
    private int getLastDataSetIndex(BarData barData) {
        int dataSetCount = barData.getDataSetCount();
        return dataSetCount > 0 ? (dataSetCount - 1) : 0;
    }

    private CandleDataSet createCandleDataSet() {
        CandleDataSet dataSet = new CandleDataSet(null, "DataSet 1");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setValueTextSize(12f);

        return dataSet;
    }

    private float culcMaxscale(float count) {
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

    @NonNull
    private LineDataSet setMACDMaLine(int type, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + type);
        lineDataSetMa.setHighlightEnabled(false);
        lineDataSetMa.setDrawValues(false);

        //DEA
        if (type == 0) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma5));
        } else {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma10));
        }

        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);

        return lineDataSetMa;
    }

    @NonNull
    private LineDataSet setKDJMaLine(int type, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + type);
        lineDataSetMa.setHighlightEnabled(false);
        lineDataSetMa.setDrawValues(false);

        //DEA
        if (type == 0) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma5));
        } else if (type == 1) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma10));
        } else if (type == 2) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma20));
        } else {
            lineDataSetMa.setColor(getResources().getColor(R.color.ma30));
        }

        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);

        return lineDataSetMa;
    }

    private void updateText(int index) {
        if (index >= 0 && index < kLineDatas.size()) {
            KLineBean klData = kLineDatas.get(index);
            mTvOpen.setText(MyUtils.getDecimalFormatVol(klData.open));
            mTvClose.setText(MyUtils.getDecimalFormatVol(klData.close));
            mTvMax.setText(MyUtils.getDecimalFormatVol(klData.high));
            mTvMin.setText(MyUtils.getDecimalFormatVol(klData.low));
//            mTvAmount.setText(MyUtils.getDecimalFormatVol(klData.vol));

            float result = klData.close - klData.open;
            mTvSub.setText(MyUtils.getDecimalFormatVol(result));

            float percent = Math.abs(result) / klData.close;
            mTvPercent.setText(MyUtils.getDecimalFormatVol(percent));
            if (result < 0){
                //跌了，用绿色显示
                mTvSub.setTextColor(getResources().getColor(R.color.decreasing_color));
                mTvClose.setTextColor(getResources().getColor(R.color.decreasing_color));
                mTvPercent.setTextColor(getResources().getColor(R.color.decreasing_color));
            }else if (result == 0){
                //白色
                mTvSub.setTextColor(Color.WHITE);
                mTvClose.setTextColor(Color.WHITE);
                mTvPercent.setTextColor(Color.WHITE);
            }else{
                //涨了，用红色
                mTvSub.setTextColor(getResources().getColor(R.color.increasing_color));
                mTvClose.setTextColor(getResources().getColor(R.color.increasing_color));
                mTvPercent.setTextColor(getResources().getColor(R.color.increasing_color));
            }

            int unit = MyUtils.getVolUnitNum(klData.vol);
            mTvNum.setText(MyUtils.getVolUnitText((int) Math.pow(10, unit), klData.vol));
        }

        int newIndex = index;
        if (null != mData.getMa5DataL() && mData.getMa5DataL().size() > 0) {
            if (newIndex >= 0 && newIndex < mData.getMa5DataL().size())
                mTvKMa5.setText(MyUtils.getDecimalFormatVol(mData.getMa5DataL().get(newIndex).getY()));
        }
        if (null != mData.getMa10DataL() && mData.getMa10DataL().size() > 0) {
            if (newIndex >= 0 && newIndex < mData.getMa10DataL().size())
                mTvKMa10.setText(MyUtils.getDecimalFormatVol(mData.getMa10DataL().get(newIndex).getY()));
        }
        if (null != mData.getMa20DataL() && mData.getMa20DataL().size() > 0) {
            if (newIndex >= 0 && newIndex < mData.getMa20DataL().size())
                mTvKMa20.setText(MyUtils.getDecimalFormatVol(mData.getMa20DataL().get(newIndex).getY()));
        }
        if (null != mData.getMa30DataL() && mData.getMa30DataL().size() > 0) {
            if (newIndex >= 0 && newIndex < mData.getMa30DataL().size())
                mTvKMa30.setText(MyUtils.getDecimalFormatVol(mData.getMa30DataL().get(newIndex).getY()));
        }
    }
}

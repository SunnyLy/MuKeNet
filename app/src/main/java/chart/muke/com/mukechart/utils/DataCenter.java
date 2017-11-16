package chart.muke.com.mukechart.utils;

import android.util.SparseArray;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.actualchart.tonghuashun.KLineBean;
import chart.muke.com.mukechart.actualchart.tonghuashun.KMAEntity;
import chart.muke.com.mukechart.constant.MukeConstant;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/11/1  15:09
 * @Version v1.0.0
 * @Annotation 数据处理中心
 */
public class DataCenter {

    private float volmax;
    private SparseArray<String> xValuesLabel = new SparseArray<>();

    private ArrayList<KLineBean> kDatas = new ArrayList<>();
    private ArrayList<CandleEntry> candleEntries = new ArrayList<>();//K线数据
    private ArrayList<String> xVals = new ArrayList<>();//X轴数据
    private ArrayList<BarEntry> barEntries = new ArrayList<>();//成交量数据

    private ArrayList<Entry> ma5DataL = new ArrayList<>();
    private ArrayList<Entry> ma10DataL = new ArrayList<>();
    private ArrayList<Entry> ma20DataL = new ArrayList<>();
    private ArrayList<Entry> ma30DataL = new ArrayList<>();

    public ArrayList<String> getXVals() {
        return xVals;
    }

    public  List<KLineBean> getCandleDatas(){

        List<KLineBean> candleBeans = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(MukeConstant.KLINEURL);

            JSONObject data = jsonObject.optJSONObject("data").optJSONObject("sz002081");
            JSONArray list = data.optJSONArray("day");
            if (list != null) {
                int count = list.length();
                xValuesLabel.clear();
                for (int i = 0; i < count; i++) {
                    JSONArray dayData = list.optJSONArray(i);
                    KLineBean candleBean = new KLineBean();
                    candleBean.mDate = dayData.optString(0);
                    candleBean.mOpenPrice = (float) dayData.optDouble(1);
                    candleBean.mClosePrice = (float) dayData.optDouble(2);
                    candleBean.mShadowHigh = (float) dayData.optDouble(3);
                    candleBean.mShadowLow = (float) dayData.optDouble(4);
                    candleBean.mValue = (float) dayData.optDouble(5);

                    candleBeans.add(candleBean);

                    volmax = Math.max(candleBean.mValue, volmax);
                    xValuesLabel.put(i, candleBean.mDate);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return candleBeans;
    }

    //得到成交量
    public void initLineDatas(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        xVals.clear();//X轴数据
        barEntries.clear();//成交量数据
        candleEntries.clear();//K线数据
        for (int i = 0, j = 0; i < datas.size(); i++, j++) {
            xVals.add(datas.get(i).mDate + "");
            barEntries.add(new BarEntry(i,new float[]{datas.get(i).mShadowHigh,datas.get(i).mShadowLow,
                    datas.get(i).mOpenPrice, datas.get(i).mClosePrice, datas.get(i).mValue}));
            candleEntries.add(new CandleEntry(i, datas.get(i).mShadowHigh, datas.get(i).mShadowLow,
                    datas.get(i).mOpenPrice, datas.get(i).mClosePrice));
        }
    }

    /**
     * 初始化K线图均线
     *
     * @param datas
     */
    public void initKLineMA(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        ma5DataL  .clear();
        ma10DataL .clear();
        ma20DataL .clear();
        ma30DataL .clear();

        KMAEntity kmaEntity5 = new KMAEntity(datas, 5);
        KMAEntity kmaEntity10 = new KMAEntity(datas, 10);
        KMAEntity kmaEntity20 = new KMAEntity(datas, 20);
        KMAEntity kmaEntity30 = new KMAEntity(datas, 30);
        for (int i = 0; i < kmaEntity5.getMAs().size(); i++) {
            ma5DataL.add(new Entry(kmaEntity5.getMAs().get(i), i));
            ma10DataL.add(new Entry(kmaEntity10.getMAs().get(i), i));
            ma20DataL.add(new Entry(kmaEntity20.getMAs().get(i), i));
            ma30DataL.add(new Entry(kmaEntity30.getMAs().get(i), i));
        }

    }


    public ArrayList<Entry> getMa5DataL() {
        return ma5DataL;
    }

    public ArrayList<Entry> getMa10DataL() {
        return ma10DataL;
    }

    public ArrayList<Entry> getMa20DataL() {
        return ma20DataL;
    }

    public ArrayList<Entry> getMa30DataL() {
        return ma30DataL;
    }

    /**
     * 得到K线图数据
     *
     * @return
     */
    public ArrayList<KLineBean> getKLineDatas() {
//        kDatas.clear();
//        List<KLineBean> data = getCandleDatas();
//        if (data != null && data.size() > 0)
//            kDatas.addAll(data);
        return kDatas;
    }
    /**
     * 得到K线数据
     *
     * @return
     */
    public ArrayList<CandleEntry> getCandleEntries() {
        return candleEntries;
    }

    /**
     * 将jsonobject转换为K线数据
     *
     */
    public void parseKLine() {
        JSONObject obj = null;
        try {
            obj = new JSONObject(MukeConstant.KLINEURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<KLineBean> kLineBeans = new ArrayList<>();
        JSONObject data = obj.optJSONObject("data").optJSONObject("sz002081");
        JSONArray list = data.optJSONArray("day");
        if (list != null) {
            int count = list.length();
            for (int i = 0; i < count; i++) {
                JSONArray dayData = list.optJSONArray(i);
                KLineBean kLineData = new KLineBean();
                kLineData.mDate = dayData.optString(0);
                kLineData.mOpenPrice = (float) dayData.optDouble(1);
                kLineData.mClosePrice = (float) dayData.optDouble(2);
                kLineData.mShadowHigh = (float) dayData.optDouble(3);
                kLineData.mShadowLow = (float) dayData.optDouble(4);
                kLineData.mValue = (float) dayData.optDouble(5);

                kLineBeans.add(kLineData);

                volmax = Math.max(kLineData.mValue, volmax);
                xValuesLabel.put(i, kLineData.mDate);
            }
        }
        kDatas.addAll(kLineBeans);
    }

}

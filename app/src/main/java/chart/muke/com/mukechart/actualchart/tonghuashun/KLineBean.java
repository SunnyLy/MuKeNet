package chart.muke.com.mukechart.actualchart.tonghuashun;

import java.io.Serializable;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  17:35
 * @Version v1.0.0
 * @Annotation 蜡烛图实体Bean
 */
public class KLineBean implements Serializable{

    //当前日期
    public String mDate;
    //开盘价
    public float mOpenPrice;
    //收盘价
    public float mClosePrice;
    //阴线最高价
    public float mShadowHigh;
    //阴线最低价
    public float mShadowLow;
    //对应的价格
    public float mValue;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public float getOpenPrice() {
        return mOpenPrice;
    }

    public void setOpenPrice(float mOpenPrice) {
        this.mOpenPrice = mOpenPrice;
    }

    public float getClosePrice() {
        return mClosePrice;
    }

    public void setClosePrice(float mClosePrice) {
        this.mClosePrice = mClosePrice;
    }

    public float getShadowHigh() {
        return mShadowHigh;
    }

    public void setShadowHigh(float mShadowHigh) {
        this.mShadowHigh = mShadowHigh;
    }

    public float getShadowLow() {
        return mShadowLow;
    }

    public void setShadowLow(float mShadowLow) {
        this.mShadowLow = mShadowLow;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }

    @Override
    public String toString() {
        return "KLineBean{" +
                "mDate='" + mDate + '\'' +
                ", mOpenPrice=" + mOpenPrice +
                ", mClosePrice=" + mClosePrice +
                ", mShadowHigh=" + mShadowHigh +
                ", mShadowLow=" + mShadowLow +
                ", mValue=" + mValue +
                '}';
    }
}

package chart.muke.com.mukechart.actualchart.moji;

import android.widget.HorizontalScrollView;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/10  17:49
 * @Version v1.0.0
 * @Annotation 仿墨迹天气App
 *            实现24小时天气预报曲线
 */
public class MojiAppActivity extends MukeBaseActivity implements MukeHorizontalScrollView.OnScrollChangedListener {

    private MukeHorizontalScrollView mHSV;

    private WeatherView mWeatherView;

    @Override
    public int getLayoutId() {
        setTitle(R.string.titile_moji);
        return R.layout.activity_moji;
    }

    @Override
    public void onContentChanged() {
        mHSV = (MukeHorizontalScrollView) findViewById(R.id.moji_hsv);
        mHSV.setScrollListener(this);

        mWeatherView = (WeatherView) findViewById(R.id.weather_view);
    }

    @Override
    protected void initParams() {
    }

    @Override
    public void onScrollChanged(HorizontalScrollView scrollView, int offset, int maxOffest) {
        mWeatherView.setScrollDistance(scrollView.getScrollX(),offset,maxOffest);
    }

//    @Override
//    public void onScrollChanged(HorizontalScrollView scrollView, int x, int y, int oldx, int oldy,int offset,int maxOffset) {
//
//        mWeatherView.setScrollDistance(scrollView.getScrollX(),offset,maxOffset);
//
//    }
}

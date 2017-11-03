package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.content.Context;

import com.github.mikephil.charting.components.MarkerView;

import chart.muke.com.mukechart.R;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  15:34
 * @Version v1.0.0
 * @Annotation 利用MPAC的 MarkerView来统一展示
 *            当用户选择某点时的数据
 */
public class RightMarkerView extends MarkerView {
    public RightMarkerView(Context context) {
        super(context, R.layout.view_left_marker);
    }
}

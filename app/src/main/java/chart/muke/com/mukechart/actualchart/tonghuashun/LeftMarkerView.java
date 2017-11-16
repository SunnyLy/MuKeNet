package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.text.DecimalFormat;

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
public class LeftMarkerView extends AbsMukeMarkerView {

    public LeftMarkerView(Context context) {
        super(context, R.layout.view_left_marker);
    }

    @Override
    protected View getRenderView() {
        return findViewById(R.id.tv_marker);
    }

    @Override
    protected DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#0.00");
    }


    public void setData(float yValForHighlight) {

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

    }
}

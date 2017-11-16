package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;

import java.text.DecimalFormat;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  16:48
 * @Version v1.0.0
 * @Annotation
 */
public abstract class AbsMukeMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public AbsMukeMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        getDecimalFormat();
    }

    /**
     * 渲染markerView
     * @param value
     */
    public void renderView(float value){

        View view = getRenderView();
        if (view != null && view instanceof TextView){
            if (getDecimalFormat() != null){
                ((TextView) view).setText(getDecimalFormat().format(value));
            }
        }
    }

    /**
     * 获取渲染View
     * @return
     */
    protected abstract View getRenderView();

    /**
     * 获取数字格式化
     */
    protected abstract DecimalFormat getDecimalFormat();
}

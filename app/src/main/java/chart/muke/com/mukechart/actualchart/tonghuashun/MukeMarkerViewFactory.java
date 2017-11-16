package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.components.MarkerView;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  15:37
 * @Version v1.0.0
 * @Annotation MarkerView工厂类，
 * 用来统一管理用户点击图表时左右上下的标记view
 */
public class MukeMarkerViewFactory {

    public static final String TAG = "MukeMarkerViewFactory";

    private static MukeMarkerViewFactory mMarkerViewFac;

    public MukeMarkerViewFactory() {
    }

    public static MukeMarkerViewFactory getInstance() {

        if (mMarkerViewFac == null) {
            synchronized (MukeMarkerViewFactory.class) {
                if (mMarkerViewFac == null) {
                    mMarkerViewFac = new MukeMarkerViewFactory();
                }
            }
        }

        return mMarkerViewFac;
    }


    public MarkerView create(@NonNull Context context,int markerViewType) {
        MarkerView markerView = null;
        switch (markerViewType) {
            case MarkerViewType.LEFT:
                markerView = new LeftMarkerView(context);
                break;
            case MarkerViewType.RIGHT:
                markerView = new RightMarkerView(context);
                break;
            case MarkerViewType.TOP:
                markerView = new TopMarkerView(context);
                break;
            case MarkerViewType.BOTTOM:
                markerView = new BottomMarkerView(context);
                break;
            default:
                break;
        }

        return markerView;
    }
}

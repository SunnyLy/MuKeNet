package chart.muke.com.mukechart.actualchart.tonghuashun;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  15:49
 * @Version v1.0.0
 * @Annotation 用于规定MarkerView的类别
 *            主要是用静态属性来替代enum
 *            因为android官网上说enum枚举比静态类要
 *            至少多占2倍的内存。但我没有进行验证……
 */
public class MarkerViewType {

    //LeftMarkerView
    public static final int LEFT = 0x001;

    //BottomMarkerView
    public static final int BOTTOM = 0x002;

    //TopMarkerView
    public static final int TOP = 0x003;

    //RightMarkerView
    public static final int RIGHT = 0x004;
}

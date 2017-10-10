package chart.muke.com.mukechart.actualchart;

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
public class MojiAppActivity extends MukeBaseActivity {
    @Override
    public int getLayoutId() {
        setTitle(R.string.titile_moji);
        return R.layout.activity_moji;
    }

    @Override
    protected void initParams() {

    }
}

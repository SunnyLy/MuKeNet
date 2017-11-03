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
 * @Date 2017/10/30  15:14
 * @Version v1.0.0
 * @Annotation 仿支付宝 芝麻信用分
 */
public class AlipayAppActivity extends MukeBaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_alipay;
    }

    @Override
    protected void initParams() {
        setTitle(R.string.title_alipay);

    }
}

package chart.muke.com.mukechart.basicgraph;

import com.github.mikephil.charting.utils.ColorTemplate;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

import static com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS;
import static com.github.mikephil.charting.utils.ColorTemplate.LIBERTY_COLORS;

/**
 * @Annotation <p>简单直方图的绘制</p>
 * @Auth Sunny
 * @date 2017/9/2
 * @Version V1.0.0
 */

public class SimpleColumnActivity extends MukeBaseActivity {

    private MukeColumnView mColumnView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_column;
    }

    @Override
    public void onContentChanged() {
        mColumnView = (MukeColumnView) findViewById(R.id.column_view);
    }

    @Override
    protected void initParams() {

        setTitle(R.string.title_simple_column);

        mColumnView.setXAxisValue(10, 9);
        mColumnView.setYAxisValue(10,7);

        int columnInfo[][] = new int[][]{
                {6, ColorTemplate.MATERIAL_COLORS[0]},
                {5, LIBERTY_COLORS[1]},
                {4, LIBERTY_COLORS[3]},
                {3, LIBERTY_COLORS[4]},
                {5, JOYFUL_COLORS[0]},
                {3, LIBERTY_COLORS[2]},
                {2, LIBERTY_COLORS[0]}};
        mColumnView.setAxisTextSize(20);
        mColumnView.setColumnInfo(columnInfo);
        mColumnView.setTouchEnable(true);
        mColumnView.invalidate();
    }
}

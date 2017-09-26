package chart.muke.com.mukechart.basicgraph;

import android.graphics.Color;

import com.github.mikephil.charting.utils.ColorTemplate;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

import static com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS;
import static com.github.mikephil.charting.utils.ColorTemplate.LIBERTY_COLORS;

/**
 * @Annotation <p>基本几何图形的绘制</p>
 * @Auth Sunny
 * @date 2017/9/2
 * @Version V1.0.0
 */

public class SimpleGraphActivity extends MukeBaseActivity {

    private ColumnView mColumnView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_graph;
    }

    @Override
    public void onContentChanged() {
        mColumnView = (ColumnView) findViewById(R.id.column_view);
    }

    @Override
    protected void initParams() {

        setTitle(R.string.title_simple_graph);

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
        mColumnView.invalidate();
    }
}

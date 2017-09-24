package chart.muke.com.mukechart.basicgraph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

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
                {6, Color.BLUE},
                {5, Color.GREEN},
                {4, Color.RED},
                {3, Color.BLUE},
                {5, Color.YELLOW},
                {3, Color.LTGRAY},
                {2, Color.BLUE}};
        mColumnView.setColumnInfo(columnInfo);
    }
}

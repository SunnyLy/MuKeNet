package chart.muke.com.mukechart.basicgraph;

import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.base.MukeBaseActivity;

import static com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS;
import static com.github.mikephil.charting.utils.ColorTemplate.LIBERTY_COLORS;

/**
 * @Annotation <p>简单曲线图的绘制</p>
 * @Auth Sunny
 * @date 2017/9/2
 * @Version V1.0.0
 */

public class SimpleCurveActivity extends MukeBaseActivity {

    private MukeCurveView mCurveView;
    private List<Integer> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_curve;
    }

    @Override
    public void onContentChanged() {
        mCurveView = (MukeCurveView) findViewById(R.id.curve_view);
    }

    @Override
    protected void initParams() {

        setTitle(R.string.title_simple_curve);

        dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            dataList.add(random.nextInt(10) * 100);
            Log.e(",,,,,", "value:" + (random.nextInt(10) * 100));
        }

        mCurveView.setXAxisValue(12, 12);
        mCurveView.setYAxisValue((float) getMax(dataList.toArray(new Integer[dataList.size()])),6);
        mCurveView.setDataList(dataList);
        mCurveView.setFill(true);
        mCurveView.invalidate();
    }

    public void  showHideShader(View view){
        ToggleButton button = (ToggleButton) view;
        boolean checked = button.isChecked();
        if (checked){
            mCurveView.setFill(false);
        }else{
            mCurveView.setFill(true);
        }
        mCurveView.invalidate();
    }

    /**
     * * 取出数组中的最大值
     *  * @param arr
     *  * @return
     *
     */
    public static int getMax(Integer[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

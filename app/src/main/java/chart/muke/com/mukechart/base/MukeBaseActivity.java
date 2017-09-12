package chart.muke.com.mukechart.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import chart.muke.com.mukechart.basicchart.BarChartActivity;

/**
 * @Annotation <p>Base基類</p>
 * @Auth Sunny
 * @date 2017/9/4
 * @Version V1.0.0
 */

public abstract class MukeBaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initParams();
    }

    /**
     * 初始化一些參數
     */
    protected abstract void initParams();

    /**
     * 獲取佈局文件
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 为View控件设置点击监听
     * @param views
     */
    public void setOnViewClickListeners(View... views){
        if (views != null && views.length > 0){
            for (View view:views){
                view.setOnClickListener(this);
            }
        }
    }

    /**
     * 跳轉至目標界面
     * @param cls
     */
    public void jumpTarget(@NonNull Class<?> cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}

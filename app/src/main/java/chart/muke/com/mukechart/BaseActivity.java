package chart.muke.com.mukechart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * @Annotation <p>普通的基类，
 *               1，封装通用的获取布局文件id的方法；
 *               2，，，</p>
 * @Auth Sunny
 * @date 2017/11/5
 * @Version V1.0.0
 */

public abstract class BaseActivity extends FragmentActivity {


    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        initParams();
    }

    //初始化参数方法lll
    protected abstract void initParams();

    //获取布局文件id
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package chart.muke.com.mukechart.actualchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import chart.muke.com.mukechart.utils.MukeUtils;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/17  10:52
 * @Version v1.0.0
 * @Annotation  自定义水平滚动的ScrollView:
 *           主要是用来监听滚动的距离，
 *           在API 23时，系统已经提供了listener,可直接监听
 */
public class MukeHorizontalScrollView extends HorizontalScrollView {

    private Context context;

    private OnScrollChangedListener mScrollListener;

    public OnScrollChangedListener getScrollListener() {
        return mScrollListener;
    }

    public void setScrollListener(OnScrollChangedListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }

    public MukeHorizontalScrollView(Context context) {
        this(context,null);
    }

    public MukeHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MukeHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int offset = computeHorizontalScrollOffset();//ScrollView滚动条的偏移量
        //computeHorizontalScrollExtent():ScrollView滚动条的长度
        //computeHorizontalScrollRange():水平方向上，ScrollView可滚动的范围区间
        int maxOffset = computeHorizontalScrollRange() - MukeUtils.getScreenWidth(context);
        if (mScrollListener != null)
            mScrollListener.onScrollChanged(this,l,t,oldl,oldt,offset,maxOffset);
    }

    public interface OnScrollChangedListener{
        void onScrollChanged(HorizontalScrollView scrollView, int x, int y, int oldx, int oldy,int offset,int maxOffest);
    }
}

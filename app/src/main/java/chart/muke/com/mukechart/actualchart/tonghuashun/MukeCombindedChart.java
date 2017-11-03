package chart.muke.com.mukechart.actualchart.tonghuashun;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.utils.DataCenter;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/31  15:05
 * @Version v1.0.0
 * @Annotation 蜡烛图组合图
 */
public class MukeCombindedChart extends CombinedChart {

    public static final String TAG = "MukeCombindedChart";

    private MukeMarkerViewFactory mMarkerViewFac;

    private LeftMarkerView mLeftMV;
    private RightMarkerView mRightMV;
    private TopMarkerView mTopMV;
    private BottomMarkerView mBottomMV;

    private DataCenter mDataCenter;

    private boolean mDrawMarkerViews = true;

    public MukeCombindedChart(Context context) {
        this(context, null);
    }

    public MukeCombindedChart(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MukeCombindedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MukeMarkerView);
        mMarkerViewFac = MukeMarkerViewFactory.getInstance();
        mDataCenter = new DataCenter();
        if (typedArray != null){
            boolean showLeftMV = typedArray.getBoolean(R.styleable.MukeMarkerView_showLeftMarker,true);
            boolean showBottomMV = typedArray.getBoolean(R.styleable.MukeMarkerView_showBottomMarker,true);
            boolean showTopMV = typedArray.getBoolean(R.styleable.MukeMarkerView_showTopMarker,false);
            boolean showRightMV = typedArray.getBoolean(R.styleable.MukeMarkerView_showRightMarker,false);
            mDrawMarkerViews = typedArray.getBoolean(R.styleable.MukeMarkerView_drawMarkerView,true);

            if (showLeftMV){
                mLeftMV = (LeftMarkerView) mMarkerViewFac.create(context,MarkerViewType.LEFT);
            }
            if (showBottomMV)
                mBottomMV = (BottomMarkerView) mMarkerViewFac.create(context,MarkerViewType.BOTTOM);
            if (showTopMV){
                mTopMV = (TopMarkerView) mMarkerViewFac.create(context,MarkerViewType.TOP);
            }
            if (showRightMV)
                mRightMV = (RightMarkerView) mMarkerViewFac.create(context,MarkerViewType.RIGHT);

            typedArray.recycle();
        }

    }


    @Override
    protected void drawMarkers(Canvas canvas) {
        Log.e(TAG,"drawMarkers");
        if (!mDrawMarkerViews || !valuesToHighlight()) {
            return;
        }

        for (int i = 0; i < mIndicesToHighlight.length; i++) {
            Highlight highlight = mIndicesToHighlight[i];
            int xIndex = (int) mIndicesToHighlight[i].getX();
            int dataSetIndex = mIndicesToHighlight[i].getDataSetIndex();
            float deltaX = mXAxis != null
                    ? mXAxis.mAxisRange
                    : ((mData == null ? 0.f : mData.getCandleData().getEntryCount()) - 1.f);
            if (xIndex <= deltaX && xIndex <= deltaX * mAnimator.getPhaseX()) {
                Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);
                // make sure entry not null
                if (e == null || e.getX() != mIndicesToHighlight[i].getX())
                    continue;
                float[] pos = getMarkerPosition(highlight);
                // check bounds
                if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                    continue;

                if (null != mTopMV) {
                    mTopMV.refreshContent(e, mIndicesToHighlight[i]);
                    int width = (int) mViewPortHandler.contentWidth();
                    mTopMV.setTvWidth(width);
                    mTopMV.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    mTopMV.layout(0, 0, width,
                            mTopMV.getMeasuredHeight());
                    mTopMV.draw(canvas, mViewPortHandler.contentLeft(), mIndicesToHighlight[i].getY() - mTopMV.getHeight() / 2);
                }

                if (null != mLeftMV) {
                    //修改标记值
                    float yValForHighlight = mIndicesToHighlight[i].getY();
                    mLeftMV.setData(yValForHighlight);

                    mLeftMV.refreshContent(e, mIndicesToHighlight[i]);

                    mLeftMV.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    mLeftMV.layout(0, 0, mLeftMV.getMeasuredWidth(),
                            mLeftMV.getMeasuredHeight());

                    mLeftMV.draw(canvas, mViewPortHandler.contentLeft(), mIndicesToHighlight[i].getY() - mLeftMV.getHeight() / 2);

                }

                if (null != mBottomMV) {
                    String time = mDataCenter.getCandleDatas().get((int) mIndicesToHighlight[i].getX()).mDate;
                    mBottomMV.setData(time);
                    mBottomMV.refreshContent(e, mIndicesToHighlight[i]);

                    mBottomMV.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    mBottomMV.layout(0, 0, mBottomMV.getMeasuredWidth(),
                            mBottomMV.getMeasuredHeight());

                    mBottomMV.draw(canvas, pos[0] - mBottomMV.getWidth() / 2, mViewPortHandler.contentBottom());
                }


            }
        }
    }
}

package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @Annotation <p>简单直方图</p>
 * @Auth Sunny
 * @date 2017/9/24
 * @Version V1.0.0
 */

public class MukeColumnView extends BaseGraphView implements View.OnTouchListener {

    private Context mContext;
    private boolean touchEnable = false;//触摸开关

    private float originalX, originalY;//手指按下时的点的坐标

    public MukeColumnView(Context context) {
        this(context, null);
    }

    public MukeColumnView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MukeColumnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public boolean isTouchEnable() {
        return touchEnable;
    }

    public void setTouchEnable(boolean touchEnable) {
        this.touchEnable = touchEnable;
        if (touchEnable) {
            setOnTouchListener(this);
        }
    }

    @Override
    protected void drawLabel(Canvas canvas, Paint paint) {

    }

    @Override
    protected void drawColumnCurve(Canvas canvas, Paint paint) {
        if (columnInfo == null)
            return;
//        float cellWidth = (width - padding*2) / axisDivideSizeX;
        float columnLeft = originX + padding;//第一个柱条左边距
        float columnRight = padding + padding;//第一个padding为右边间距，第二个padding为箭头的宽度
        /**
         * 每一个柱条的宽度为View的总宽度 减去 两边要留出的距离
         */
        float cellWidth = (width - (columnLeft + columnRight)) / columnInfo.length;
        for (int i = 0; i < columnInfo.length; i++) {
            paint.setColor(columnInfo[i][1]);
            if (mTouchPosition == i && mTouched) {
                //添加触摸事件，当触摸时
                // 方法一：添加透明度。
                paint.setAlpha(128);
            }
            float leftTopY = height - height * columnInfo[i][0] / maxAxisValueY;
            //左上角x,y右下角x,y，画笔
            canvas.drawRect(columnLeft + cellWidth * (i), leftTopY, columnLeft + cellWidth * (i + 1), originY, paint);
        }
    }

    private int mTouchPosition;
    private boolean mTouched;//是否已经触摸

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                originalX = event.getX();
                originalY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float endY = event.getY();
                float columnLeft = originX + padding;//第一个柱条左边距
                float columnRight = padding + padding;//第一个padding为右边间距，第二个padding为箭头的宽度
                float cellWidth = (width - (columnLeft + columnRight)) / columnInfo.length;
                for (int i = 0; i < columnInfo.length; i++) {
                    float leftTopY = height - height * columnInfo[i][0] / maxAxisValueY;
                    if (originalX >= columnLeft + cellWidth * (i) && endX <= columnLeft + cellWidth * (i + 1)) {
                        //有效触摸区域
                        Toast.makeText(mContext, "有效触摸区域:" + i, Toast.LENGTH_SHORT).show();
                        mTouchPosition = i;
                        mTouched = true;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouched = false;
                break;
            default:
                break;
        }
        return true;
    }
}

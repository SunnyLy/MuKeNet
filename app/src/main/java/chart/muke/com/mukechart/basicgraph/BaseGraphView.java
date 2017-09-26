package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import chart.muke.com.mukechart.R;

/**
 * @Annotation <p>创建一个图表抽象基类:
 * 注意事项：
 * 1，图表控件宽高的确定
 * 可以通过获取屏幕的宽高，再减去边距
 * <p>
 * 2，坐标轴上文字的标记
 * 3，标题的位置
 * 由屏幕宽度的一半再减去标题字符串宽度的一半，就得到标题显示在屏幕正中间</p>
 * @Auth Sunny
 * @date 2017/9/24
 * @Version V1.0.0
 */

public abstract class BaseGraphView extends View {
    //坐标轴画笔
    private Paint mXYAxisPaint;
    private Paint mGraphTitlePaint;
    private Paint mAxisNamePaint;
    public Paint mPaint;


    private String mGrapthTitle;
    private String mXAxisName;
    private String mYAxisName;
    private int mAxisTextColor;
    private float mAxisTextSize;

    //X坐标轴最大值
    public float maxAxisValueX = 900;
    //X坐标轴刻度线数量
    public int axisDivideSizeX = 9;
    //Y坐标轴最大值
    public float maxAxisValueY = 700;
    //Y坐标轴刻度线数量
    public int axisDivideSizeY = 7;
    //视图宽度
    public int width;
    //视图高度
    public int height;
    //坐标原点位置，给定默认初始值
    //坐标原点的Y坐标，注意应该在X轴的坐标值，标题，以及Label等展示区上方
    public int originX;
    public int originY;
    public int padding = 30;
    public int mDefMargin = 10;
    public int mDefXValueHeight = 30;
    public int mDefTitleHeight = 30;
    public int mDefLabelHeight = 30;
    //柱状图数据
    public int columnInfo[][];

    //背景颜色
    public int bgColor = Color.WHITE;

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public BaseGraphView(Context context) {
        this(context, null);
    }

    public BaseGraphView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunnyGraphStyle);
        mGrapthTitle = typedArray.getString(R.styleable.SunnyGraphStyle_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_X_AxisName);
        mYAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_Y_AxisName);
        mAxisTextColor = typedArray.getColor(R.styleable.SunnyGraphStyle_axisTextColor, context.getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getDimension(R.styleable.SunnyGraphStyle_axisTextSize, 12);

        if (typedArray != null) {
            typedArray.recycle();
        }

        initPaint(context);

    }

    private void initPaint(Context context) {

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setTextSize(20);
        }
    }


    public void setGrapthTitle(String grapthTitle) {
        mGrapthTitle = grapthTitle;
    }

    public void setXAxisName(String XAxisName) {
        mXAxisName = XAxisName;
    }

    public void setYAxisName(String YAxisName) {
        mYAxisName = YAxisName;
    }

    public void setAxisTextColor(int axisTextColor) {
        mAxisTextColor = axisTextColor;
    }

    public void setAxisTextSize(float axisTextSize) {
        mAxisTextSize = axisTextSize;
    }

    /**
     * 手动设置X轴最大值及等份数
     *
     * @param maxAxisValueX
     * @param dividedSize
     */
    public void setXAxisValue(float maxAxisValueX, int dividedSize) {
        this.maxAxisValueX = maxAxisValueX;
        if (columnInfo != null && columnInfo.length > 0)
            this.axisDivideSizeX = columnInfo.length;
    }

    /**
     * 手动设置Y轴最大值及等份数
     *
     * @param maxAxisValueY
     * @param dividedSize
     */
    public void setYAxisValue(float maxAxisValueY, int dividedSize) {
        this.maxAxisValueY = maxAxisValueY;
        this.axisDivideSizeY = dividedSize;
    }

    /**
     * 传入柱状图数据
     *
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = MeasureSpec.getSize(widthMeasureSpec)  ;
//        height = MeasureSpec.getSize(heightMeasureSpec);

        Log.e(",,,,,,,,,", "width:" + width + ",height:" + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        width = getWidth() - originX-100;
//        height = getHeight() - 400;

        width = getWidth();
        height = getHeight();

        canvas.drawColor(bgColor);

        originX = 100;
        originY = getTop() + height - (mDefXValueHeight + mDefTitleHeight + mDefXValueHeight);
        /**
         * 画X,Y轴
         */
        drawAxisX(canvas, mPaint);
        drawAxisY(canvas, mPaint);
        /**
         * 画X,Y轴上的分割线标记
         */
        drawAxisScaleMarkX(canvas, mPaint);
        drawAxisScaleMarkY(canvas, mPaint);

        /**
         * 画X,Y轴上的箭头
         */
        drawAxisArrowsX(canvas, mPaint);
        drawAxisArrowsY(canvas, mPaint);

        /**
         * 画X,Y轴上的刻度值
         */
        drawAxisScaleMarkValueX(canvas, mPaint);
        drawAxisScaleMarkValueY(canvas, mPaint);

        /**
         * 画柱条
         */
        drawColumn(canvas, mPaint);

        /**
         * 画标题
         */
        drawTitle(canvas, mPaint);

        /**
         * 画颜色标记：如：红代表xx,绿代表xx
         */
        drawLabel(canvas, mPaint);
    }

    protected abstract void drawLabel(Canvas canvas, Paint paint);

    protected abstract void drawAxisScaleMarkValueY(Canvas canvas, Paint paint);

    protected abstract void drawAxisScaleMarkValueX(Canvas canvas, Paint paint);

    //画标题
    private void drawTitle(Canvas canvas, Paint paint) {
        //画标题
        if (!TextUtils.isEmpty(mGrapthTitle)) {
            //设置画笔绘制文字的属性
            mPaint.setColor(mAxisTextColor);
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setFakeBoldText(true);
            canvas.drawText(mGrapthTitle, (getWidth() / 2) - (paint.measureText(mGrapthTitle) / 2), originY + mDefXValueHeight + mDefMargin + 10, paint);
        }
    }

    //开始 画中间的矩形
    protected abstract void drawColumn(Canvas canvas, Paint paint);


    private void drawAxisArrowsY(Canvas canvas, Paint paint) {
        //画三角形（Y轴箭头）
        Path mPathX = new Path();
//        mPathX.moveTo(originX, originY - height - 30);//起始点
//        mPathX.lineTo(originX - 10, originY - height);//下一点
//        mPathX.lineTo(originX + 10, originY - height);//下一点

        mPathX.moveTo(originX, getTop() + padding);//起始点
        mPathX.lineTo(originX - 10, getTop() + padding * 2);//下一点
        mPathX.lineTo(originX + 10, getTop() + padding * 2);//下一点
        mPathX.close();
        paint.setColor(Color.BLACK);
        canvas.drawPath(mPathX, paint);
        if (!TextUtils.isEmpty(mYAxisName))
            canvas.drawText(mYAxisName, originX - 50, getTop() + 40, paint);
    }

    /**
     * X轴上的箭头
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsX(Canvas canvas, Paint paint) {
        //画三角形（X轴箭头）
        Path mPathX = new Path();
        mPathX.moveTo(width - padding, originY);//起始点
        mPathX.lineTo(width - padding * 2, originY - 10);//下一点
        mPathX.lineTo(width - padding * 2, originY + 10);//下一点
        mPathX.close();
        canvas.drawPath(mPathX, paint);
        if (!TextUtils.isEmpty(mXAxisName))
            canvas.drawText(mXAxisName, width - padding * 2, originY + 30, paint);
    }

    /**
     * Y轴上的标记
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkY(Canvas canvas, Paint paint);

    /**
     * X轴上的标记
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkX(Canvas canvas, Paint paint);

    protected abstract void drawAxisY(Canvas canvas, Paint paint);

    protected abstract void drawAxisX(Canvas canvas, Paint paint);
}

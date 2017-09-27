package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.utils.MukeUtils;

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
    /**================
     * 各种画笔（有些开源库，喜欢用一个Paint,
     * 但那样容易乱，不便管理)
     * ================
     */
    private Paint mXYAxisPaint;
    private Paint mTitlePaint;
    private Paint mAxisNamePaint;
    public Paint mPaint;
    public Paint mColumnCurvePaint;

    //用于测量字体的宽高
    private Rect mBoundsRect;


    /**================
     * 自定义Style属性值
     * ================
     */
    private String mGrapthTitle;
    private String mXAxisName;
    private String mYAxisName;
    private int mAxisTextColor;
    private float mAxisTextSize;
    private boolean isShowOrigin;
    private boolean isShowXaxis;
    private boolean isShowYaxis;
    private int axisColor;

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
    private int mCellWidth;//X轴上每一份的宽度
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
        isShowOrigin = typedArray.getBoolean(R.styleable.SunnyGraphStyle_showOrigin,true);
        isShowXaxis = typedArray.getBoolean(R.styleable.SunnyGraphStyle_showXaxis,true);
        isShowYaxis = typedArray.getBoolean(R.styleable.SunnyGraphStyle_showYaxis,true);
        axisColor = typedArray.getColor(R.styleable.SunnyGraphStyle_axisColor,Color.BLACK);

        if (typedArray != null) {
            typedArray.recycle();
        }

        mBoundsRect = new Rect();
        initPaint(context);

    }

    public int getCellWidth() {
        mCellWidth = width / axisDivideSizeX;
        return mCellWidth ;
    }

    private void initPaint(Context context) {

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setTextSize(20);
        }

        mXYAxisPaint = new Paint();
        mXYAxisPaint.setDither(true);
        mXYAxisPaint.setAntiAlias(true);

        mTitlePaint = new Paint();
        mTitlePaint.setDither(true);
        mTitlePaint.setAntiAlias(true);

        mColumnCurvePaint = new Paint();
        mColumnCurvePaint.setDither(true);
        mColumnCurvePaint.setAntiAlias(true);
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
         * 画X,Y轴上的箭头
         */
        mXYAxisPaint.setColor(axisColor);
        if (isShowXaxis){
            drawAxisX(canvas, mXYAxisPaint);
            drawAxisArrowsX(canvas, mXYAxisPaint);

        }

        if (isShowYaxis){
            drawAxisY(canvas, mXYAxisPaint);
            drawAxisArrowsY(canvas, mXYAxisPaint);
        }

        /**
         * 画X,Y轴上的分割线标记
         */
        drawAxisScaleMarkX(canvas, mPaint);
        drawAxisScaleMarkY(canvas, mPaint);


        /**
         * 画X,Y轴上的刻度值
         */
        drawAxisScaleMarkValueX(canvas, mPaint);
        drawAxisScaleMarkValueY(canvas, mPaint);

        /**
         * 画柱条,曲线
         */
        drawColumnCurve(canvas, mColumnCurvePaint);

        /**
         * 画标题
         */
        drawTitle(canvas);

        /**
         * 画颜色标记：如：红代表xx,绿代表xx
         */
        drawLabel(canvas, mPaint);
    }

    protected abstract void drawLabel(Canvas canvas, Paint paint);


    //画标题
    private void drawTitle(Canvas canvas) {
        //画标题
        if (!TextUtils.isEmpty(mGrapthTitle)) {
            //设置画笔绘制文字的属性
            mTitlePaint.setColor(mAxisTextColor);
            mTitlePaint.setTextSize(mAxisTextSize);
            mTitlePaint.setFakeBoldText(true);
            canvas.drawText(mGrapthTitle, (getWidth() / 2) - (mTitlePaint.measureText(mGrapthTitle) / 2), originY + mDefXValueHeight + mDefMargin + 10, mTitlePaint);
        }
    }

    //开始 画中间的矩形
    protected abstract void drawColumnCurve(Canvas canvas, Paint paint);


    /**
     * Y轴上的箭头
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsY(Canvas canvas, Paint paint) {
        //画三角形（Y轴箭头）
        Path mPathX = new Path();
        mPathX.moveTo(originX, getTop() + padding);//起始点
        mPathX.lineTo(originX - 10, getTop() + padding * 2);//下一点
        mPathX.lineTo(originX + 10, getTop() + padding * 2);//下一点
        mPathX.close();
        canvas.drawPath(mPathX, paint);
        if (!TextUtils.isEmpty(mYAxisName)){
            paint.setTextSize(mAxisTextSize);
            paint.getTextBounds(mYAxisName,0,mYAxisName.length(),mBoundsRect);
            float textWidth = mBoundsRect.width();
            float textHeight = mBoundsRect.height();
            float startX = originX+ padding;
            canvas.drawText(mYAxisName, startX, getTop() + padding + textHeight, paint);
        }

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
        if (!TextUtils.isEmpty(mXAxisName)){
            paint.setTextSize( mAxisTextSize);
            paint.getTextBounds(mXAxisName,0,mXAxisName.length(),mBoundsRect);
            float textWidth = mBoundsRect.width();
            float textHeight = mBoundsRect.height();
            float startX = (width - padding)  - textWidth - padding;
            canvas.drawText(mXAxisName, startX, originY + textHeight + padding, paint);
        }
    }

    /**
     * Y轴上的标记
     *
     * @param canvas
     * @param paint
     */
    public  void drawAxisScaleMarkY(Canvas canvas, Paint paint){
        float cellHeight = (height)/ axisDivideSizeY;
        for (int i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(originX, (height - cellHeight * (i + 1)), originX + 10, (height - cellHeight * (i + 1)), paint);
        }
    }

    /**
     * X轴上的标记
     *
     * @param canvas
     * @param paint
     */
    public  void drawAxisScaleMarkX(Canvas canvas, Paint paint){
        float cellWidth = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX - 1; i++) {
            canvas.drawLine(cellWidth * (i + 1) + originX, height ,
                    cellWidth * (i + 1) + originX, height +10, paint);
        }

    }

    /**]
     * Y轴上标记 对应的值
     * @param canvas
     * @param paint
     */
    public  void drawAxisScaleMarkValueY(Canvas canvas, Paint paint){
        float cellHeight = height / axisDivideSizeY;
        float cellValue = maxAxisValueY / axisDivideSizeY;
        paint.reset();
        paint.setTextSize(20);
        paint.setColor(Color.GRAY);
        for (int i = 1; i < axisDivideSizeY; i++) {
            canvas.drawText(String.valueOf(i), originX - 30, height - cellHeight * i + 10, paint);
        }
    }

    /**
     * X轴上标记 对应的值
     * @param canvas
     * @param paint
     */
    public  void drawAxisScaleMarkValueX(Canvas canvas, Paint paint){
        if (columnInfo == null || columnInfo.length ==0){
            return;
        }
        //设置画笔绘制文字的属性
        paint.setColor(Color.GRAY);
        paint.setTextSize(20);
        paint.setFakeBoldText(true);

        float columnLeft = originX + padding;//第一个柱条左边距
        float columnRight = padding + padding;//第一个padding为右边间距，第二个padding为箭头的宽度
        /**
         * 每一个柱条的宽度为View的总宽度 减去 两边要留出的距离
         */
        float cellWidth = (width - (columnLeft + columnRight)) / columnInfo.length;
//        float cellWidth = width / axisDivideSizeX;
        float cellValue = maxAxisValueX / columnInfo.length;
        for (int i = 1; i <= columnInfo.length; i++) {
//            canvas.drawText(String.valueOf(cellValue * i), cellWidth * i + originX - 35, originY + 30, paint);
            //an = a1 + (n-1)*d
            //a1 = columnLeft + cellWidth/2
            //d = cellWidth
            canvas.drawText(String.valueOf(i), (columnLeft + cellWidth/2) + (i -1)*cellWidth, height- (mDefTitleHeight + mDefLabelHeight), paint);
        }
    }
    /**
     * 画Y轴
     * @param canvas
     * @param paint
     */
    public  void drawAxisY(Canvas canvas, Paint paint){
        //画竖轴(Y)
        int[] location = new int[2];
        getLocationInWindow(location);
        canvas.drawLine(originX, originY, originX, getTop()+padding, paint);//参数说明：起始点左边x,y，终点坐标x,y，画笔

    }

    /**
     * 画X轴
     * @param canvas
     * @param paint
     */
    public  void drawAxisX(Canvas canvas, Paint paint){
        paint.setColor(Color.BLACK);
        //设置画笔宽度
        paint.setStrokeWidth(5);
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //画横轴(X)
        canvas.drawLine(originX, originY, width - padding, originY, paint);
    }
}

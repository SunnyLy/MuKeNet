package chart.muke.com.mukechart.actualchart.moji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import chart.muke.com.mukechart.R;
import chart.muke.com.mukechart.utils.MukeUtils;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/10/10  18:55
 * @Version v1.0.0
 * @Annotation 可左右滚动查看的24小时天气预报图
 */
public class WeatherView extends View {
    private Context mContext;

    /**
     * 24小时空气中，每小格的宽度
     **/
    private int mAirCellWidth = 76;//76px
    private int mAirCellHeight = 26;//26px

    /**
     * 空气每小格之间的间隔
     **/
    private int mAirPadding = 4;//4px

    /***各画笔**/
    private Paint mAirPaint;
    private Paint mWeatherPaint;
    private Paint mWeatherDrawablePaint;
    private Paint mTextPaint;//画数值


    /**
     * View的宽高
     **/
    private int mViewWidth;
    private int mViewHeight;

    /**
     * 空气部分的起点坐标
     **/
    private int mAirOffestX;
    private int mAirOffestY;

    private int mPadding = 30;//view边距
    private int mPaddingBottem = 15;

    private float mScrollDistance;
    private int mCurrentIndex = 0;
    private static final int ITEM_SIZE = 24;
    private int offset;
    private int maxOffset;

    private int tempBaseTop;  //温度折线的上边Y坐标
    private int tempBaseBottom; //温度折线的下边Y坐标
    private static final int bottomTextHeight = 60;
    private int maxTemp = 26;
    private int minTemp = 21;
    private int mCellNum;
    private float mStartX;
    private static final int TEMP[] = {24, 25, 26, 26, 26,
            26, 25, 24, 23, 23,
            23, 22, 22, 22, 22,
            22, 22, 21, 21, 21,
            21, 22, 23, 23};

    private static final int AIR_VALUE[] = {102, 100, 93, 93, 83,
            74, 74, 60, 53, 53,
            43, 44, 44, 54, 64,
            32, 32, 22, 33, 33,
            33, 35, 35, 30};
    private static final int WEATHER_RES[] ={R.mipmap.w0, R.mipmap.w1, R.mipmap.w3, -1, -1
            ,R.mipmap.w5, R.mipmap.w7, R.mipmap.w9, -1, -1
            ,-1, R.mipmap.w10, R.mipmap.w15, -1, -1
            ,-1, -1, -1, -1, -1
            ,R.mipmap.w18, -1, -1, R.mipmap.w19};

    private List<Point> mWeatherPoints = new ArrayList<>();

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public float getScrollDistance() {
        return mScrollDistance;
    }

    public void setScrollDistance(float scrollDistance, int offset, int maxOffset) {
        this.mScrollDistance = scrollDistance;
        int times = (int) (mScrollDistance/mAirCellWidth);//滑动的距离 是最小宽度的倍数
        int num = 0;
        if (mScrollDistance%mAirCellWidth ==0){
            //刚好滑动的距离为最小宽度的倍数
           num = times;
        }else{
            if ((mScrollDistance%mAirCellWidth) >= mAirCellWidth/2){
                num = times + 1;
            }else{
                num = times;
            }
        }
        mCellNum = num>23?23:num;
//        Log.e("sunny:","num="+num+",mCellNum="+mCellNum);

        int index = calculateItemIndex(offset);
        Log.e("sunny:","curIndex="+index);
        mCurrentIndex = index;//滚动后对应的当前空气View所在位置
        this.offset = offset;
        this.maxOffset = maxOffset;
        invalidate();
    }

    //通过滚动条偏移量计算当前选择的时刻
    private int calculateItemIndex(int offset){
        int x = getScrollBarX();//X轴上滚动的距离
        int sum = mPadding-mAirCellWidth/2;//判断滚动
        for(int i=0; i<ITEM_SIZE; i++){
          //  if (i==0)
            sum += mAirCellWidth + mAirPadding;
//            else
//                sum += mAirCellWidth/2 + mPadding;
            Log.e("sunny:","scrollbarX:"+x+",sum="+sum+",i="+i);
            if(x < sum){
                return i;
            }
        }
        return ITEM_SIZE - 1;
    }

    private void initView() {

        mAirPaint = new Paint();
        mAirPaint.setColor(0xFF28A77F);
        mAirPaint.setDither(true);
        mAirPaint.setAntiAlias(true);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(18);

        mWeatherPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeatherPaint.setTextSize(18);

        mWeatherDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeatherDrawablePaint.setTextSize(18);

        mWeatherPoints.clear();
        for (int i = 0; i < TEMP.length; i++) {

            //先画时间点的矩形块
            RectF rectF = new RectF();
            rectF.left = mAirOffestX + (mAirCellWidth + mAirPadding) * i;
            rectF.top = mAirOffestY;
            rectF.right = mAirCellWidth * (i + 1) + mAirPadding * i;
            rectF.bottom = mAirOffestY + mAirCellHeight;

            //再计算各时间点对应的温度值的坐标
            Point point = getWeatherPoint(rectF,TEMP[i]);
            mWeatherPoints.add(point);
        }
    }

    private Point getWeatherPoint(RectF rectF, int tempWeather) {
        Point weatherPoint = new Point();
        int offsetTop = getTop() -getPaddingTop();
        if (tempWeather <= minTemp){
            //最小值
            weatherPoint.set((int) rectF.left, offsetTop+235);
        }else if (tempWeather >= maxTemp){
            //最大值
            weatherPoint.set((int) rectF.left, offsetTop + 90);
        }else{
            //中间值
            int minHeight = offsetTop+235;
            int maxHeight = offsetTop + 90;
            int unit = maxTemp - minTemp;//Y轴等份数
            int unitHeight = (minHeight - maxHeight)/unit;//每等份的高度
            int tempY = maxHeight + (unit - (tempWeather - minTemp))*unitHeight;//得出当前温度的Y轴坐标
            weatherPoint.set((int) rectF.left,tempY);
        }
        return weatherPoint;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = mAirCellWidth * 24 + mAirPadding * 23 + mPadding * 2;

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int[] screenSize = MukeUtils.getScreenWidth_Height(mContext);

        if (heightMode == MeasureSpec.EXACTLY) {
            mViewHeight = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            mViewHeight = screenSize[1];
        }

        tempBaseTop = (mViewHeight - bottomTextHeight)/4;
        tempBaseBottom = (mViewHeight - bottomTextHeight)*2/3;
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFF4F4A4F);
        drawAir(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 画空气部分的值
     *
     * @param canvas
     */
    private void drawAir(Canvas canvas) {
        mAirOffestX = mPadding;
        mAirOffestY = getHeight() - mPaddingBottem;

        Path weatherPath = new Path();
        Path weatherDrawablePath = new Path();//天气图片
        for (int i = 0; i < 24; i++) {

            //先画矩形
            RectF rectF = new RectF();
            rectF.left = mAirOffestX + (mAirCellWidth + mAirPadding) * i;
            rectF.top = mAirOffestY;
//            rectF.right = mAirCellWidth * (i + 1) + mAirPadding * i;
            rectF.right = rectF.left + mAirCellWidth -1 ;
            rectF.bottom = mAirOffestY + mAirCellHeight;
            Path path = new Path();

            path.moveTo(rectF.left,rectF.bottom);
            path.lineTo(rectF.left,rectF.bottom - 20);
            //画左角弧形
            RectF roundAngle = new RectF(rectF.left,rectF.top,(rectF.left+12),(rectF.top+12));
            path.arcTo(roundAngle,180,90);
            //再画直线
            path.lineTo(rectF.right-6,rectF.top);
            //再画右角弧形
            RectF roundAngleRight = new RectF(rectF.right - 12,rectF.top,rectF.right,rectF.top + 12);
            path.arcTo(roundAngleRight,270,90);
            //再画直线
            path.lineTo(rectF.right,rectF.bottom);
            path.close();

            mAirPaint.setPathEffect(new CornerPathEffect(1));

            if (i == mCurrentIndex){
                mAirPaint.setAlpha(255);
            }else{
                mAirPaint.setAlpha(125);
            }
            canvas.drawPath(path,mAirPaint);

            //画空气值Text
            drawAirValueText(canvas,rectF,i);

            //画温度曲线
            drawTempCurve(rectF,weatherPath,i);
        }

        mWeatherDrawablePaint.setColor(0xFFA1A6AA);
        //因为是画曲线，用CornerPathEffect()会更圆滑
        mWeatherDrawablePaint.setDither(true);
        mWeatherDrawablePaint.setAntiAlias(true);
        mWeatherDrawablePaint.setStrokeWidth(3);
        mWeatherDrawablePaint.setStyle(Paint.Style.FILL);
        //1,前两个小时，为少云
        drawMinCloud(canvas,weatherDrawablePath);

       // canvas.drawPath(weatherDrawablePath,mWeatherDrawablePaint);
        canvas.drawPath(weatherPath,mWeatherPaint);
       // drawWeatherInfo(weatherPath);

        mStartX = mAirOffestX + mScrollDistance;
      //  canvas.drawText("38",mPadding + getScrollBarX(),mStartY,mTextPaint);


    }

    private void drawAirValueText(Canvas canvas, RectF rect, int i) {
        //画出box上面的风力提示文字
        Rect targetRect = new Rect();
        if (i == 0){
            //最左边
            targetRect.left = mPadding;
            targetRect.right = mPadding+mAirCellWidth;
        }else if (i == ITEM_SIZE -1){
            //最右边
            targetRect.left = getScrollBarX();
            targetRect.right = mViewWidth - mPadding - mAirCellWidth-mAirCellWidth/2;
        } else{
            targetRect.left = getScrollBarX();
            targetRect.right = getScrollBarX() + mAirCellWidth;
        }
        targetRect.top = (int) rect.top - MukeUtils.dp2px(20);
        targetRect.bottom = (int) rect.top - MukeUtils.dp2px(0);
//        Rect targetRect = new Rect(getScrollBarX(), (int) rect.top - MukeUtils.dp2px(20)
//                , getScrollBarX() + mAirCellWidth, (int) rect.top - MukeUtils.dp2px(0));
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        if (i == mCurrentIndex)
        canvas.drawText(""+AIR_VALUE[i], targetRect.centerX(), baseline, mTextPaint);
    }

    private void drawMinCloud(Canvas canvas, Path weatherDrawablePath) {
        weatherDrawablePath.moveTo(mAirOffestX,mWeatherPoints.get(0).y);
        weatherDrawablePath.lineTo(mAirOffestX+(mAirCellWidth + mAirPadding) ,mWeatherPoints.get(1).y);
        weatherDrawablePath.lineTo(mAirOffestX+(mAirCellWidth*2 + mAirPadding) ,mWeatherPoints.get(1).y);
        weatherDrawablePath.lineTo(mAirOffestX+(mAirCellWidth*2 + mAirPadding) ,(getTop() - getPaddingTop() + 300));
        weatherDrawablePath.lineTo(mAirOffestX ,(getTop() - getPaddingTop() + 300));

        canvas.drawPath(weatherDrawablePath,mWeatherDrawablePaint);

        Drawable drawable = ContextCompat.getDrawable(getContext(), WEATHER_RES[0]);
        int radius = mAirCellWidth/2;
        int width = mAirCellWidth*2 + mAirPadding;
        int left =  width/2;
        int right = left + radius*2;
        int top = (getTop() - getPaddingTop() + 300) - radius*2;
        int bottom = (getTop() - getPaddingTop() + 300);
        drawable.setBounds(left,
                top,
                right,
                bottom);
        drawable.draw(canvas);
    }

    private void drawWeatherPic(RectF rectF, Path weatherDrawablePath, int i) {
        mWeatherDrawablePaint.setColor(0xFFA1A6AA);
        //因为是画曲线，用CornerPathEffect()会更圆滑
        CornerPathEffect pathEffect = new CornerPathEffect(10);
        mWeatherDrawablePaint.setPathEffect(pathEffect);
        mWeatherDrawablePaint.setDither(true);
        mWeatherDrawablePaint.setAntiAlias(true);
        mWeatherDrawablePaint.setStrokeWidth(3);
//        mWeatherDrawablePaint.setStyle(Paint.Style.FILL);
        if(i != 0){
            weatherDrawablePath.lineTo(rectF.left,mWeatherPoints.get(i).y);

        }else{
            weatherDrawablePath.moveTo(rectF.left, mWeatherPoints.get(0).y);
        }
    }

    private void drawWeatherInfo(Path weatherPath) {
        //闭合区间用来画天气情况：比如，少云，多云等。
        weatherPath.lineTo(mAirOffestX + (mAirCellWidth + mAirPadding) * 23,(getTop() - getPaddingTop() + 300));
        weatherPath.lineTo(mAirOffestX,(getTop() - getPaddingTop() + 300));
        weatherPath.lineTo(mAirOffestX,mWeatherPoints.get(0).y);

        /**
         * x0,y0:渐变的起点坐标
         * x1,y1:渐变的终点坐标
         * 用来控制，水平/垂直渐变
         */
        LinearGradient mShader = new LinearGradient(0,0,mAirOffestX,mAirOffestY,
                new int[] {ColorTemplate.COLORFUL_COLORS[0],Color.TRANSPARENT},null, Shader.TileMode.CLAMP);
        mWeatherPaint.setShader(mShader);

        //TODO
    }

    /**
     * 画温度曲线
     * @param rectF
     * @param weatherPath
     * @param i
     */
    private void drawTempCurve(RectF rectF, Path weatherPath, int i) {
        mWeatherPaint.setColor(Color.WHITE);
        Point point = mWeatherPoints.get(i);
        //因为是画曲线，用CornerPathEffect()会更圆滑
        CornerPathEffect pathEffect = new CornerPathEffect(50);
        mWeatherPaint.setPathEffect(pathEffect);
        mWeatherPaint.setDither(true);
        mWeatherPaint.setAntiAlias(true);
        mWeatherPaint.setStrokeWidth(3);
        mWeatherPaint.setStyle(Paint.Style.STROKE);
        if(i != 0){
            weatherPath.lineTo(rectF.left,mWeatherPoints.get(i).y);

        }else{
            weatherPath.moveTo(rectF.left, mWeatherPoints.get(0).y);
        }
    }

    /**
     * 画天气图片
     * @param canvas
     * @param position
     * @param left
     * @param right
     */
    private void drawWeatherPic(Canvas canvas, int position, float left, float right) {
        Point point = calculateTempPoint((int) left,(int) right,TEMP[position]);
        if(WEATHER_RES[position] != -1 && position != mCurrentIndex){
            Drawable drawable = ContextCompat.getDrawable(getContext(), WEATHER_RES[position]);
            drawable.setBounds(point.x - MukeUtils.dp2px(10),
                    point.y - MukeUtils.dp2px(25),
                    point.x + MukeUtils.dp2px(10),
                    point.y - MukeUtils.dp2px(5));
            drawable.draw(canvas);
        }
    }

    private int getScrollBarX(){
        if (maxOffset <= 0){
            return 0;
        }

        int x = (ITEM_SIZE - 1) * mAirCellWidth * offset / maxOffset;
        return x + mPadding;
    }


    private Point calculateTempPoint(int left, int right, int temp){
        double minHeight = tempBaseTop;
        double maxHeight = tempBaseBottom;
        double tempY = maxHeight - (temp - minTemp)* 1.0/(maxTemp - minTemp) * (maxHeight - minHeight);
        Point point = new Point((left + right)/2, (int)tempY);
        return point;
    }
}

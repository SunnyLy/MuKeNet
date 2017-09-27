package chart.muke.com.mukechart.basicgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * @Annotation <p>简单直方图</p>
 * @Auth Sunny
 * @date 2017/9/24
 * @Version V1.0.0
 */

public class MukeColumnView extends BaseGraphView {

    public MukeColumnView(Context context) {
        super(context);
    }

    public MukeColumnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MukeColumnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawLabel(Canvas canvas, Paint paint) {

    }

    @Override
    protected void drawColumnCurve(Canvas canvas, Paint paint) {
        if(columnInfo == null)
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
            float leftTopY = height - height * columnInfo[i][0] / maxAxisValueY;
            //左上角x,y右下角x,y，画笔
            canvas.drawRect(columnLeft+ cellWidth * (i), leftTopY, columnLeft+cellWidth * (i + 1), originY, paint);
        }
    }
}

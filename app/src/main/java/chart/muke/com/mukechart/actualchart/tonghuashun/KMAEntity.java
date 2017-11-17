package chart.muke.com.mukechart.actualchart.tonghuashun;

import java.util.ArrayList;

import static java.lang.Float.NaN;

/**
 * Created by loro on 2017/3/7.
 */

public class KMAEntity {
    private ArrayList<Float> MAs;

    /**
     * 得到已N日为单位的均值
     *
     * @param kLineBeen
     * @param n         几日均值
     */
    public KMAEntity(ArrayList<KLineBean2> kLineBeen, int n) {
        this(kLineBeen, n, NaN);
    }

    /**
     * 得到已N日为单位的均值
     *
     * @param kLineBeen
     * @param n         几日均值
     * @param defult    不足N日时的默认值
     */
    public KMAEntity(ArrayList<KLineBean2> kLineBeen, int n, float defult) {
        MAs = new ArrayList<Float>();
        float ma = 0.0f;
        int index = n - 1;
        if (kLineBeen != null && kLineBeen.size() > 0) {
            for (int i = 0; i < kLineBeen.size(); i++) {
                if (i >= index) {
                    ma = getSum(i - index, i, kLineBeen) / n;
                } else {
                    ma = defult;
                }
                MAs.add(ma);
            }
        }
    }


    private static float getSum(Integer a, Integer b, ArrayList<KLineBean2> datas) {
        float sum = 0;
        for (int i = a; i <= b; i++) {
            sum += datas.get(i).mClosePrice;
        }
        return sum;
    }

    public static float getLastMA(ArrayList<KLineBean2> datas, int n) {
        if (null != datas && datas.size() > 0) {
            int count = datas.size() - 1;
            int index = n - 1;
            if (datas.size() >= n) {
                return getSum(count - index, count, datas) / n;
            }
            return NaN;
        }
        return NaN;
    }

    public ArrayList<Float> getMAs() {
        return MAs;
    }

}
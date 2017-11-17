package chart.muke.com.mukechart.actualchart.tonghuashun.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loro on 2017/3/2.
 */

public class KDJEntity {

    private ArrayList<Float> Ks;
    private ArrayList<Float> Ds;
    private ArrayList<Float> Js;

    /**
     * 得到KDJ
     *
     * @param kLineBeens
     * @param n          N为0时，时间范围取之前所有
     */
    public KDJEntity(List<KLineBean> kLineBeens, int n) {
        Ks = new ArrayList<Float>();
        Ds = new ArrayList<Float>();
        Js = new ArrayList<Float>();

        ArrayList<Float> ks = new ArrayList<Float>();
        ArrayList<Float> ds = new ArrayList<Float>();
        ArrayList<Float> js = new ArrayList<Float>();

        float k = 50.0f;
        float d = 50.0f;
        float j = 0.0f;
        float rSV = 0.0f;

        if (kLineBeens != null && kLineBeens.size() > 0) {

            KLineBean kLineBean = kLineBeens.get(0);
            float high = kLineBean.high;
            float low = kLineBean.low;

            for (int i = 0; i < kLineBeens.size(); i++) {
                kLineBean = kLineBeens.get(i);
                if (i > 0) {
                    if (n == 0) {
                        high = high > kLineBean.high ? high : kLineBean.high;
                        low = low < kLineBean.low ? low : kLineBean.low;
                    } else {
                        int t = i - n + 1;
                        Float[] wrs = getHighAndLowByK(t, i, (ArrayList<KLineBean>) kLineBeens);
                        high = wrs[0];
                        low = wrs[1];
                    }
                }
                if (high != low) {
                    rSV = (kLineBean.close - low) / (high - low) * 100;
                } else {
                    rSV = 0;
                }
                k = k * 2 / 3 + rSV / 3;
                d = d * 2 / 3 + k / 3;
                j = (3 * k) - (2 * d);

                //其他软件没有大于100小于0的值，但是我算出来确实有，其它软件在0和100的时候出现直线，怀疑也是做了处理
                j = j < 0 ? 0 : j;
                j = j > 100 ? 100 : j;

                ks.add(k);
                ds.add(d);
                js.add(j);
            }
            for (int i = 0; i < ks.size(); i++) {
                Ks.add(ks.get(i));
                Ds.add(ds.get(i));
                Js.add(js.get(i));
            }
        }
    }

    /**
     * 得到某区间内最高价和最低价
     *
     * @param a          开始位置 可以为0
     * @param b          结束位置
     * @param kLineBeens
     * @return
     */
    private Float[] getHighAndLowByK(Integer a, Integer b, ArrayList<KLineBean> kLineBeens) {
        if (a < 0)
            a = 0;

        KLineBean kLineBean = kLineBeens.get(a);
        float high = kLineBean.high;
        float low = kLineBean.low;
        Float[] wrs = new Float[2];
        for (int i = a; i <= b; i++) {
            kLineBean = kLineBeens.get(i);
            high = high > kLineBean.high ? high : kLineBean.high;
            low = low < kLineBean.low ? low : kLineBean.low;
        }

        wrs[0] = high;
        wrs[1] = low;
        return wrs;
    }


    public ArrayList<Float> getK() {
        return Ks;
    }

    public ArrayList<Float> getD() {
        return Ds;
    }

    public ArrayList<Float> getJ() {
        return Js;
    }
}

package chart.muke.com.mukechart.actualchart.tonghuashun.demo;

import java.util.ArrayList;

/**
 * Created by loro on 2017/3/8.
 */

public class EXPMAEntity {

    private ArrayList<Float> EXPMAs;

    public EXPMAEntity(ArrayList<KLineBean> kLineBeens, int n) {
        EXPMAs = new ArrayList<>();

        float ema = 0.0f;
        float t = n + 1;
        float yz = 2 / t;
        if (kLineBeens != null && kLineBeens.size() > 0) {

            for (int i = 0; i < kLineBeens.size(); i++) {
                if (i == 0) {
                    ema = kLineBeens.get(i).close;
                } else {
                    ema = (yz * kLineBeens.get(i).close) + ((1 - yz) * ema);
//                    ema = (kLineBeens.get(i).close - ema) * (2 / (n + 1)) + ema;
                }
                EXPMAs.add(ema);
            }
        }
    }

    public ArrayList<Float> getEXPMAs() {
        return EXPMAs;
    }
}

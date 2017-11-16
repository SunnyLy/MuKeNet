package chart.muke.com.mukechart.constant;

/**
 * ------------------------------------------------
 * Copyright © 2014-2017 CLife. All Rights Reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2017/9/28  13:34
 * @Version v1.0.0
 * @Annotation
 */
public class MukeConstant {

    /**
     * 自定义View的类型
     */
    public static class ViewStyle{
        public static final int CIRCLE = 0x100;//圆
        public static final int ROUND_RECT = 0x101;//圆角矩形
        public static final int TRIANGLE = 0x102;//三角形
        public static final int ARC = 0x103;//弧形
        public static final int BEZIER = 0x104;//贝塞尔曲线
        public static final int NONE = 0x00;//默认为圆形
    }


    public static final String KLINEURL = "{\"code\":0,\"msg\":\"\",\"data\":{\"sz002081\":" +
            "{\"day\":[[\"2016/01/08\",\"19.12\",\"19.00\",\"19.25\",\"18.36\",\"185103.00\"]," +
            "[\"2016/01/09\",\"18.72\",\"18.53\",\"19.30\",\"18.50\",\"148269.00\"]," +
            "[\"2016/01/12\",\"18.54\",\"18.70\",\"18.99\",\"18.20\",\"121594.00\"]," +
            "[\"2016/01/13\",\"18.60\",\"20.57\",\"20.57\",\"18.40\",\"356289.00\"]," +
            "[\"2016/01/14\",\"20.29\",\"20.12\",\"21.20\",\"20.10\",\"224766.00\"]," +
            "[\"2016/01/15\",\"20.11\",\"20.88\",\"21.18\",\"19.80\",\"169003.00\"]," +
            "[\"2016/01/16\",\"20.78\",\"22.56\",\"22.97\",\"20.75\",\"358150.00\"]," +
            "[\"2016/01/19\",\"21.78\",\"23.19\",\"24.30\",\"21.01\",\"372455.00\"]," +
            "[\"2016/01/20\",\"23.50\",\"23.63\",\"24.42\",\"23.01\",\"329929.00\"]," +
            "[\"2016/01/21\",\"23.60\",\"23.45\",\"24.20\",\"23.00\",\"230416.00\"]," +
            "[\"2016/01/22\",\"23.33\",\"23.60\",\"23.95\",\"23.33\",\"140173.00\"]," +
            "[\"2016/01/23\",\"23.65\",\"23.60\",\"24.30\",\"22.90\",\"150031.00\"]," +
            "[\"2016/01/26\",\"23.60\",\"25.10\",\"25.50\",\"23.00\",\"213255.00\"]," +
            "[\"2016/01/27\",\"25.08\",\"24.91\",\"25.19\",\"24.17\",\"130545.00\"]," +
            "[\"2016/01/28\",\"24.55\",\"24.31\",\"24.98\",\"23.88\",\"112312.00\"]," +
            "[\"2016/01/29\",\"24.00\",\"24.08\",\"24.50\",\"23.75\",\"100329.00\"]," +
            "[\"2016/01/30\",\"24.08\",\"22.73\",\"24.29\",\"22.51\",\"200311.00\"]," +
            "[\"2016/02/02\",\"22.22\",\"22.76\",\"22.89\",\"22.18\",\"136929.00\"]," +
            "[\"2016/02/03\",\"22.87\",\"24.65\",\"24.95\",\"22.84\",\"202499.00\"]," +
            "[\"2016/02/04\",\"24.90\",\"24.91\",\"25.37\",\"24.00\",\"171597.00\"]," +
            "[\"2016/02/05\",\"25.29\",\"24.02\",\"25.36\",\"24.00\",\"162542.00\"]," +
            "[\"2016/02/06\",\"23.98\",\"23.40\",\"24.50\",\"22.90\",\"95130.00\"]," +
            "[\"2016/02/09\",\"23.25\",\"24.79\",\"25.11\",\"23.25\",\"193043.00\"]," +
            "[\"2016/02/10\",\"24.95\",\"25.00\",\"25.08\",\"24.15\",\"100616.00\"]," +
            "[\"2016/02/11\",\"24.72\",\"25.65\",\"26.77\",\"24.72\",\"250914.00\"]," +
            "[\"2016/02/12\",\"25.50\",\"25.81\",\"26.35\",\"25.50\",\"126058.00\"]," +
            "[\"2016/02/13\",\"25.85\",\"25.81\",\"26.30\",\"25.68\",\"91586.00\"]," +
            "[\"2016/02/16\",\"25.82\",\"26.83\",\"27.19\",\"25.80\",\"230116.00\"]," +
            "[\"2016/02/17\",\"27.11\",\"28.99\",\"29.51\",\"26.90\",\"145368.00\"]," +
            "[\"2016/02/25\",\"29.65\",\"28.76\",\"31.00\",\"27.46\",\"238173.00\"]," +
            "[\"2016/02/26\",\"28.40\",\"29.45\",\"30.50\",\"28.21\",\"169563.00\"]," +
            "[\"2016/02/27\",\"28.60\",\"28.74\",\"29.20\",\"28.20\",\"176638.00\"]," +
            "[\"2016/03/02\",\"28.86\",\"28.37\",\"29.04\",\"27.44\",\"206785.00\"]," +
            "[\"2016/03/03\",\"28.22\",\"27.55\",\"28.41\",\"27.33\",\"184383.00\"]," +
            "[\"2016/03/04\",\"27.55\",\"26.74\",\"27.55\",\"26.18\",\"304093.00\"]," +
            "[\"2016/03/05\",\"26.57\",\"26.80\",\"27.16\",\"26.30\",\"176714.00\"]," +
            "[\"2016/03/06\",\"26.81\",\"26.20\",\"26.95\",\"25.50\",\"175459.00\"]," +
            "[\"2016/03/09\",\"25.80\",\"26.45\",\"26.48\",\"25.33\",\"159204.00\"]," +
            "[\"2016/03/10\",\"26.50\",\"27.62\",\"27.74\",\"26.10\",\"221012.00\"]," +
            "[\"2016/03/11\",\"27.46\",\"26.56\",\"27.66\",\"26.20\",\"173080.00\"]," +
            "[\"2016/03/12\",\"26.66\",\"26.41\",\"27.09\",\"25.88\",\"117987.00\"]," +
            "[\"2016/03/13\",\"26.21\",\"26.71\",\"26.82\",\"26.01\",\"120784.00\"]," +
            "[\"2016/03/16\",\"26.68\",\"29.05\",\"29.06\",\"26.18\",\"332473.00\"]," +
            "[\"2016/03/17\",\"29.08\",\"28.45\",\"29.15\",\"28.12\",\"193844.00\"]," +
            "[\"2016/03/18\",\"28.28\",\"28.73\",\"28.79\",\"27.80\",\"218231.00\"]," +
            "[\"2016/03/19\",\"28.54\",\"28.12\",\"28.54\",\"27.80\",\"202060.00\"]," +
            "[\"2016/03/20\",\"28.05\",\"27.92\",\"28.06\",\"27.58\",\"195466.00\"]," +
            "[\"2016/03/23\",\"28.36\",\"28.96\",\"29.30\",\"28.18\",\"280738.00\"]," +
            "[\"2016/03/24\",\"28.97\",\"29.90\",\"30.29\",\"27.71\",\"377201.00\"]," +
            "[\"2016/03/25\",\"30.24\",\"32.89\",\"32.89\",\"30.24\",\"492223.00\"]," +
            "[\"2016/03/26\",\"33.00\",\"32.19\",\"34.20\",\"31.28\",\"515999.00\"]," +
            "[\"2016/03/27\",\"32.85\",\"35.40\",\"35.41\",\"31.51\",\"359527.37\"]," +
            "[\"2016/03/30\",\"34.90\",\"35.00\",\"36.68\",\"34.19\",\"241667.00\"]," +
            "[\"2016/03/31\",\"35.50\",\"35.17\",\"36.89\",\"35.00\",\"222157.00\"]," +
            "[\"2016/04/01\",\"36.24\",\"35.90\",\"36.31\",\"34.65\",\"208226.00\"]," +
            "[\"2016/04/02\",\"35.60\",\"34.40\",\"35.80\",\"34.15\",\"357314.00\"]," +
            "[\"2016/04/03\",\"34.00\",\"33.96\",\"34.37\",\"33.38\",\"235621.00\"]," +
            "[\"2016/04/07\",\"33.90\",\"34.26\",\"34.50\",\"33.74\",\"216172.00\"]," +
            "[\"2016/04/08\",\"34.10\",\"32.85\",\"34.28\",\"32.72\",\"267892.00\"]," +
            "[\"2016/04/09\",\"32.85\",\"33.23\",\"33.50\",\"31.35\",\"279596.00\"]," +
            "[\"2016/04/10\",\"33.16\",\"34.30\",\"34.39\",\"32.60\",\"247968.00\"]," +
            "[\"2016/04/13\",\"34.10\",\"33.33\",\"34.65\",\"33.01\",\"302627.00\"]," +
            "[\"2016/04/14\",\"33.33\",\"32.38\",\"33.33\",\"32.00\",\"237550.00\"]," +
            "[\"2016/04/15\",\"32.27\",\"32.84\",\"33.50\",\"31.35\",\"237718.00\"]," +
            "[\"2016/04/16\",\"32.55\",\"32.38\",\"33.58\",\"32.00\",\"204321.00\"]," +
            "[\"2016/04/17\",\"32.20\",\"32.30\",\"33.15\",\"32.02\",\"191028.00\"]," +
            "[\"2016/04/20\",\"32.19\",\"31.20\",\"33.00\",\"30.78\",\"222683.00\"]," +
            "[\"2016/04/21\",\"31.10\",\"31.89\",\"32.50\",\"30.88\",\"221715.00\"]," +
            "[\"2016/04/22\",\"32.07\",\"33.04\",\"33.30\",\"32.03\",\"217471.00\"]," +
            "[\"2016/04/23\",\"33.12\",\"33.50\",\"34.48\",\"32.70\",\"293017.00\"]," +
            "[\"2016/04/24\",\"33.20\",\"32.65\",\"33.98\",\"32.48\",\"169516.00\"]," +
            "[\"2016/04/27\",\"32.70\",\"32.31\",\"33.59\",\"31.72\",\"209953.00\"]," +
            "[\"2016/04/28\",\"31.96\",\"30.01\",\"31.98\",\"29.40\",\"372144.00\"]," +
            "[\"2016/04/29\",\"30.00\",\"30.28\",\"30.51\",\"29.69\",\"193173.00\"]," +
            "[\"2016/04/30\",\"30.50\",\"30.39\",\"31.13\",\"30.25\",\"200255.00\"]," +
            "[\"2016/05/04\",\"30.39\",\"30.21\",\"30.81\",\"30.08\",\"128096.00\"]," +
            "[\"2016/05/05\",\"30.11\",\"30.90\",\"31.26\",\"30.01\",\"221361.00\"]," +
            "[\"2016/05/06\",\"31.39\",\"30.12\",\"31.49\",\"30.01\",\"196728.00\"]," +
            "[\"2016/05/07\",\"30.06\",\"28.89\",\"30.50\",\"28.45\",\"176297.00\"]," +
            "[\"2016/05/08\",\"28.63\",\"30.10\",\"30.36\",\"28.63\",\"201379.00\"]," +
            "[\"2016/05/11\",\"30.15\",\"32.53\",\"32.85\",\"30.02\",\"300765.00\"]," +
            "[\"2016/06/17\",\"35.67\",\"35.67\",\"35.67\",\"35.67\",\"31555.00\"]," +
            "[\"2016/06/18\",\"36.00\",\"35.37\",\"38.98\",\"34.97\",\"704041.00\"]," +
            "[\"2016/06/19\",\"34.60\",\"35.21\",\"37.50\",\"32.55\",\"390824.00\"]," +
            "[\"2016/06/23\",\"35.90\",\"37.12\",\"37.34\",\"34.30\",\"506878.00\"]," +
            "[\"2016/06/24\",\"37.69\",\"35.70\",\"37.90\",\"34.50\",\"484916.00\"]," +
            "[\"2016/06/25\",\"35.15\",\"32.14\",\"35.17\",\"32.13\",\"448311.00\"]," +
            "[\"2016/06/26\",\"30.00\",\"28.93\",\"31.12\",\"28.93\",\"285069.00\"]," +
            "[\"2016/06/29\",\"29.50\",\"26.04\",\"30.19\",\"26.04\",\"512053.00\"]," +
            "[\"2016/06/30\",\"26.04\",\"28.19\",\"28.64\",\"23.44\",\"598296.00\"]," +
            "[\"2016/07/01\",\"27.79\",\"25.43\",\"29.50\",\"25.37\",\"532487.00\"]," +
            "[\"2016/07/02\",\"26.45\",\"25.27\",\"27.25\",\"24.38\",\"589364.00\"]," +
            "[\"2016/07/03\",\"25.22\",\"25.10\",\"27.49\",\"22.80\",\"464546.00\"]," +
            "[\"2016/07/06\",\"27.61\",\"25.80\",\"27.61\",\"23.77\",\"604335.00\"]," +
            "[\"2016/07/07\",\"25.00\",\"23.26\",\"25.58\",\"23.22\",\"500520.00\"]," +
            "[\"2016/07/10\",\"25.59\",\"25.59\",\"25.59\",\"24.69\",\"294731.00\"]," +
            "[\"2016/07/13\",\"27.90\",\"28.15\",\"28.15\",\"26.08\",\"490461.00\"]," +
            "[\"2016/07/14\",\"28.20\",\"29.18\",\"30.50\",\"27.29\",\"508763.00\"]," +
            "[\"2016/07/15\",\"28.13\",\"26.26\",\"28.60\",\"26.26\",\"309393.00\"]," +
            "[\"2016/07/16\",\"26.26\",\"26.79\",\"27.50\",\"24.20\",\"259400.00\"]," +
            "[\"2016/07/17\",\"26.99\",\"28.37\",\"28.88\",\"26.95\",\"236307.00\"],[\"2016/07/20\",\"28.58\",\"27.97\",\"29.18\",\"27.60\",\"256482.00\"],[\"2016/07/21\",\"27.58\",\"27.98\",\"28.53\",\"27.20\",\"194943.00\"],[\"2016/07/22\",\"27.55\",\"26.82\",\"28.00\",\"26.10\",\"368791.00\"],[\"2016/07/23\",\"26.85\",\"27.27\",\"27.52\",\"26.29\",\"328047.00\"],[\"2016/07/24\",\"27.17\",\"26.65\",\"27.23\",\"26.41\",\"285046.00\"],[\"2016/07/27\",\"27.00\",\"23.99\",\"27.10\",\"23.99\",\"305865.00\"],[\"2016/07/28\",\"23.00\",\"22.30\",\"23.85\",\"21.59\",\"565467.00\"],[\"2016/07/29\",\"22.90\",\"23.05\",\"23.80\",\"20.93\",\"366429.00\"],[\"2016/07/30\",\"22.58\",\"21.60\",\"22.99\",\"21.50\",\"265692.00\"],[\"2016/07/31\",\"21.16\",\"22.14\",\"22.19\",\"20.90\",\"302069.00\"],[\"2016/08/03\",\"22.00\",\"21.78\",\"22.00\",\"20.35\",\"295652.00\"],[\"2016/08/04\",\"21.70\",\"22.93\",\"23.10\",\"21.50\",\"218375.00\"],[\"2016/08/05\",\"22.68\",\"22.36\",\"23.50\",\"22.30\",\"176969.00\"],[\"2016/08/06\",\"21.77\",\"21.40\",\"22.35\",\"21.31\",\"182870.00\"],[\"2016/08/07\",\"21.71\",\"21.88\",\"22.09\",\"21.31\",\"203770.00\"],[\"2016/08/10\",\"22.20\",\"23.51\",\"23.74\",\"21.89\",\"423415.00\"],[\"2016/08/11\",\"23.38\",\"22.91\",\"23.69\",\"22.80\",\"390854.00\"],[\"2016/08/12\",\"22.50\",\"21.91\",\"22.80\",\"21.86\",\"351568.00\"],[\"2016/08/13\",\"21.70\",\"22.31\",\"22.32\",\"21.50\",\"250720.00\"],[\"2016/08/14\",\"21.71\",\"22.13\",\"22.56\",\"21.70\",\"380285.00\"],[\"2016/08/17\",\"21.89\",\"21.85\",\"22.13\",\"21.51\",\"382347.00\"],[\"2016/08/18\",\"21.87\",\"19.90\",\"22.28\",\"19.67\",\"418692.00\"],[\"2016/08/19\",\"19.20\",\"19.70\",\"19.88\",\"17.91\",\"867565.00\"],[\"2016/08/20\",\"19.29\",\"18.18\",\"19.38\",\"17.97\",\"713111.00\"],[\"2016/08/21\",\"17.60\",\"16.60\",\"18.00\",\"16.55\",\"654835.00\"],[\"2016/08/24\",\"15.79\",\"14.94\",\"15.79\",\"14.94\",\"570743.00\"],[\"2016/08/25\",\"13.75\",\"13.45\",\"14.80\",\"13.45\",\"676539.00\"],[\"2016/08/26\",\"13.45\",\"13.64\",\"14.68\",\"13.01\",\"684866.00\"],[\"2016/08/27\",\"14.15\",\"14.93\",\"14.96\",\"13.64\",\"589858.00\"],[\"2016/08/28\",\"15.32\",\"15.90\",\"15.99\",\"14.75\",\"694801.00\"],[\"2016/08/31\",\"15.45\",\"15.41\",\"15.65\",\"14.76\",\"461673.00\"],[\"2016/09/01\",\"15.05\",\"14.65\",\"15.11\",\"13.90\",\"479480.00\"],[\"2016/09/02\",\"13.69\",\"14.91\",\"15.10\",\"13.60\",\"498930.00\"],[\"2016/09/07\",\"14.92\",\"14.20\",\"15.60\",\"14.20\",\"402829.00\"],[\"2016/09/08\",\"14.01\",\"14.86\",\"15.00\",\"13.70\",\"268439.00\"],[\"2016/09/09\",\"15.10\",\"15.49\",\"15.65\",\"14.88\",\"478260.00\"],[\"2016/09/10\",\"15.20\",\"15.05\",\"15.43\",\"15.00\",\"280570.00\"],[\"2016/09/11\",\"14.99\",\"14.75\",\"15.22\",\"14.45\",\"246984.00\"],[\"2016/09/14\",\"14.85\",\"13.28\",\"14.86\",\"13.28\",\"394075.00\"],[\"2016/09/15\",\"13.00\",\"12.25\",\"13.19\",\"12.02\",\"302154.00\"],[\"2016/09/16\",\"12.45\",\"13.48\",\"13.48\",\"12.31\",\"281973.00\"],[\"2016/09/17\",\"13.41\",\"13.39\",\"14.10\",\"13.11\",\"374399.00\"],[\"2016/09/18\",\"13.45\",\"13.30\",\"13.57\",\"12.99\",\"266589.00\"],[\"2016/09/21\",\"13.00\",\"13.70\",\"13.80\",\"12.90\",\"258642.00\"],[\"2016/09/22\",\"13.71\",\"13.90\",\"14.23\",\"13.60\",\"317067.00\"],[\"2016/09/23\",\"13.75\",\"13.65\",\"13.84\",\"13.48\",\"251750.00\"],[\"2016/09/24\",\"13.76\",\"13.89\",\"14.14\",\"13.70\",\"213722.00\"],[\"2016/09/25\",\"13.80\",\"13.17\",\"13.86\",\"12.95\",\"209454.00\"],[\"2016/09/28\",\"13.20\",\"13.39\",\"13.47\",\"13.00\",\"131688.00\"],[\"2016/09/29\",\"13.10\",\"12.97\",\"13.17\",\"12.89\",\"147435.00\"],[\"2016/09/30\",\"12.99\",\"13.15\",\"13.24\",\"12.86\",\"148187.00\"],[\"2016/10/08\",\"13.81\",\"13.88\",\"14.05\",\"13.53\",\"380919.00\"],[\"2016/10/09\",\"13.88\",\"14.22\",\"14.43\",\"13.77\",\"324269.00\"],[\"2016/10/12\",\"14.44\",\"15.17\",\"15.35\",\"14.35\",\"483082.00\"],[\"2016/10/13\",\"14.95\",\"15.12\",\"15.19\",\"14.83\",\"303929.00\"],[\"2016/10/14\",\"15.00\",\"14.97\",\"15.29\",\"14.87\",\"284898.00\"],[\"2016/10/15\",\"14.84\",\"15.77\",\"15.85\",\"14.80\",\"389705.00\"],[\"2016/10/16\",\"15.93\",\"15.72\",\"16.18\",\"15.36\",\"370393.00\"],[\"2016/10/19\",\"15.73\",\"15.62\",\"16.10\",\"15.38\",\"379663.00\"],[\"2016/10/20\",\"15.61\",\"15.94\",\"16.03\",\"15.38\",\"433642.00\"],[\"2016/10/21\",\"15.95\",\"14.60\",\"16.05\",\"14.50\",\"486439.00\"],[\"2016/10/22\",\"14.63\",\"15.02\",\"15.17\",\"14.34\",\"373226.00\"],[\"2016/10/23\",\"15.09\",\"15.40\",\"15.60\",\"14.90\",\"412351.00\"],[\"2016/10/26\",\"15.66\",\"15.52\",\"15.83\",\"15.29\",\"437417.00\"],[\"2016/10/27\",\"15.40\",\"15.33\",\"15.45\",\"14.53\",\"339427.00\"],[\"2016/10/28\",\"15.26\",\"15.10\",\"15.80\",\"15.00\",\"348481.00\"],[\"2016/10/29\",\"15.21\",\"15.55\",\"15.66\",\"15.02\",\"283531.00\"],[\"2016/10/30\",\"15.55\",\"15.30\",\"15.56\",\"14.91\",\"356755.00\"],[\"2016/11/02\",\"14.93\",\"14.56\",\"15.23\",\"14.50\",\"329189.00\"],[\"2016/11/03\",\"14.55\",\"14.48\",\"14.76\",\"14.35\",\"207398.00\"],[\"2016/11/04\",\"14.60\",\"15.85\",\"15.90\",\"14.56\",\"492216.00\"],[\"2016/11/05\",\"15.88\",\"16.52\",\"17.04\",\"15.78\",\"868952.00\"],[\"2016/11/06\",\"16.65\",\"17.51\",\"17.77\",\"16.41\",\"650157.00\"],[\"2016/11/09\",\"17.40\",\"17.46\",\"17.80\",\"16.81\",\"545421.00\"],[\"2016/11/10\",\"17.27\",\"17.45\",\"17.79\",\"17.11\",\"407447.00\"],[\"2016/11/11\",\"17.30\",\"17.54\",\"17.64\",\"17.00\",\"427455.00\"],[\"2016/11/12\",\"17.66\",\"17.55\",\"18.20\",\"17.33\",\"490032.00\"],[\"2016/11/13\",\"17.21\",\"16.80\",\"17.33\",\"16.66\",\"400540.00\"],[\"2016/11/16\",\"16.28\",\"17.31\",\"17.38\",\"16.22\",\"284873.00\"],[\"2016/11/17\",\"17.51\",\"16.91\",\"17.90\",\"16.88\",\"408779.00\"],[\"2016/11/18\",\"16.91\",\"16.54\",\"17.08\",\"16.42\",\"304213.00\"],[\"2016/11/19\",\"16.59\",\"16.91\",\"16.94\",\"16.37\",\"279270.00\"],[\"2016/11/20\",\"16.92\",\"16.91\",\"17.08\",\"16.72\",\"270150.00\"],[\"2016/11/23\",\"16.89\",\"17.23\",\"17.70\",\"16.78\",\"416514.00\"],[\"2016/11/24\",\"17.32\",\"17.83\",\"17.85\",\"17.06\",\"350648.00\"],[\"2016/11/25\",\"17.88\",\"18.09\",\"18.10\",\"17.53\",\"391541.00\"],[\"2016/11/26\",\"18.17\",\"17.40\",\"18.35\",\"17.40\",\"436484.00\"],[\"2016/11/27\",\"17.29\",\"15.86\",\"17.36\",\"15.66\",\"418290.00\"],[\"2016/11/30\",\"16.02\",\"16.18\",\"16.25\",\"15.17\",\"353051.00\"],[\"2016/12/01\",\"16.15\",\"16.49\",\"16.78\",\"15.77\",\"355027.00\"],[\"2016/12/02\",\"16.30\",\"16.94\",\"17.06\",\"16.10\",\"422471.00\"],[\"2016/12/03\",\"17.00\",\"17.04\",\"17.45\",\"16.88\",\"397669.00\"],[\"2016/12/04\",\"16.80\",\"16.76\",\"16.98\",\"16.60\",\"254673.00\"],[\"2016/12/07\",\"16.83\",\"16.76\",\"16.92\",\"16.61\",\"192508.00\"],[\"2016/12/08\",\"16.72\",\"15.97\",\"16.72\",\"15.91\",\"281667.00\"],[\"2016/12/09\",\"15.85\",\"16.10\",\"16.15\",\"15.75\",\"162891.00\"],[\"2016/12/10\",\"16.12\",\"16.46\",\"16.88\",\"16.10\",\"319148.00\"],[\"2016/12/11\",\"16.44\",\"16.70\",\"16.84\",\"16.36\",\"217099.00\"],[\"2016/12/14\",\"16.55\",\"18.37\",\"18.37\",\"16.47\",\"488192.00\"],[\"2016/12/15\",\"18.40\",\"19.00\",\"19.68\",\"18.35\",\"782479.00\"],[\"2016/12/16\",\"19.12\",\"18.91\",\"19.48\",\"18.70\",\"411009.00\"],[\"2016/12/17\",\"18.93\",\"19.30\",\"19.48\",\"18.90\",\"434939.00\"],[\"2016/12/18\",\"19.38\",\"19.16\",\"19.69\",\"18.87\",\"411471.00\"],[\"2016/12/21\",\"19.00\",\"19.34\",\"19.87\",\"18.71\",\"411641.00\"],[\"2016/12/22\",\"19.40\",\"19.85\",\"19.89\",\"19.08\",\"343137.00\"],[\"2016/12/23\",\"19.98\",\"19.07\",\"19.99\",\"19.00\",\"315581.00\"],[\"2016/12/24\",\"18.95\",\"19.06\",\"19.35\",\"18.72\",\"237126.00\"],[\"2016/12/25\",\"19.11\",\"19.17\",\"19.38\",\"18.93\",\"163681.00\"],[\"2016/12/28\",\"19.17\",\"18.45\",\"19.35\",\"18.45\",\"229227.00\"],[\"2016/12/29\",\"18.55\",\"19.18\",\"19.35\",\"18.22\",\"212629.00\"],[\"2016/12/30\",\"19.20\",\"19.14\",\"19.35\",\"18.83\",\"207808.00\"],[\"2016/12/31\",\"19.16\",\"18.68\",\"19.20\",\"18.60\",\"162437.00\"],[\"2017/01/04\",\"18.81\",\"17.05\",\"18.81\",\"16.90\",\"180120.00\"],[\"2017/01/05\",\"16.20\",\"16.75\",\"18.03\",\"15.80\",\"272718.00\"],[\"2017/01/06\",\"16.90\",\"16.94\",\"17.08\",\"16.43\",\"198058.00\"],[\"2017/01/07\",\"16.61\",\"15.27\",\"16.74\",\"15.25\",\"63479.00\"],[\"2017/01/08\",\"15.60\",\"15.26\",\"15.94\",\"14.63\",\"289668.00\"],[\"2017/01/11\",\"14.90\",\"13.97\",\"15.30\",\"13.91\",\"208439.00\"],[\"2017/01/12\",\"14.05\",\"14.14\",\"14.28\",\"13.79\",\"198535.00\"],[\"2017/01/13\",\"14.25\",\"13.78\",\"14.65\",\"13.71\",\"183315.00\"],[\"2017/01/14\",\"13.35\",\"14.28\",\"14.35\",\"13.27\",\"194797.00\"],[\"2017/01/15\",\"14.26\",\"13.84\",\"14.54\",\"13.67\",\"211021.00\"],[\"2017/01/18\",\"13.48\",\"13.97\",\"14.28\",\"13.37\",\"129268.00\"],[\"2017/01/19\",\"13.96\",\"14.56\",\"14.66\",\"13.80\",\"223909.00\"],[\"2017/01/20\",\"14.48\",\"14.30\",\"14.60\",\"14.21\",\"142733.00\"],[\"2017/01/21\",\"14.06\",\"13.60\",\"14.40\",\"13.60\",\"129618.00\"],[\"2017/01/22\",\"13.80\",\"13.59\",\"13.89\",\"13.20\",\"189615.00\"],[\"2017/01/25\",\"13.74\",\"13.82\",\"13.95\",\"13.61\",\"125423.00\"],[\"2017/01/26\",\"13.66\",\"12.53\",\"13.77\",\"12.50\",\"168538.00\"],[\"2017/01/27\",\"12.56\",\"12.37\",\"12.70\",\"11.44\",\"347611.00\"],[\"2017/01/28\",\"12.03\",\"11.84\",\"12.47\",\"11.71\",\"173067.00\"],[\"2017/01/29\",\"11.90\",\"12.36\",\"12.46\",\"11.86\",\"160726.00\"],[\"2017/02/01\",\"12.15\",\"12.02\",\"12.36\",\"11.83\",\"122485.00\"],[\"2017/02/02\",\"12.02\",\"12.52\",\"12.56\",\"12.02\",\"139139.00\"],[\"2017/02/03\",\"12.50\",\"12.53\",\"12.62\",\"12.31\",\"116326.00\"],[\"2017/02/04\",\"12.74\",\"12.79\",\"13.00\",\"12.64\",\"169624.00\"],[\"2017/02/05\",\"12.83\",\"12.67\",\"12.90\",\"12.66\",\"93049.00\"],[\"2017/02/15\",\"12.10\",\"12.30\",\"12.47\",\"12.10\",\"107412.00\"],[\"2017/02/16\",\"12.45\",\"12.85\",\"12.87\",\"12.43\",\"181297.00\"],[\"2017/02/17\",\"12.96\",\"13.30\",\"13.38\",\"12.96\",\"206638.00\"],[\"2017/02/18\",\"13.42\",\"13.95\",\"14.22\",\"13.27\",\"350170.00\"],[\"2017/02/19\",\"14.00\",\"13.70\",\"14.00\",\"13.60\",\"261421.00\"],[\"2017/02/22\",\"13.81\",\"14.00\",\"14.33\",\"13.80\",\"298902.00\"],[\"2017/02/23\",\"14.00\",\"13.81\",\"14.01\",\"13.57\",\"141709.00\"],[\"2017/02/24\",\"13.65\",\"13.82\",\"13.95\",\"13.41\",\"138151.00\"],[\"2017/02/25\",\"13.80\",\"12.45\",\"13.80\",\"12.44\",\"220304.00\"],[\"2017/02/26\",\"12.61\",\"12.63\",\"12.85\",\"12.44\",\"157598.00\"],[\"2017/02/29\",\"12.63\",\"12.20\",\"12.63\",\"11.82\",\"167929.00\"],[\"2017/03/01\",\"12.23\",\"12.76\",\"12.90\",\"12.07\",\"246822.00\"],[\"2017/03/02\",\"12.72\",\"14.04\",\"14.04\",\"12.72\",\"287224.00\"],[\"2017/03/03\",\"14.90\",\"14.95\",\"15.44\",\"14.76\",\"870902.00\"],[\"2017/03/04\",\"15.09\",\"14.69\",\"15.58\",\"14.33\",\"699604.00\"],[\"2017/03/07\",\"14.76\",\"14.71\",\"15.50\",\"14.50\",\"512004.00\"],[\"2017/03/08\",\"14.51\",\"14.68\",\"14.84\",\"14.05\",\"331510.00\"],[\"2017/03/09\",\"14.15\",\"14.15\",\"14.45\",\"14.01\",\"231897.00\"],[\"2017/03/10\",\"14.23\",\"13.70\",\"14.33\",\"13.68\",\"193441.00\"],[\"2017/03/11\",\"13.42\",\"13.47\",\"13.70\",\"13.10\",\"174475.00\"],[\"2017/03/14\",\"13.56\",\"14.54\",\"14.82\",\"13.56\",\"399240.00\"],[\"2017/03/15\",\"14.39\",\"14.23\",\"14.74\",\"14.10\",\"305928.00\"],[\"2017/03/16\",\"14.32\",\"14.11\",\"14.40\",\"14.00\",\"143571.00\"],[\"2017/03/17\",\"14.17\",\"14.91\",\"15.00\",\"14.17\",\"269213.00\"],[\"2017/03/18\",\"14.92\",\"15.50\",\"15.62\",\"14.85\",\"375522.00\"],[\"2017/03/21\",\"15.60\",\"15.65\",\"16.21\",\"15.58\",\"317073.00\"],[\"2017/03/22\",\"15.40\",\"15.79\",\"16.40\",\"15.31\",\"287952.00\"],[\"2017/03/23\",\"15.91\",\"15.92\",\"16.03\",\"15.60\",\"164341.00\"],[\"2017/03/24\",\"15.60\",\"15.55\",\"16.08\",\"15.50\",\"202585.00\"],[\"2017/03/25\",\"15.53\",\"15.74\",\"15.88\",\"15.45\",\"111713.00\"],[\"2017/03/28\",\"15.74\",\"15.70\",\"16.25\",\"15.65\",\"194782.00\"],[\"2017/03/29\",\"15.75\",\"16.30\",\"16.38\",\"15.53\",\"301944.00\"],[\"2017/03/30\",\"16.29\",\"17.57\",\"17.93\",\"16.11\",\"433027.00\"],[\"2017/03/31\",\"17.40\",\"17.08\",\"17.55\",\"17.04\",\"246606.00\"],[\"2017/04/01\",\"17.09\",\"17.55\",\"17.60\",\"16.95\",\"216661.00\"],[\"2017/04/05\",\"17.46\",\"17.55\",\"17.96\",\"17.27\",\"218921.00\"],[\"2017/04/06\",\"17.40\",\"17.20\",\"17.57\",\"16.82\",\"217841.00\"],[\"2017/04/07\",\"17.21\",\"16.91\",\"17.38\",\"16.91\",\"149523.00\"],[\"2017/04/08\",\"16.59\",\"16.45\",\"16.80\",\"16.33\",\"137207.00\"],[\"2017/04/11\",\"16.69\",\"17.55\",\"17.97\",\"16.67\",\"217821.00\"],[\"2017/04/12\",\"17.54\",\"17.21\",\"17.96\",\"17.14\",\"155616.00\"],[\"2017/04/13\",\"17.19\",\"17.31\",\"17.91\",\"17.17\",\"196837.00\"],[\"2017/04/14\",\"17.20\",\"17.22\",\"17.50\",\"16.95\",\"121346.00\"],[\"2017/04/15\",\"17.25\",\"17.13\",\"17.31\",\"16.96\",\"80561.00\"],[\"2017/04/18\",\"17.12\",\"16.35\",\"17.12\",\"16.30\",\"157622.00\"],[\"2017/04/19\",\"16.52\",\"16.38\",\"16.70\",\"16.19\",\"86750.00\"],[\"2017/04/20\",\"16.43\",\"15.67\",\"16.55\",\"15.27\",\"182457.00\"],[\"2017/04/21\",\"15.56\",\"15.45\",\"15.86\",\"15.18\",\"67845.00\"],[\"2017/04/22\",\"15.42\",\"15.70\",\"15.78\",\"15.12\",\"159974.00\"],[\"2017/04/25\",\"15.77\",\"15.55\",\"15.98\",\"15.40\",\"150715.00\"],[\"2017/04/26\",\"15.60\",\"15.70\",\"15.74\",\"15.46\",\"89126.00\"],[\"2017/04/27\",\"15.71\",\"15.49\",\"15.83\",\"15.40\",\"108200.00\"],[\"2017/04/28\",\"15.45\",\"15.24\",\"15.56\",\"14.99\",\"144103.00\"],[\"2017/04/29\",\"15.18\",\"15.30\",\"15.43\",\"15.07\",\"76883.00\"],[\"2017/05/03\",\"15.28\",\"15.91\",\"16.06\",\"15.18\",\"124344.00\"],[\"2017/05/04\",\"15.78\",\"15.69\",\"16.00\",\"15.56\",\"150615.00\"],[\"2017/05/05\",\"15.40\",\"15.74\",\"15.79\",\"15.40\",\"95713.00\"],[\"2017/05/06\",\"15.78\",\"15.11\",\"15.87\",\"15.10\",\"139890.00\"],[\"2017/05/09\",\"14.91\",\"14.33\",\"15.00\",\"14.31\",\"161207.00\"],[\"2017/05/10\",\"14.34\",\"14.30\",\"14.52\",\"14.20\",\"72236.00\"],[\"2017/05/11\",\"14.45\",\"14.30\",\"14.47\",\"14.21\",\"82662.00\"],[\"2017/05/12\",\"14.14\",\"14.20\",\"14.35\",\"13.97\",\"94724.00\"],[\"2017/05/13\",\"14.21\",\"14.21\",\"14.48\",\"14.10\",\"73611.00\"],[\"2017/05/16\",\"14.22\",\"14.37\",\"14.37\",\"14.00\",\"82875.00\"],[\"2017/05/17\",\"14.40\",\"14.40\",\"14.62\",\"14.27\",\"88418.00\"],[\"2017/05/18\",\"14.30\",\"14.09\",\"14.30\",\"13.64\",\"134740.00\"],[\"2017/05/19\",\"14.05\",\"13.99\",\"14.22\",\"13.91\",\"68580.00\"],[\"2017/05/20\",\"13.86\",\"14.08\",\"14.10\",\"13.78\",\"66820.00\"],[\"2017/05/23\",\"14.08\",\"14.14\",\"14.25\",\"14.00\",\"79913.00\"],[\"2017/05/24\",\"14.15\",\"14.09\",\"14.25\",\"14.04\",\"92120.00\"],[\"2017/05/25\",\"14.20\",\"14.08\",\"14.29\",\"13.98\",\"74598.00\"],[\"2017/05/26\",\"14.08\",\"14.16\",\"14.18\",\"13.67\",\"79067.00\"],[\"2017/05/27\",\"14.09\",\"14.02\",\"14.19\",\"13.98\",\"61079.00\"],[\"2017/05/30\",\"14.12\",\"14.60\",\"14.70\",\"14.12\",\"177216.00\"],[\"2017/05/31\",\"14.69\",\"15.16\",\"15.26\",\"14.55\",\"261970.00\"],[\"2017/06/01\",\"15.10\",\"15.24\",\"15.57\",\"15.01\",\"229556.00\"],[\"2017/06/02\",\"15.25\",\"15.80\",\"15.97\",\"15.25\",\"286928.00\"],[\"2017/06/03\",\"10.40\",\"10.21\",\"10.63\",\"10.16\",\"330628.00\",{\"nd\":\"2016\",\"fh_sh\":\"2\",\"djr\":\"2017/06/02\",\"cqr\":\"2017/06/03\",\"FHcontent\":\"10\\u6d3e2\\u5143\\u90015\\u80a1\"}],[\"2017/06/06\",\"10.22\",\"10.23\",\"10.58\",\"10.08\",\"210343.00\"]," +
            "[\"2017/06/07\",\"10.24\",\"10.22\",\"10.32\",\"10.10\",\"163159.00\"]," +
            "[\"2017/06/08\",\"10.19\",\"10.04\",\"10.21\",\"9.91\",\"254633.00\"]," +
            "[\"2017/06/13\",\"10.04\",\"9.86\",\"10.26\",\"9.80\",\"299741.00\"]]," +
            "\"qt\":{\"sz002081\":[\"51\",\"\\u91d1 \\u87b3 \\u8782\",\"002081\",\"9.86\",\"10.04\",\"10.04\",\"299741\",\"143765\",\"155976\",\"9.85\",\"1280\",\"9.84\",\"226\",\"9.83\",\"164\",\"9.82\",\"680\",\"9.81\",\"697\",\"9.86\",\"430\",\"9.87\",\"321\",\"9.88\",\"701\",\"9.89\",\"607\",\"9.90\",\"281\",\"15:00:01\\/9.86\\/3535\\/S\\/3485510\\/33300|14:57:01\\/9.86\\/101\\/B\\/99559\\/32990|14:56:58\\/9.86\\/18\\/B\\/17743\\/32981|14:56:52\\/9.86\\/73\\/S\\/72481\\/32970|14:56:49\\/9.86\\/38\\/S\\/36980\\/32964|14:56:46\\/9.87\\/64\\/B\\/63108\\/32959\",\"20170613150137\",\"-0.18\",\"-1.79\",\"10.26\",\"9.80\",\"9.86\\/296206\\/298840720\",\"299741\",\"30233\",\"1.19\",\"15.99\",\"\",\"10.26\",\"9.80\",\"4.58\",\"248.32\",\"260.63\",\"1.91\",\"11.04\",\"9.04\",\"\"],\"market\":[\"2017/06/13 16:11:01|HK_close_\\u5df2\\u6536\\u76d8|SH_close_\\u5df2\\u6536\\u76d8|SZ_close_\\u5df2\\u6536\\u76d8|US_close_\\u672a\\u5f00\\u76d8|SQ_close_\\u5df2\\u4f11\\u5e02|DS_close_\\u5df2\\u4f11\\u5e02|ZS_close_\\u5df2\\u4f11\\u5e02\"],\"zjlx\":[\"sz002081\",\"13808.61\",\"16272.41\",\"-2463.80\",\"-8.15\",\"16424.02\",\"13960.23\",\"2463.79\",\"8.15\",\"30232.63\",\"53095.67\",\"61220.51\",\"\\u91d1 \\u87b3 \\u8782\",\"20170613\",\"20170608^8921.92^12669.59\",\"20170607^7605.27^7386.58\",\"20170606^7653.72^9006.39\",\"20170603^15106.15^15885.54\"]},\"mx_price\":{\"mx\":[],\"price\":[]},\"prec\":\"19.15\",\"version\":\"4\"}}}";
}

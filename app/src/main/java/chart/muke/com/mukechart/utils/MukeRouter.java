package chart.muke.com.mukechart.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * @Annotation <p>页面路由工具类</p>
 * @Auth Sunny
 * @date 2017/9/12
 * @Version V1.0.0
 */

public class MukeRouter {

    public static void jump2target(@NonNull Context context, @NonNull Class<?> target){
        Intent intent = new Intent(context,target);
        context.startActivity(intent);
    }
}

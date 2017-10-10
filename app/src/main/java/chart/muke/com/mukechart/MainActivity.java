package chart.muke.com.mukechart;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chart.muke.com.mukechart.actualchart.MojiAppActivity;
import chart.muke.com.mukechart.basicchart.BarChartActivity;
import chart.muke.com.mukechart.basicchart.CurveChartActivity;
import chart.muke.com.mukechart.basicchart.KChartActivity;
import chart.muke.com.mukechart.basicchart.LineChartActivity;
import chart.muke.com.mukechart.basicchart.PieChartActivity;
import chart.muke.com.mukechart.basicchart.RadarChartActivity;
import chart.muke.com.mukechart.basicgraph.SimpleColumnActivity;
import chart.muke.com.mukechart.basicgraph.SimpleCurveActivity;
import chart.muke.com.mukechart.basicgraph.SimpleGraphActivity;
import chart.muke.com.mukechart.utils.MukeRouter;

public class MainActivity extends ListActivity {


    private List<? extends Map<String, ?>> mDatas = new ArrayList<>();
    private String[] mFrom;
    private int[] mTo;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(android.R.id.list);
        initParams();
        mListView.setAdapter(new ArrayAdapter<String >(this,android.R.layout.simple_list_item_single_choice,mFrom));
    }

    private void initParams() {
        mFrom = getResources().getStringArray(R.array.chart_types);
        mTo = new int[]{R.id.tv_item_func};
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, BarChartActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        MukeRouter.jump2target(MainActivity.this, CurveChartActivity.class);
                        break;
                    case 2:
                        MukeRouter.jump2target(MainActivity.this, LineChartActivity.class);
                        break;
                    case 3:
                        MukeRouter.jump2target(MainActivity.this, KChartActivity.class);
                        break;
                    case 4:
                        MukeRouter.jump2target(MainActivity.this, PieChartActivity.class);
                        break;
                    case 5:
                        MukeRouter.jump2target(MainActivity.this, RadarChartActivity.class);
                        break;
                    case 6:
                        MukeRouter.jump2target(MainActivity.this, SimpleGraphActivity.class);
                        break;
                    case 7:
                        MukeRouter.jump2target(MainActivity.this, SimpleColumnActivity.class);
                        break;
                    case 8:
                        MukeRouter.jump2target(MainActivity.this, SimpleCurveActivity.class);
                        break;
                    case 9:
                        MukeRouter.jump2target(MainActivity.this, MojiAppActivity.class);
                        break;
                        default:
                            break;
                }
            }
        });
    }
}

package chart.muke.com.mukechart;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chart.muke.com.mukechart.basicchart.BarChartActivity;

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
                        default:
                            break;
                }
                Toast.makeText(MainActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

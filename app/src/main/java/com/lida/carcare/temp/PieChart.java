package com.lida.carcare.temp;

import android.graphics.Color;
import android.os.Bundle;
import com.apkfuns.logutils.LogUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 饼状图
 * Created by Administrator on 2017/3/21.
 */

public class PieChart extends BaseActivity {
    @BindView(R.id.chart)
    com.github.mikephil.charting.charts.PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);
        ButterKnife.bind(this);
//        pieChart.setDescription("平均客单价");
//        pieChart.setDescriptionTextSize(40f);
        pieChart.setTransparentCircleRadius(0f); // 半透明圈
        pieChart.setHoleRadius(60f);  //实心圆

        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(-90); // 初始旋转角度

        pieChart.setRotationEnabled(false); // 可以手动旋转
        pieChart.setUsePercentValues(false);  //显示成百分比
        pieChart.setCenterText("A级 73.0\n\n3.0%");  //饼状图中间的文字

        pieChart.setHoleColor(Color.parseColor("#ffff00")); //实心圆背景
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        List<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(3.0f));
        data.add(new PieEntry(97.0f));
        PieDataSet pieDataSet = new PieDataSet(data, "Hello");
        pieDataSet.setValueTextColor(Color.parseColor("#00FFFFFF"));
        pieDataSet.setColors(new int[]{Color.parseColor("#FF0000"), Color.parseColor("#00FF00")});
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                LogUtils.e(h.getX());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}


























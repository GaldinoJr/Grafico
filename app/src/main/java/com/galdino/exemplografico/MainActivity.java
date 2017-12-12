package com.galdino.exemplografico;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewTreeObserver;

import com.galdino.exemplografico.adapter.MonthListAdapter;
import com.galdino.exemplografico.databinding.ActivityMainBinding;
import com.galdino.exemplografico.domain.Month;
import com.galdino.exemplografico.domain.MonthList;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private int mMeasuredWidth;
    private MonthList mMonthList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initializeGraph();
        initializeMonthList();
        //
    }

    private void initializeMonthList() {
        mMeasuredWidth = mBinding.getRoot().getMeasuredWidth();
        if (mMeasuredWidth > 0) {
            inflateMonthList();
        }
        else
        {
            ViewTreeObserver vto = mBinding.getRoot().getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    mBinding.getRoot().getViewTreeObserver().removeOnPreDrawListener(this);
                    mMeasuredWidth = mBinding.getRoot().getMeasuredWidth();
                    inflateMonthList();
                    return true;
                }
            });
        }
    }

    private void inflateMonthList()
    {
        mMonthList = new MonthList(this);
        mMonthList.getFirstListMonths().get(0).setSelected(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);

        MonthListAdapter monthListAdapter = new MonthListAdapter(mMeasuredWidth,mMonthList.getFirstListMonths());
        monthListAdapter.setListener(new MonthListAdapter.Listener() {
            @Override
            public void onItemClicked(Month month) {
                monthListAdapter.disableItemsMenu();
                monthListAdapter.notifyDataSetChanged();
                month.setSelected(true);
            }
        });
        mBinding.rvMonth.setLayoutManager(linearLayoutManager);
        mBinding.rvMonth.setAdapter(monthListAdapter);
    }

    private void initializeGraph() {
        setData();
        // style chart
//        lineChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
//        lineChart.setDrawGridBackground(false);// this is a must
//        lineChart.setBackgroundColor(getResources().getColor(android.R.color.white)); // use your bg color
//        lineChart.setDrawGridBackground(false);
//        lineChart.setDrawBorders(false);
//        lineChart.setAutoScaleMinMaxEnabled(true);
        // Desliga legenda
        Legend legend = mBinding.lineChart.getLegend();
        legend.setEnabled(false);
        // Desliga descrição
        mBinding.lineChart.getDescription().setEnabled(false);

        // remove axis
        YAxis leftAxis = mBinding.lineChart.getAxisLeft();
        leftAxis.setEnabled(false);
        YAxis rightAxis = mBinding.lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        XAxis xAxis = mBinding.lineChart.getXAxis();
        xAxis.setEnabled(false);
        // cor do gráfico
        // Animação
        mBinding.lineChart.animateXY(2000, 2000);
        mBinding.lineChart.invalidate();
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals= new ArrayList<>();
        yVals.add(new Entry(0, 68));
        yVals.add(new Entry(1, 48));
        yVals.add(new Entry(2, 90.5f));
        yVals.add(new Entry(3, 70));

        return yVals;
    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");

        return xVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");
        set1.setFillAlpha(110);

        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);


//        set1.setDrawValues(false);
//        set1.setLineWidth(2f);
//        set1.setDrawCircles(false);

        int circleRadiusDimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.circle_radius);
        set1.setCircleRadius(circleRadiusDimensionPixelSize);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData( dataSets);
        mBinding.lineChart.setData(data);
    }
}

package com.galdino.exemplografico;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart lineChart = findViewById(R.id.line_chart);
        //
        setData(lineChart);
        // style chart
//        lineChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
//        lineChart.setDrawGridBackground(false);// this is a must
//        lineChart.setBackgroundColor(getResources().getColor(android.R.color.white)); // use your bg color
//        lineChart.setDrawGridBackground(false);
//        lineChart.setDrawBorders(false);
//        lineChart.setAutoScaleMinMaxEnabled(true);
        // Desliga legenda
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        // Desliga descrição
        lineChart.getDescription().setEnabled(false);

        // remove axis
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setEnabled(false);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        // cor do gráfico
        // Animação
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals= new ArrayList<>();
        yVals.add(new Entry(0, 68));
        yVals.add(new Entry(1, 48));
        yVals.add(new Entry(2, 70.5f));
        yVals.add(new Entry(3, 100));
        yVals.add(new Entry(4, 30f));

        return yVals;
    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }

    private void setData(LineChart lineChart) {
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
        lineChart.setData(data);
    }
}

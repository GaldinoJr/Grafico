package com.galdino.exemplografico;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;



/**
 * Created by galdino on 11/12/17.
 */

public class Chart extends LineChart
{
    public static final int SENT_MODE = 0;
    public static final int ACCEPTED_MODE = 1;
    private LineData mLineData;
    private boolean mPutZoom;

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        setDescription(null);
        getLegend().setEnabled(false);

        int white = ContextCompat.getColor(getContext(), android.R.color.white);
        getAxisLeft().setTextColor(white);
        getAxisRight().setEnabled(false);

        XAxis xAxis = getXAxis();
        xAxis.setTextColor(white);
        xAxis.setValueFormatter((value, axis) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.US);
            return simpleDateFormat.format(new Date((long) value));
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        mLineData = new LineData();
    }

    public void addData(List<GraphPoint> graphPointList, int mode, int colorResourceId) {
        List<Entry> entries = getEntriesByTotalAmountList(graphPointList, mode);
        Context context = getContext();

        int color = ContextCompat.getColor(context, colorResourceId);
        int circleRadiusDimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.circle_radius);
        int lineWidthDimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.line_width);
        int transparent = ContextCompat.getColor(context, android.R.color.transparent);

        LineDataSet dataSet = new LineDataSet(entries, null);
        dataSet.setValueTextColor(transparent);
        dataSet.setCircleRadius(circleRadiusDimensionPixelSize);
        dataSet.setLineWidth(lineWidthDimensionPixelSize);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setDrawCircleHole(false);

        mLineData.addDataSet(dataSet);
        setData(mLineData);

        int count = entries.size() / 6;
        XAxis xAxis = getXAxis();
        xAxis.setLabelCount(count, true);
        if (!mPutZoom) {
            zoom(count, 1, 0, 0);
            setPinchZoom(false);
            setDoubleTapToZoomEnabled(false);
            mPutZoom = true;
        }
    }

    private static List<Entry> getEntriesByTotalAmountList(List<GraphPoint> graphPoints, int mode) {
        List<Entry> entries = new LinkedList<>();
        if (graphPoints != null) {
            for (GraphPoint data : graphPoints) {
                // turn your data into Entry objects
                long timeInMillis = data.getDateInMillis();
                float amount = getAmount(data, mode);
                Entry entry = new Entry(timeInMillis, amount);
                entries.add(entry);
            }
        }
        return entries;
    }

    private static float getAmount(GraphPoint data, int mode) {
        if (mode == ACCEPTED_MODE) {
            return (float) data.getAccepted();
        }
        return (float) data.getSent();
    }
}

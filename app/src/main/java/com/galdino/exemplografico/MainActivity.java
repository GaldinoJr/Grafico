package com.galdino.exemplografico;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewTreeObserver;

import com.galdino.exemplografico.adapter.DataMonthListAdapter;
import com.galdino.exemplografico.adapter.MonthListAdapter;
import com.galdino.exemplografico.databinding.ActivityMainBinding;
import com.galdino.exemplografico.domain.Month;
import com.galdino.exemplografico.domain.MonthList;
import com.galdino.exemplografico.domain.dataMonth.TipoEntradaSaida;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        initializeDataMonth();
        //
    }

    private void initializeDataMonth()
    {
        List<TipoEntradaSaida> listEntradaSaida = loadListEntradaSaida();
        DataMonthListAdapter adapter = new DataMonthListAdapter(listEntradaSaida);
        mBinding.rvDataMonth.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvDataMonth.setAdapter(adapter);
    }

    private List<TipoEntradaSaida> loadListEntradaSaida() {
        List<TipoEntradaSaida> listEntradaSaida = new LinkedList<>();
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Crédito","15000.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Débito","15000.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Dinheiro","15000.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Bom","15000.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Pré","15000.50"));
        return listEntradaSaida;
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
//        getResources().getColor(R.drawable.line_color_background);
//        set1.setFillDrawable(getResources().getDrawable(R.drawable.line_color_background));
//        set1.setColor(Color.BLACK);
//        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(2f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        // ativar o fundo
//        set1.setDrawFilled(true);
        // cor em degrade da linha
        Paint paint = mBinding.lineChart.getRenderer().getPaintRender();
        int height = 200;

        LinearGradient linGrad = new LinearGradient(0, 0, 0, height,
                getResources().getColor(R.color.lineGraphHigh),
                getResources().getColor(R.color.lineGraphLow),
                Shader.TileMode.CLAMP);
        paint.setShader(linGrad);


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

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
import com.galdino.exemplografico.domain.dataMonth.DadosPrevisao;
import com.galdino.exemplografico.domain.dataMonth.ObjectFinantialValues;
import com.galdino.exemplografico.domain.dataMonth.Resumo;
import com.galdino.exemplografico.domain.dataMonth.TipoEntradaSaida;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private int mMeasuredWidth;
    private MonthList mMonthList;
    private ObjectFinantialValues objectFinantialValues;
    private int mMonthInitial;
    private int mIndexMonthSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initializeMockedData();
        initializeIndexGraph();
        initializeGraph();
        initializeMonthList();
        initializeDataMonth();
        //
    }

    private void initializeIndexGraph()
    {
        mMonthInitial = 1;
        if(mIndexMonthSelected > 4)
        {
            if(mIndexMonthSelected >8)
            {
                mMonthInitial = 9;
            }
            else
            {
                mMonthInitial = 5;
            }
        }
    }

    private void initializeMockedData()
    {
        mIndexMonthSelected = 0;
        objectFinantialValues = new ObjectFinantialValues();
        DadosPrevisao dadosPrevisao = new DadosPrevisao();

        List<Resumo> summaryList = new LinkedList<>();
        for(int i = 0; i < 12; i++)
        {
            List<TipoEntradaSaida> listEntradaSaida = loadMockedListEntradaSaida(i);
            Resumo resumo = new Resumo();
            resumo.setMes(String.valueOf(i));
            resumo.setTotal(String.valueOf((i+1)*2));
            resumo.setTipoEs(listEntradaSaida);
            summaryList.add(resumo);
        }
        dadosPrevisao.setResumo(summaryList);
        objectFinantialValues.setDadosPrevisao(dadosPrevisao);
    }

    private void initializeDataMonth()
    {
        Month monthSelected = new Month(mMonthInitial);
        selectedMonthList(monthSelected , mMonthInitial);
    }

    private void selectedMonthList(Month selected, int monthSelected)
    {
        monthSelected = monthSelected -1;
        mBinding.tvMonthSelected.setText(selected.getCompleteMonthName());
        if(objectFinantialValues == null ||  objectFinantialValues.getDadosPrevisao() == null||
                objectFinantialValues.getDadosPrevisao().getResumo() == null||
                objectFinantialValues.getDadosPrevisao().getResumo().get(monthSelected) == null ||
                objectFinantialValues.getDadosPrevisao().getResumo().get(monthSelected).getTipoEs() == null)
        {
            return;
        }
        List<TipoEntradaSaida> listEntradaSaida = objectFinantialValues.getDadosPrevisao().getResumo().get(monthSelected).getTipoEs();
        DataMonthListAdapter adapter = new DataMonthListAdapter(listEntradaSaida);
        mBinding.rvDataMonth.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvDataMonth.setAdapter(adapter);
    }

    private List<TipoEntradaSaida> loadMockedListEntradaSaida(int index) {
        List<TipoEntradaSaida> listEntradaSaida = new LinkedList<>();
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Crédito","1500" + String.valueOf(index + 1) + "0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Débito","1500" + String.valueOf(index + 1) + "0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Dinheiro","1500" + String.valueOf(index + 1) +"0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Bom","1500" + String.valueOf(index + 1) +"0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Pré","1500" + String.valueOf(index + 1) +"0.50"));
        return listEntradaSaida;
    }

    private void initializeMonthList() {
        mMeasuredWidth = mBinding.getRoot().getMeasuredWidth();
        if (mMeasuredWidth > 0) {
            inflateMonthList(0);
        }
        else
        {
            ViewTreeObserver vto = mBinding.getRoot().getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    mBinding.getRoot().getViewTreeObserver().removeOnPreDrawListener(this);
                    mMeasuredWidth = mBinding.getRoot().getMeasuredWidth();
                    inflateMonthList(0);
                    return true;
                }
            });
        }
    }

    private void inflateMonthList(int indexPag)
    {
        mMonthList = new MonthList(this);
        List<Month> monthList = null;
        switch (indexPag)
        {
            case 0:
                monthList = mMonthList.getFirstListMonths();
                break;
            case 1:
                monthList = mMonthList.getSecondListMonths();
                break;
            case 2:
                monthList = mMonthList.getThirdListMonths();
                break;
        }
        if(monthList  != null) {
            monthList.get(indexPag).setSelected(true);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            MonthListAdapter monthListAdapter = new MonthListAdapter(mMeasuredWidth, mMonthList.getFirstListMonths());
            monthListAdapter.setListener(new MonthListAdapter.Listener() {
                @Override
                public void onItemClicked(Month month) {
                    if (month != null) {
                        monthListAdapter.disableItemsMenu();
                        monthListAdapter.notifyDataSetChanged();
                        month.setSelected(true);
                        mMonthInitial = month.getMonthNumeric();
                        selectedMonthList(month, mMonthInitial);
                    }
                }
            });
            mBinding.rvMonth.setLayoutManager(linearLayoutManager);
            mBinding.rvMonth.setAdapter(monthListAdapter);
        }
    }
    // region Graph
    private void initializeGraph() {
        setData();
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

    private ArrayList<Entry> setYAxisValues()
    {
        if(objectFinantialValues == null || objectFinantialValues.getDadosPrevisao() == null || objectFinantialValues.getDadosPrevisao().getResumo() == null)
        {
            return null;
        }
        List<Resumo> summaryList = objectFinantialValues.getDadosPrevisao().getResumo();
        ArrayList<Entry> yVals= new ArrayList<>();
        Collections.sort(summaryList, new Comparator<Resumo>() {
            @Override
            public int compare(Resumo resumo1, Resumo resumo2) {
                return resumo1.getMesInteger() - resumo2.getMesInteger();
            }
        });


        for(int j = mMonthInitial; j < mMonthInitial + 4; j++)
        {
            try
            {
                yVals.add(new Entry(j, Float.parseFloat(summaryList.get(j).getTotal())));
            }
            catch (Exception ex){}
        }

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
//        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();
        if(yVals == null || yVals.size() < 1)
        {
            return;
        }
        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");
        set1.setFillAlpha(110);
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
                getResources().getColor(R.color.colorBlueDarkGraph),
                Shader.TileMode.CLAMP);
        paint.setShader(linGrad);
        int circleRadiusDimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.circle_radius);
        set1.setCircleRadius(circleRadiusDimensionPixelSize);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData( dataSets);
        mBinding.lineChart.setData(data);
    }
    //endregion
}

package com.galdino.exemplografico.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.galdino.exemplografico.R;
import com.galdino.exemplografico.adapter.DataMonthListAdapter;
import com.galdino.exemplografico.adapter.MonthListAdapter;
import com.galdino.exemplografico.databinding.FragmentFinantialGraphBinding;
import com.galdino.exemplografico.domain.Month;
import com.galdino.exemplografico.domain.MonthList;
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
import java.util.List;

public class FinancialGraphFragment extends Fragment {

    private static final String ARG_MONTH_SELECTED = "ARG_MONTH_SELECTED";
    private static final String ARG_INDEX_MONTH_SELECTED = "ARG_INDEX_MONTH_SELECTED";
    private static final String ARG_MONTHS = "ARG_MONTHS";
    private static final String ARG_INDEX_PAGE = "ARG_INDEX_PAGE";
    private FragmentFinantialGraphBinding mBinding;
    //
    private int mIndexMonthSelected;
    private int mMonthSelected;
    private int mIndexPage;
    private List<Resumo> mSummaryList;
    //
    private int mMeasuredWidth;
    private MonthList mMonthList;

    public static FinancialGraphFragment newInstance(int indexPage, int indexMonthSelected, int monthSelected, List<Resumo> summaryList) {
        FinancialGraphFragment fragment = new FinancialGraphFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX_MONTH_SELECTED, indexMonthSelected);
        args.putInt(ARG_MONTH_SELECTED, monthSelected);
        args.putInt(ARG_INDEX_PAGE, indexPage);
        args.putParcelableArrayList(ARG_MONTHS, new ArrayList<>(summaryList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mIndexMonthSelected = getArguments().getInt(ARG_INDEX_MONTH_SELECTED);
            mMonthSelected = getArguments().getInt(ARG_MONTH_SELECTED);
            mIndexPage = getArguments().getInt(ARG_INDEX_PAGE);
            mSummaryList = getArguments().getParcelableArrayList(ARG_MONTHS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_finantial_graph, container, false);
        mMonthList = new MonthList(getContext());
        initializeGraph();
        initializeMonthList();
        initializeDataMonth();
        return mBinding.getRoot();
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
    private void setData()
    {
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

    private ArrayList<Entry> setYAxisValues()
    {
        ArrayList<Entry> yVals= new ArrayList<>();
        Collections.sort(mSummaryList, new Comparator<Resumo>() {
            @Override
            public int compare(Resumo resumo1, Resumo resumo2) {
                return resumo1.getMesInteger() - resumo2.getMesInteger();
            }
        });


        for(int j = 0; j < mSummaryList.size(); j++)
        {
            try
            {
                yVals.add(new Entry(j, Float.parseFloat(mSummaryList.get(j).getTotal())));
            }
            catch (Exception ex){}
        }

        return yVals;
    }
    //endregion
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
        List<Month> monthList = null;
        switch (mIndexPage)
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
        if(monthList != null) {
            monthList.get(mIndexMonthSelected).setSelected(true);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            MonthListAdapter monthListAdapter = new MonthListAdapter(mMeasuredWidth, monthList);
            monthListAdapter.setListener(new MonthListAdapter.Listener() {
                @Override
                public void onItemClicked(Month month) {
                    if (month != null && !month.isSelected()) {
                        monthListAdapter.disableItemsMenu();
                        monthListAdapter.notifyDataSetChanged();
                        month.setSelected(true);
                        selectedMonthList(month, month.getIndex());
                    }
                }
            });
            mBinding.rvMonth.setLayoutManager(linearLayoutManager);
            mBinding.rvMonth.setAdapter(monthListAdapter);
        }
    }
    private void selectedMonthList(Month selected, int monthSelected)
    {
        mBinding.tvMonthSelected.setText(selected.getCompleteMonthName());
        if(mSummaryList == null ||  mSummaryList.get(monthSelected) == null)
        {
            return;
        }
        List<TipoEntradaSaida> listEntradaSaida = mSummaryList.get(monthSelected).getTipoEs();
        DataMonthListAdapter adapter = new DataMonthListAdapter(listEntradaSaida);
        mBinding.rvDataMonth.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvDataMonth.setAdapter(adapter);
    }

    private void initializeDataMonth()
    {
        Month monthSelected = new Month(mMonthSelected);
        selectedMonthList(monthSelected , mIndexMonthSelected);
    }
}

package com.galdino.exemplografico.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.galdino.exemplografico.domain.dataMonth.Resumo;
import com.galdino.exemplografico.fragment.FinancialGraphFragment;

import java.util.List;

/**
 * Created by galdino on 14/12/17.
 */

public class GraphPageAdapter extends FragmentPagerAdapter
{
    private final Float mMinValue;
    private final Float mMaxValue;
    private List<Resumo> mSummaryList;
    // todo não esta usando o mes selecionado ainda
    private int mIndexMonthSelected;
    //
    private int mMonthInitial;

    public GraphPageAdapter(FragmentManager fm, List<Resumo> mSummaryList, int mIndexMonthSelected, Float minValue, Float maxValue) {
        super(fm);
        this.mSummaryList = mSummaryList;
        this.mIndexMonthSelected = mIndexMonthSelected;
        this.mMinValue = minValue ;
        this.mMaxValue = maxValue;
    }

    @Override
    public Fragment getItem(int position)
    {
        initializeIndexGraph(position);
        List<Resumo> resumos;
        int indexInitial = mMonthInitial-1;
//        if(position != 0)
//        {
//            indexInitial = mMonthInitial-2;
//        }

        int indexFinal;
        if(mMonthInitial + 4 <=mSummaryList.size())
        {
            indexFinal = mMonthInitial + 3;
            if(position != 2)
            {
                indexFinal = mMonthInitial + 4;
            }
            resumos = mSummaryList.subList(indexInitial, indexFinal);
        }
        else
        {
            resumos = mSummaryList.subList(indexInitial, mSummaryList.size());
        }
        int indexSelected = getIndexSelected(resumos);
        FinancialGraphFragment financialGraphFragment = FinancialGraphFragment.newInstance(position, indexSelected,mMonthInitial , resumos, mMinValue, mMaxValue);
        return financialGraphFragment;
    }

    private int getIndexSelected(List<Resumo> resumos)
    {
        int index = 0;
        for(int i = 0; i < resumos.size(); i++)
        {
            if(i+1 == mIndexMonthSelected)
            {
                return index;
            }
            if(index == 3)
            {
                index =0;
            }
            else {
                index++;
            }
        }
        return 0;
    }

    private void initializeIndexGraph(int position)
    {
        mMonthInitial = 1;
        if(position == 1)
        {
            mMonthInitial = 5;
        }
        if(position == 2)
        {
            mMonthInitial = 9;
        }
    }

    @Override
    public int getCount()
    {
        if(mSummaryList == null) {
            return 0;
        }
        else if (mSummaryList.size() > 8)
        {
            return 3;
        }
        else if (mSummaryList.size() > 4)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

}

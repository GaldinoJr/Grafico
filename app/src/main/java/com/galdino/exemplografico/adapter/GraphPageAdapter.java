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
    private List<Resumo> mSummaryList;
    private int mIndexMonthSelected;
    //
    private int mMonthInitial;

    public GraphPageAdapter(FragmentManager fm, List<Resumo> mSummaryList, int mIndexMonthSelected) {
        super(fm);
        this.mSummaryList = mSummaryList;
        this.mIndexMonthSelected = mIndexMonthSelected;
    }

    @Override
    public Fragment getItem(int position)
    {
        initializeIndexGraph(position);
        List<Resumo> resumos ;
        if(mMonthInitial + 4 <=mSummaryList.size()) {
            resumos = mSummaryList.subList(mMonthInitial, mMonthInitial + 4);
        }
        else
        {
            resumos = mSummaryList.subList(mMonthInitial, mSummaryList.size());
        }
        return FinancialGraphFragment.newInstance(mIndexMonthSelected,resumos);
    }

    private void initializeIndexGraph(int position)
    {
        mMonthInitial = 1;
        if(position == 2)
        {
            mMonthInitial = 5;
        }
        if(position == 3)
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

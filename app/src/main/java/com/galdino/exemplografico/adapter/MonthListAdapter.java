package com.galdino.exemplografico.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galdino.exemplografico.R;
import com.galdino.exemplografico.databinding.MonthAdapterBinding;
import com.galdino.exemplografico.domain.Month;

import java.util.List;

/**
 * Created by galdino on 12/12/17.
 */

public class MonthListAdapter extends RecyclerView.Adapter<MonthListAdapter.ViewHolder>
{
    private final int mMeasuredWidth;
    private List<Month> mList;
    private MonthListAdapter.Listener mListener;

    public MonthListAdapter(int measuredWidth, List<Month> mList)
    {
        this.mList = mList;
        this.mMeasuredWidth = measuredWidth;
    }

    public int getItemWidth() {
        int width;
        if (mList.size() >= 4) {
            width = mMeasuredWidth / 4;
        } else {
            width = mMeasuredWidth / mList.size();
        }
        return width;
    }

    public void disableItemsMenu() {
        if(mList != null && mList.size() > 0)
        {
            for (Month month: mList)
            {
                month.setSelected(false);
            }
        }
    }

    public interface Listener{
        void onItemClicked(Month month);
    }

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MonthListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MonthAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.month_adapter,parent,false);
        int width = getItemWidth();
        if(width != 0)
        {
            View view = binding.getRoot();
            view.getLayoutParams().width = width;
        }
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MonthListAdapter.ViewHolder holder, int position)
    {
        if(holder.mBinding != null && mList.get(position) != null)
        {
            Month month = mList.get(position);
            Context context = holder.mBinding.getRoot().getContext();
            holder.mBinding.tvMonth.setText(month.getMonth());
            if(month.isSelected())
            {
                holder.mBinding.tvMonth.setTextColor(ContextCompat.getColor(context, R.color.colorBlueLightGraph));
            }
            else
            {
                holder.mBinding.tvMonth.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }
        }
    }

    @Override
    public int getItemCount()
    {
        if(mList == null) {
            return 0;
        }
        else
        {
            return mList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private MonthAdapterBinding mBinding;
        public ViewHolder(MonthAdapterBinding binding)
        {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(view -> {
                int adapterPosition = getAdapterPosition();
                mListener.onItemClicked(mList.get(adapterPosition));
            });
        }
    }
}

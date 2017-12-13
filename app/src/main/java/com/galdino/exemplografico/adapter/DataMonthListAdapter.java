package com.galdino.exemplografico.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.galdino.exemplografico.R;
import com.galdino.exemplografico.databinding.DataMonthAdapterBinding;
import com.galdino.exemplografico.domain.dataMonth.TipoEntradaSaida;

import java.util.List;

/**
 * Created by galdino on 13/12/17.
 */

public class DataMonthListAdapter  extends RecyclerView.Adapter<DataMonthListAdapter.ViewHolder>
{
    private List<TipoEntradaSaida> mList;
    private DataMonthListAdapter.Listener mListener;

    public DataMonthListAdapter(List<TipoEntradaSaida> mList)
    {
        this.mList = mList;
    }

    public interface Listener{
        void onItemClicked(TipoEntradaSaida tipoEntradaSaida);
    }

    public void setListener(DataMonthListAdapter.Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public DataMonthListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        DataMonthAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.data_month_adapter,parent,false);

        return new DataMonthListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataMonthListAdapter.ViewHolder holder, int position)
    {
        if(holder.mBinding != null && mList.get(position) != null)
        {
            TipoEntradaSaida tipoEntradaSaida = mList.get(position);
            holder.mBinding.tvText.setText(tipoEntradaSaida.getTipoEs());
            holder.mBinding.tvValue.setText(tipoEntradaSaida.getTotal());
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
        private DataMonthAdapterBinding mBinding;
        public ViewHolder(DataMonthAdapterBinding binding)
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

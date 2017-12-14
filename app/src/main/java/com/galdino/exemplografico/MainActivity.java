package com.galdino.exemplografico;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.galdino.exemplografico.databinding.ActivityMainBinding;
import com.galdino.exemplografico.domain.dataMonth.DadosPrevisao;
import com.galdino.exemplografico.domain.dataMonth.ObjectFinantialValues;
import com.galdino.exemplografico.domain.dataMonth.Resumo;
import com.galdino.exemplografico.domain.dataMonth.TipoEntradaSaida;
import com.galdino.exemplografico.fragment.FinancialGraphFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private ObjectFinantialValues objectFinantialValues;

    private int mIndexMonthSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initializeMockedData();
        if(objectFinantialValues == null || objectFinantialValues.getDadosPrevisao() == null || objectFinantialValues.getDadosPrevisao().getResumo() == null)
        {
            return;
        }
        List<Resumo> resumo = objectFinantialValues.getDadosPrevisao().getResumo();
        FinancialGraphFragment financialGraphFragment = FinancialGraphFragment.newInstance(mIndexMonthSelected, resumo);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_root,financialGraphFragment)
                .commit();

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

    private List<TipoEntradaSaida> loadMockedListEntradaSaida(int index) {
        List<TipoEntradaSaida> listEntradaSaida = new LinkedList<>();
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Crédito","1500" + String.valueOf(index + 1) + "0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cartão de Débito","1500" + String.valueOf(index + 1) + "0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Dinheiro","1500" + String.valueOf(index + 1) +"0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Bom","1500" + String.valueOf(index + 1) +"0.50"));
        listEntradaSaida.add(new TipoEntradaSaida("Cheque Pré","1500" + String.valueOf(index + 1) +"0.50"));
        return listEntradaSaida;
    }
    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");

        return xVals;
    }
}

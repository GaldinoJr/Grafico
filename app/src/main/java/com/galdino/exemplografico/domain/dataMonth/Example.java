
package com.galdino.exemplografico.domain.dataMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("dadosPrevisao")
    @Expose
    private DadosPrevisao dadosPrevisao;

    public DadosPrevisao getDadosPrevisao() {
        return dadosPrevisao;
    }

    public void setDadosPrevisao(DadosPrevisao dadosPrevisao) {
        this.dadosPrevisao = dadosPrevisao;
    }

}

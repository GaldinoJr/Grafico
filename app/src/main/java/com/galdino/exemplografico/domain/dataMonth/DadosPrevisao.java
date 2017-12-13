
package com.galdino.exemplografico.domain.dataMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DadosPrevisao {

    @SerializedName("mesAtual")
    @Expose
    private String mesAtual;
    @SerializedName("mesAnterior")
    @Expose
    private String mesAnterior;
    @SerializedName("resumo")
    @Expose
    private List<Resumo> resumo = null;

    public String getMesAtual() {
        return mesAtual;
    }

    public void setMesAtual(String mesAtual) {
        this.mesAtual = mesAtual;
    }

    public String getMesAnterior() {
        return mesAnterior;
    }

    public void setMesAnterior(String mesAnterior) {
        this.mesAnterior = mesAnterior;
    }

    public List<Resumo> getResumo() {
        return resumo;
    }

    public void setResumo(List<Resumo> resumo) {
        this.resumo = resumo;
    }

}

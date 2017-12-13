
package com.galdino.exemplografico.domain.dataMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resumo {

    @SerializedName("ano")
    @Expose
    private String ano;
    @SerializedName("mes")
    @Expose
    private String mes;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("tipo_es")
    @Expose
    private List<TipoEntradaSaida> tipoEs = null;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<TipoEntradaSaida> getTipoEs() {
        return tipoEs;
    }

    public void setTipoEs(List<TipoEntradaSaida> tipoEs) {
        this.tipoEs = tipoEs;
    }

}

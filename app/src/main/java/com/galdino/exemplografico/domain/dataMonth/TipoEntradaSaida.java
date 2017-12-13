
package com.galdino.exemplografico.domain.dataMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoEntradaSaida {
    @SerializedName("tipo_es")
    @Expose
    private String tipoEs;
    @SerializedName("total")
    @Expose
    private String total;

    public TipoEntradaSaida() {
    }

    public TipoEntradaSaida(String tipoEs, String total) {
        this.tipoEs = tipoEs;
        this.total = total;
    }

    public String getTipoEs() {
        return tipoEs;
    }

    public void setTipoEs(String tipoEs) {
        this.tipoEs = tipoEs;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}

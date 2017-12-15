
package com.galdino.exemplografico.domain.dataMonth;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Resumo implements Parcelable {

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

    public int getMesInteger()
    {
        if(mes == null)
        {
            return 0;
        }
        else
        {
            return Integer.parseInt(mes);
        }
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTotal() {
        return total;
    }

    public Float getTotalValue()
    {
        if(total != null)
        {
            return Float.parseFloat(total);
        }
        else
        {
            return 0f;
        }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ano);
        dest.writeString(this.mes);
        dest.writeString(this.total);
        dest.writeList(this.tipoEs);
    }

    public Resumo() {
    }

    protected Resumo(Parcel in) {
        this.ano = in.readString();
        this.mes = in.readString();
        this.total = in.readString();
        this.tipoEs = new ArrayList<TipoEntradaSaida>();
        in.readList(this.tipoEs, TipoEntradaSaida.class.getClassLoader());
    }

    public static final Parcelable.Creator<Resumo> CREATOR = new Parcelable.Creator<Resumo>() {
        @Override
        public Resumo createFromParcel(Parcel source) {
            return new Resumo(source);
        }

        @Override
        public Resumo[] newArray(int size) {
            return new Resumo[size];
        }
    };
}

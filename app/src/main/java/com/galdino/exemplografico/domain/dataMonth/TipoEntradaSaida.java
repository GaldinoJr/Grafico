
package com.galdino.exemplografico.domain.dataMonth;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoEntradaSaida implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipoEs);
        dest.writeString(this.total);
    }

    protected TipoEntradaSaida(Parcel in) {
        this.tipoEs = in.readString();
        this.total = in.readString();
    }

    public static final Parcelable.Creator<TipoEntradaSaida> CREATOR = new Parcelable.Creator<TipoEntradaSaida>() {
        @Override
        public TipoEntradaSaida createFromParcel(Parcel source) {
            return new TipoEntradaSaida(source);
        }

        @Override
        public TipoEntradaSaida[] newArray(int size) {
            return new TipoEntradaSaida[size];
        }
    };
}

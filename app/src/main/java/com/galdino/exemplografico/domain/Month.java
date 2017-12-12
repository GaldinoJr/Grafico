package com.galdino.exemplografico.domain;

/**
 * Created by galdino on 12/12/17.
 */

public class Month
{
    String Month;
    boolean selected;

    public Month(String month) {
        Month = month;
    }

    public String getMonth() {
        return Month;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

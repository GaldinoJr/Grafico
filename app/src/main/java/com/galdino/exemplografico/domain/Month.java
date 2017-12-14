package com.galdino.exemplografico.domain;

/**
 * Created by galdino on 12/12/17.
 */

public class Month
{
    private static final String DEF_1 = "Jan";
    private static final String DEF_2 = "Fev";
    private static final String DEF_3 = "Mar";
    private static final String DEF_4 = "Abr";
    private static final String DEF_5 = "Mai";
    private static final String DEF_6 = "Jun";
    private static final String DEF_7 = "Jul";
    private static final String DEF_8 = "Ago";
    private static final String DEF_9 = "Set";
    private static final String DEF_10 = "Out";
    private static final String DEF_11 = "Nov";
    private static final String DEF_12 = "Dez";
    //
    private static final String DEF_Jan = "Janeiro";
    private static final String DEF_Fev = "Fevereiro";
    private static final String DEF_Mar = "Março";
    private static final String DEF_Abr = "Abril";
    private static final String DEF_Mai = "Maio";
    private static final String DEF_Jun = "Junho";
    private static final String DEF_Jul = "Julho";
    private static final String DEF_Ago = "Agosto";
    private static final String DEF_Set = "Setembro";
    private static final String DEF_Out = "Outubro";
    private static final String DEF_Nov = "Novembro";
    private static final String DEF_Dez = "Dezembro";

    String month;
    boolean selected;
    private int monthNumeric;


    public Month(String month) {
        this.month = month;
    }

    public Month(int monthNumeric) {
        this.monthNumeric = monthNumeric;
    }

    public String getMonth() {
        return month;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getMonthNumeric() {
        monthNumeric = 0;
        switch (month)
        {
            case DEF_1:
                monthNumeric=1;
                break;
            case DEF_2:
                monthNumeric=2;
                break;
            case DEF_3:
                monthNumeric=3;
                break;
            case DEF_4:
                monthNumeric=4;
                break;
            case DEF_5:
                monthNumeric=5;
                break;
            case DEF_6:
                monthNumeric=6;
                break;
            case DEF_7:
                monthNumeric=7;
                break;
            case DEF_8:
                monthNumeric=8;
                break;
            case DEF_9:
                monthNumeric=9;
                break;
            case DEF_10:
                monthNumeric=10;
                break;
            case DEF_11:
                monthNumeric=11;
                break;
            case DEF_12:
                monthNumeric=12;
                break;
        }
        return monthNumeric;
    }

    public String getCompleteMonthName()
    {
        String completeMonthName ="";
        switch (monthNumeric)
        {
            case 1:
                completeMonthName=DEF_Jan;
                break;
            case 2:
                completeMonthName=DEF_Fev;
                break;
            case 3:
                completeMonthName=DEF_Mar;
                break;
            case 4:
                completeMonthName=DEF_Abr;
                break;
            case 5:
                completeMonthName=DEF_Mai;
                break;
            case 6:
                completeMonthName=DEF_Jun;
                break;
            case 7:
                completeMonthName=DEF_Jul;
                break;
            case 8:
                completeMonthName=DEF_Ago;
                break;
            case 9:
                completeMonthName=DEF_Set;
                break;
            case 10:
                completeMonthName=DEF_Out;
                break;
            case 11:
                completeMonthName=DEF_Nov;
                break;
            case 12:
                completeMonthName=DEF_Dez;
                break;
        }
        return completeMonthName;
    }
}

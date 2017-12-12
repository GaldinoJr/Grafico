package com.galdino.exemplografico.domain;

import android.content.Context;

import com.galdino.exemplografico.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by galdino on 12/12/17.
 */

public class MonthList
{
    List<Month> firstListMonths;
    List<Month> secondListMonths;
    List<Month> thirdListMonths;

    public MonthList(Context context) {
        firstListMonths = new LinkedList<>();
        secondListMonths = new LinkedList<>();
        thirdListMonths = new LinkedList<>();

        firstListMonths.add(new Month(context.getString(R.string.month_1)));
        firstListMonths.add(new Month(context.getString(R.string.month_2)));
        firstListMonths.add(new Month(context.getString(R.string.month_3)));
        firstListMonths.add(new Month(context.getString(R.string.month_4)));
        secondListMonths.add(new Month(context.getString(R.string.month_5)));
        secondListMonths.add(new Month(context.getString(R.string.month_6)));
        secondListMonths.add(new Month(context.getString(R.string.month_7)));
        secondListMonths.add(new Month(context.getString(R.string.month_8)));
        thirdListMonths.add(new Month(context.getString(R.string.month_9)));
        thirdListMonths.add(new Month(context.getString(R.string.month_10)));
        thirdListMonths.add(new Month(context.getString(R.string.month_11)));
        thirdListMonths.add(new Month(context.getString(R.string.month_12)));
    }

    public List<Month> getFirstListMonths() {
        return firstListMonths;
    }

    public List<Month> getSecondListMonths() {
        return secondListMonths;
    }

    public List<Month> getThirdListMonths() {
        return thirdListMonths;
    }
}

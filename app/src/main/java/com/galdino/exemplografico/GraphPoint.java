package com.galdino.exemplografico;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by galdino on 11/12/17.
 */

class GraphPoint
{
    private static final String DATE_FORMAT = "MMM/dd";
    private String date;
    private double sent;
    private double accepted;

    public long getDateInMillis() {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        Date date = new Date(System.currentTimeMillis());
        try {
            date = simpleDateFormat.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    @NonNull
    private SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    public double getSent() {
        return sent;
    }

    public void setDayInMillis(long dayInMillis) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        date = simpleDateFormat.format(new Date(dayInMillis));
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public double getAccepted() {
        return accepted;
    }
}

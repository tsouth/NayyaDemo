package com.mindr.utilities.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MindrDate {
    private final Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public MindrDate() {
        this(new Date());
    }

    public MindrDate(Date date) {
        calendar.setTime(date);
    }

    public MindrDate dateAndTime() {
        this.format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            MindrDate date = (MindrDate) obj;

            return toString().equals(date.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        Date date = calendar.getTime();

        return format.format(date);
    }
}

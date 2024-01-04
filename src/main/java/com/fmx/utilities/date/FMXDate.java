package com.fmx.utilities.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FMXDate {
    private final Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public FMXDate() {
        this(new Date());
    }

    public FMXDate(Date date) {
        calendar.setTime(date);
    }

    public FMXDate dateAndTime() {
        this.format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            FMXDate date = (FMXDate) obj;

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

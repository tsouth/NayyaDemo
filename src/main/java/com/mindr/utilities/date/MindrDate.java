package com.mindr.utilities.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MindrDate {
    private final Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public MindrDate() {
        this(new Date());
    }

    public MindrDate(Date date) {
        calendar.setTime(date);
    }

    public MindrDate withMonths(int months) {
        calendar.add(Calendar.MONTH, months);

        return this;
    }

    public MindrDate withYears(int years) {
        calendar.add(Calendar.YEAR, years);

        return this;
    }

    public MindrDate withEmailDateFormat() {
        this.format = new SimpleDateFormat("yyyy-MM-dd");

        return this;
    }

    public MindrDate withFullDateFormat() {
        this.format = new SimpleDateFormat("MM/dd/yyyy");

        return this;
    }

    public MindrDate withTruncatedDateFormat() {
        this.format = new SimpleDateFormat("M/d/yyyy");

        return this;
    }

    public MindrDate withEasternTimeZone() {
        TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
        format.setTimeZone(timeZone);

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

package com.zx.lab_attendance.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/13 23:51
 * @Description
 */
public class DateConversionWeek {

    public static int DateConversionWeek(String date) throws ParseException {
//        String time = "2020-2-13";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newdate = format.parse(date);
        Instant instant = newdate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        DayOfWeek day = localDate.getDayOfWeek();
        int WeekDay = 0;

        switch (day) {
            case MONDAY:
                WeekDay = 1;
                break;
            case FRIDAY:
                WeekDay = 5;
                break;
            case SATURDAY:
                WeekDay = 6;
                break;
            case SUNDAY:
                WeekDay = 7;
                break;
            case THURSDAY:
                WeekDay = 4;
                break;
            case TUESDAY:
                WeekDay = 2;
                break;
            case WEDNESDAY:
                WeekDay = 3;
                break;
        }
        return WeekDay;
    }
}

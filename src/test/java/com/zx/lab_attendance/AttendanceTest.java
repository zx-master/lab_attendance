package com.zx.lab_attendance;


import com.zx.lab_attendance.utils.DateConversionWeek;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/12 10:53
 * @Description
 */
public class AttendanceTest {

    @Test
    public void getPercentage(){
        System.out.println((double)1/3);
    }

    @Test
    public void getLastDay() throws ParseException {
        Date nowday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        String nowdayStr = sdf.format(nowday);
        int weekDay = DateConversionWeek.DateConversionWeek(nowdayStr);
        if (weekDay == 6){
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-24*60*60*1000);
            nowday = as;
            String newtime = sdf.format(as);
            weekDay = 5;
        } else if(weekDay == 7){
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-48*60*60*1000);
            nowday = as;
            String newtime = sdf.format(as);
            weekDay = 5;
        }
        List<String> strings = new ArrayList<>();
        List<String> dateString = new ArrayList<>();
        for (int i = 0;i < 5; i++) {
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-(i+weekDay+2)*24*60*60*1000);
            String newtime = sdf.format(as);
            strings.add(newtime);
        }
        for (int i = 0;i < weekDay;i++){
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-i*24*60*60*1000);
            String newtime = sdf.format(as);
            dateString.add(newtime);
        }
        System.out.println("str:" + strings);
        System.out.println("dateStr:" + dateString);
    }

    @Test
    public void getTwoWeek() throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        int weekDay = DateConversionWeek.DateConversionWeek("2020-02-01");
        List<String> lastDateList = new ArrayList<>();
        List<String> thisDateList = new ArrayList<>();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        for (int i = 0;i < 5; i++) {
            rightNow.add(Calendar.DAY_OF_MONTH, -weekDay - 6 + i);
            Date dt1 = rightNow.getTime();
            String reStr = formatter.format(dt1);
            lastDateList.add(reStr);
        }
        for (int i = 0;i < weekDay;i++){
            rightNow.add(Calendar.DAY_OF_MONTH, -weekDay + i + 1);
            Date dt1 = rightNow.getTime();
            String reStr = formatter.format(dt1);
            thisDateList.add(reStr);
        }
        System.out.println(lastDateList);
        System.out.println(thisDateList);
    }

    @Test
    public void removeDuplicate() {
        List list = Arrays.asList("a", "b", "c", "a","c","a");
        List list1 = new ArrayList();
        System.out.println(list);
        ArrayList<String> newst= new ArrayList<String>(new LinkedHashSet<String>(list));//不改变之前顺序
        for(String ll : newst){
            list1.add(ll);
        }
        System.out.println(list1);
    }

    @Test
    public void testWeek() throws ParseException {
        String time = "2020-2-13";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = format.parse(time);
        Instant instant = date.toInstant();
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
        System.out.println(WeekDay);
    }

    @Test
    public void getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
    }

    @Test
    public void getWeek(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
    }




}

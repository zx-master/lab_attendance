package com.zx.lab_attendance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/14 0:52
 * @Description
 */
public class CalendarTest {
    /**
     * <p>Title: main </P>
     * <p>Description: TODO </P>
     * @param args
     * return void    返回类型
     * throws
     * date 2014-11-24 上午09:11:47
     */
    public static void main(String[] args) {
        try {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar ca = Calendar.getInstance();
            Date d = df.parse("2020-02-13");
            ca.setTime(d);//设置当前时间

            CalendarTest ct = new CalendarTest();
//            ct.initHolidayList("2014-11-06");//初始节假日
//            ct.initHolidayList("2014-11-07");//初始节假日
//            ct.initHolidayList("2014-11-03");//初始节假日

            Calendar c = ct.addDateByWorkDay(ca,5);
            System.out.println(df.format(c.getTime()));

        } catch ( Exception e) {
            // TODO: handle exception
            System.out.println(e.getClass());
            e.printStackTrace();
        }

    }

    private static List<Calendar> holidayList = new ArrayList<Calendar>();  //节假日列表

    /**
     *
     * <p>Title: addDateByWorkDay </P>
     * <p>Description: TODO  计算相加day天，并且排除节假日和周末后的日期</P>
     * @param calendar  当前的日期
     * @param day  相加天数
     * @return
     * return Calendar    返回类型   返回相加day天，并且排除节假日和周末后的日期
     * throws
     * date 2014-11-24 上午10:32:55
     */
    public Calendar addDateByWorkDay(Calendar calendar,int day){

        try {
            for (int i = 0; i < day; i++) {

                calendar.add(Calendar.DAY_OF_MONTH, 1);

                if(checkHoliday(calendar)){
                    i--;
                }
            }
            return calendar;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     *
     * <p>Title: checkHoliday </P>
     * <p>Description: TODO 验证日期是否是节假日</P>
     * @param calendar  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     * date 2014-11-24 上午10:13:07
     */
    public boolean checkHoliday(Calendar calendar) throws Exception{

        //判断日期是否是周六周日
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            return true;
        }
        //判断日期是否是节假日
        for (Calendar ca : holidayList) {
            if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
                    ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
                return true;
            }
        }

        return false;
    }

    /**
     *
     * <p>Title: initHolidayList </P>
     * <p>Description: TODO  把所有节假日放入list</P>
     * @param date  从数据库查 查出来的格式2014-05-09
     * return void    返回类型
     * throws
     * date 2014-11-24 上午10:11:35
     */
    public void initHolidayList( String date){

        String [] da = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(da[1])-1);//月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
        holidayList.add(calendar);
    }
}

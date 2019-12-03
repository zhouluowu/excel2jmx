package com.djtest.apitest.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {
    private static Pattern ym = Pattern.compile("^\\d{4}(\\-|\\/|.)\\d{1,2}$");//年月格式的日期
    private static Pattern ymd = Pattern.compile("^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}$");//年月日格式的日期
    private static Pattern ymdhms = Pattern.compile("^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$"); //年月日时分秒格式的日期

    public static String getDate(String d, int days) throws ParseException{
        Matcher m1 = ym.matcher(d);
        Matcher m2 = ymd.matcher(d);
        Matcher m3 = ymdhms.matcher(d);
        SimpleDateFormat sdf;
        Integer type;
        if(m1.find()){
            sdf = new SimpleDateFormat("yyyy-MM");
            type = Calendar.MONTH;
        }else if (m2.find()){
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            type = Calendar.DAY_OF_MONTH;
        }else if (m3.find()){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            type = Calendar.DAY_OF_MONTH;
        }else{
            return "DateFormatError";
        }

        Date date = new Date();

        String stringDate;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sdf.parse(d));

        calendar.add(type, days);

        date = calendar.getTime();
        stringDate = sdf.format(date);

        return stringDate;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getDate("2019-10-01",1));

    }
}

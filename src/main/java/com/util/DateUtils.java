package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {
    //日期转换为字符串
    public static String dateToString(Date date){
        SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format=smf.format(date);
        return  format;}
        //字符串转换为日期
        public static Date stringToDate(String str) throws ParseException {
            SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse=smf.parse("str");
            return  parse;
        }
    }


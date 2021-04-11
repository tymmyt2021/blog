package www.wzb.common;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtils {
    /**
     * 将日期字符串根据指定格式转成日期格式
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date StringToDate(String dateStr, String formatStr){
        DateFormat dd=new SimpleDateFormat(formatStr);
        Date date=null;
        if(dateStr != null && dateStr != ""){
            try {
                date = dd.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }else
            return null;


    }

    /**
     * 将日期根据指定根式转成字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        if(date == null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

}

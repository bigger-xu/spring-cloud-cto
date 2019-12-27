package com.cto.cloud.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-03-24
 */
public class DateUtil {
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_FORMAT = "MM-dd";
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";
    public static final int SUB_YEAR = 1;
    public static final int SUB_MONTH = 2;
    public static final int SUB_DAY = 5;
    public static final int SUB_HOUR = 10;
    public static final int SUB_MINUTE = 12;
    public static final int SUB_SECOND = 13;
    static final String[] dayNames = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtil() {
    }

    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        String[] tmp = dateStr.split(" +");
        if (tmp.length != 2) {
            dateStr = dateStr + " 00:00:00";
        }

        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception var6) {
            d = null;
        }

        return d;
    }

    public static Date stringtoDate(String dateStr, String format, ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);

        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception var6) {
            d = null;
        }

        return d;
    }

    public static String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);

        try {
            result = formater.format(date);
        } catch (Exception var5) {
        }

        return result;
    }

    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, "yyyy-MM-dd HH:mm:ss").getTime();
        long second = stringtoDate(secTime, "yyyy-MM-dd HH:mm:ss").getTime();
        return (second - first) / 1000L;
    }

    public static int getDaysOfMonth(String year, String month) {
        byte days;
        if (!month.equals("1") && !month.equals("3") && !month.equals("5") && !month.equals("7") && !month.equals("8") && !month.equals("10") && !month.equals("12")) {
            if (!month.equals("4") && !month.equals("6") && !month.equals("9") && !month.equals("11")) {
                if ((Integer.parseInt(year) % 4 != 0 || Integer.parseInt(year) % 100 == 0) && Integer.parseInt(year) % 400 != 0) {
                    days = 28;
                } else {
                    days = 29;
                }
            } else {
                days = 30;
            }
        } else {
            days = 31;
        }

        return days;
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(5);
    }

    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(5);
    }

    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) + 1;
    }

    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1);
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000L;
    }

    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, "yyyy-MM-dd");
        Date afterDay = stringtoDate(after, "yyyy-MM-dd");
        return getYear(afterDay) - getYear(beforeDay);
    }

    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, "yyyy-MM-dd");
        return getYear(beforeDay) - getYear(afterDay);
    }

    public static long dayDiffCurr(String before) {
        Date currDate = stringtoDate(currDay(), "yyyy-MM-dd");
        Date beforeDate = stringtoDate(before, "yyyy-MM-dd");
        return (currDate.getTime() - beforeDate.getTime()) / 86400000L;
    }

    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(7);
        c.set(year, month - 1, 1);
        return c.get(7);
    }

    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(7);
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(7);
    }

    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }

        if (!isDate(birth)) {
            return "";
        } else {
            int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
            int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
            String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
            int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
            int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
            return s.substring(start, start + 2) + "座";
        }
    }

    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(2, months);
        return cal.getTime();
    }

    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(6, day);
        return cal.getTime();
    }

    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(6, day);
        return dateToString(cal.getTime(), format);
    }

    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(4, week);
        return cal.getTime();
    }

    public static String currDay() {
        return dateToString(new Date(), "yyyy-MM-dd");
    }

    public static String befoDay() {
        return befoDay("yyyy-MM-dd");
    }

    public static String befoDay(String format) {
        return dateToString(nextDay(new Date(), -1), format);
    }

    public static String afterDay() {
        return dateToString(nextDay(new Date(), 1), "yyyy-MM-dd");
    }

    public static int getDayNum() {
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        int daynum = (int)((dt.getTime() - dt1.getTime()) / 86400000L);
        return daynum;
    }

    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    public static String getYmdDateCN(String datestr) {
        if (datestr == null) {
            return "";
        } else if (datestr.length() < 10) {
            return "";
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
            return buf.toString();
        }
    }

    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        return dateToString(cal.getTime(), format);
    }

    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        return dateToString(cal.getTime(), format);
    }

    public static String formatString(String date) {
        Date d = stringtoDate(date, "yyyy-MM-dd");
        String dateFormat = dateToString(d, "yyyy-MM-dd");
        return dateFormat;
    }

    public static String formatLString(String date) {
        Date d = stringtoDate(date, "yyyy-MM-dd HH:mm:ss");
        String dateFormat = dateToString(d, "yyyy-MM-dd HH:mm:ss");
        return dateFormat;
    }

    public static String getTime(String date, String flag) {
        if (date.trim().indexOf(" ") == -1) {
            String[] tmp = date.split(" ");
            if (tmp.length == 2) {
                return date;
            } else {
                return flag.equals("start") ? date.trim() + " 00:00:00" : date.trim() + " 23:59:59";
            }
        } else {
            return date;
        }
    }
}

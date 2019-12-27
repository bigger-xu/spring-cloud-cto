package com.cto.cloud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-03-23
 */
public class StringUtil {
    public StringUtil() {
    }

    public static String capFirst(String str) {
        return str.replaceFirst(".", str.substring(0, 1).toUpperCase());
    }

    public static String javaStyle(String columnName) {
        String patternStr = "(_[a-z])";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(columnName);
        StringBuffer buf = new StringBuffer();

        while(matcher.find()) {
            String replaceStr = matcher.group();
            matcher.appendReplacement(buf, replaceStr.toUpperCase());
        }

        matcher.appendTail(buf);
        return buf.toString().replaceAll("_", "");
    }

    public static String getTimeString() {
        String fmt = "yyyyMMddkkmmssSSS";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date());
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        } else {
            StringBuilder result = new StringBuilder();
            boolean flag = false;

            String string;
            for(Iterator i$ = stringList.iterator(); i$.hasNext(); result.append("\"" + string + "\"")) {
                string = (String)i$.next();
                if (flag) {
                    result.append(",");
                } else {
                    flag = true;
                }
            }

            return result.toString();
        }
    }
}

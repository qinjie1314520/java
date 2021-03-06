package com.example.demo.实用工具类;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  * 简单的工具类，用户小程序使用
  *
  * @author: qinjie
 **/
public class CommonUtils {
    /**
     *
     * 功能描述: 得到当前时间
     *
     * @return: 当前时间，2017-08-08.。。。
     * @auther: 秦杰
     * @date: 2019/8/29 11:39
     */
    public static String getCurrentTime(){
        return TimeStampToTime(System.currentTimeMillis());
    }
    /**
     *
     * 功能描述: 向右补位
     *
     * @param: src原字符串，len补位后的总的长度，ch补位字符
     * @return:
     * @auther: 秦杰
     * @date: 2019/8/29 11:41
     */
    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
        for (int i = src.length(); i < len; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }
    /**
     *
     * 功能描述: 向左边补位
     *
     * @param: [src, len, ch] src：原字符串   len：补足的长度，  ch：补位的字符
     * @return: java.lang.String
     * @auther: 秦杰
     * @date: 2019/7/31 14:56
     */
    public static String padLeft(String src, int len, char ch) {

        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    /**
     *
     * 功能描述: 得到指定长度的随机字符串，存在重复的可能性
     *
     * @param: [length]生成随机字符串的长度
     * @return: java.lang.String
     * @auther: 秦杰
     * @date: 2019/7/27 16:10
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     *
     * 功能描述: 得到头部保存的信息，token信息
     *
     * @param: [attributes]
     * @return: java.lang.String
     * @auther: 秦杰
     * @date: 2019/7/27 16:12
     */
    public static String getHeadToken(ServletRequestAttributes attributes, String key) {

        HttpServletRequest request = attributes.getRequest();
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key1 = (String) headerNames.nextElement();
            String value = request.getHeader(key1);
            map.put(key1, value);
        }
        return map.get(key);
    }

    /**
     * 　　　2019年1月16日已知
     * 中国电信号段
     * 133,149,153,173,174,177,180,181,189,199
     * 中国联通号段
     * 130,131,132,145,146,155,156,166,175,176,185,186
     * 中国移动号段
     * 134(0-8),135,136,137,138,139,147,148,150,151,152,157,158,159,165,178,182,183,184,187,188,198
     * 上网卡专属号段（用于上网和收发短信，不能打电话）
     * 如中国联通的是145
     * 虚拟运营商
     * 电信：1700,1701,1702
     * 移动：1703,1705,1706
     * 联通：1704,1707,1708,1709,171
     * 卫星通信： 1349 <br>　　　　　未知号段：141、142、143、144、154
     *
     * @param str 电话
     * @return
     */
    public static boolean isMobile(String str) {
        boolean b = false;
        if (!Strings.isBlank(str)) {
            Pattern p = null;
            Matcher m = null;
            String s2 = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     *
     * 功能描述: Date转换成定时器中Cron表达式
     *
     * @param: [time]Date类型的时间
     * @return: java.lang.String
     * @auther: 秦杰
     * @date: 2019/7/27 16:14
     */
    public static String getCron(Long time) {

        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (time != null) {
            formatTimeStr = sdf.format(time);
        }
        return formatTimeStr;
    }
    /**
     *
     * 功能描述: 得到当前时间
     *
     * @return: 时间的字符串，20170810
     * @auther: 秦杰
     * @date: 2019/8/29 11:42
     */
    public static String getDateTime(){
        String strDate = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            strDate = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }



    public static String TimeStampToTime(Long t){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(t))));      // 时间戳转换成时间
    }
    public static void sortByAsc(Integer num[]){
        for(int i = 0; i < num.length-1; i++){
            for (int j = i+1; j < num.length; j++){
                if(num[i] > num[j]){
                    int a = num[i];
                    num[i] = num[j];
                    num[j] = a;
                }
            }
        }
    }
    /**
     * 功能描述:
     * @param timeStamp 当前时间戳
     * @param timeZone GMT+8:00
     * @return java.lang.Long
     */
    public static Long getMonthStartTime(Long timeStamp, String timeZone) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        // 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    /**
     * 获取当月的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthEndTime(Long timeStamp, String timeZone) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        // 获取当前月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }
    /**
     * 功能描述: 得到指定月的开始结束时间戳,返回list，0-开始时间，1-结束时间
     * @param timeStamp 毫秒级时间戳
     * @param month  月份
     * @param timeZone  如 GMT+8:00
     * @return java.lang.Long
     */
    public static List<Long> getMonthStartAndEndTime(Long timeStamp, Integer month, String timeZone) {
        List<Long> res = new ArrayList<Long>();
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.MONTH, month-1);

        // 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        res.add(calendar.getTimeInMillis());
        // 获取指定月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        res.add(calendar.getTimeInMillis());
        return res;
    }




    public static void main(String args[]){
    }
}

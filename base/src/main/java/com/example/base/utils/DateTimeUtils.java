package com.example.base.utils;

import com.example.base.constant.ApplicationConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author AnhNHH
 *
 * Class cung cấp các hàm tiện ích với kiểu dữ liệu DateTime.
 */
public class DateTimeUtils {

    /**
     * Cung cấp hàm chuyển kiểu dữ liệu String sang Date theo pattern.
     *
     * @param date chuỗi String truyền vào.
     * @param pattern khuôn mẫu của chuỗi String.
     * @return Date.
     */
    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cung cấp hàm chuyển kiểu dữ liệu Date sang String.
     *
     * @param date ngày truyền vào.
     * @param pattern khuôn mẫu của chuỗi String.
     * @return String đã được chuyển.
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Cung cấp hàm thêm một số tháng vào ngày hiện tại.
     *
     * @param date ngày truyền vào.
     * @param month số tháng muốn thêm.
     * @return Date đã được thêm một số tháng.
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * Cung cấp hàm lấy thời gian cuối cùng của ngày.
     *
     * @param date ngày truyền vào.
     * @return Date là thời gian cuối cùng của ngày.
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Cung cấp hàm lấy thời gian đầu tiên của ngày.
     *
     * @param date ngày truyền vào.
     * @return Date là thời gian đầu tiên của ngày.
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Cung cấp hàm lấy ngày hôm qua.
     *
     * @param date ngày truyền vào.
     * @return Date là ngày hôm qua.
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * Cung cấp hàm lấy số ngày giữa hai thời điểm.
     *
     * @param from ngày bắt đầu.
     * @param to ngày kết thúc.
     * @return số ngày giữa hai thời điểm kiểu long.
     */
    public static long dateDiff(Date from, Date to) {
        return (to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000);
    }

}

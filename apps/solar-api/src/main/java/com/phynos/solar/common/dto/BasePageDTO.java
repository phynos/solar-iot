package com.phynos.solar.common.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by lupc
 * @date 2020-11-20 9:28
 */
public class BasePageDTO {

    public static final String REG_NUMBER = "[0-9]+";

    public static final String REG_CHAR_NUMBER = "[0-9a-zA-Z]+";

    /**
     * 1周的毫秒数
     */
    public static final long TIME_SPAN_1_WEEK = 7 * 24 * 60 * 60 * 1000;

    /**
     * 1个月的毫秒数
     */
    public static final long TIME_SPAN_1_MONTH = 31 * 24 * 60 * 60 * 1000;

    /**
     * 3个月的毫秒数
     */
    public static final long TIME_SPAN_3_MONTH = 3 * TIME_SPAN_1_MONTH;

    /**
     * 半年的毫秒数
     */
    public static final long TIME_SPAN_HALF_YEAR = 185 * 24 * 60 * 60 * 1000;


    private int pageIndex = 1;
    private int pageSize = 10;

    /**
     * 起始日期，忽略时分秒
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    /**
     * 结束日期，实际值为后一天的零点
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    protected static boolean checkReg(String val,String regEx){
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(val);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }

    protected boolean checkTimeNull(){
        if(begin == null || end == null){
            return false;
        }
        return true;
    }

    /**
     * 检查时间跨度
     * @param timespan 时间跨度毫秒数（尽量使用本类中的常量）
     * @return true:符合间隔要求；false:间隔大于参数所传间隔
     */
    protected boolean checkTimeSpan(long timespan){
        if(!checkTimeNull())
            return false;
        long b = begin.getTime();
        long e = end.getTime();
        if(e < b)
            return false;
        long span = e - b;//计算 起始时间 和 结束时间 间隔
        if(span > timespan)
            return false;
        return true;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset(){
        return (pageIndex - 1) * pageSize;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        this.end = cal.getTime();
    }

    public static int getTotalPageCount(int total,int pageSize){
        int t1 = total % pageSize;
        int t2 = total / pageSize;
        int totalPages = t1 == 0? t2:(t2+1);
        return totalPages;
    }

    /**
     * 检查请求的页索引是否超限，如果超限，则重置为最大页数
     * @param total
     */
    public void checkAndResetPageIndex(int total){
        int totalPages = getTotalPageCount(total,pageSize);
        if(pageIndex > totalPages){
            //置为最大页数
            pageIndex = totalPages;
        }
    }

}

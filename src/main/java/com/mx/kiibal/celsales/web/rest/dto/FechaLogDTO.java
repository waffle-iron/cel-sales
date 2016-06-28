package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class FechaLogDTO implements Serializable{
    private static final long serialVersionUID = -5732145935624152411L;
    
    private String year;
    private String month;
    private String dayOfMonth;
    private String hourOfDay;
    private String minute;
    private String second;

    public FechaLogDTO() {}

    public FechaLogDTO(String year, String month, String dayOfMonth, 
            String hourOfDay, String minute, String second) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(String hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + dayOfMonth 
                + " " + hourOfDay + ":" + minute + ":" + second;
    }
    
    
    
}

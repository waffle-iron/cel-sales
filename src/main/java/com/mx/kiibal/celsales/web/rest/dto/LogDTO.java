package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class LogDTO implements Serializable{
    private static final long serialVersionUID = -4734019333859449104L;
    
    private String logPackage;
    private String logException;
    private String logExceptionMsg;
    private FechaLogDTO logDate;

    public LogDTO() {}

    public LogDTO(String logPackage, String logException, String logExceptionMsg, 
            FechaLogDTO logDate) {
        this.logPackage = logPackage;
        this.logException = logException;
        this.logExceptionMsg = logExceptionMsg;
        this.logDate = logDate;
    }

    public String getLogPackage() {
        return logPackage;
    }

    public void setLogPackage(String logPackage) {
        this.logPackage = logPackage;
    }

    public String getLogException() {
        return logException;
    }

    public void setLogException(String logException) {
        this.logException = logException;
    }

    public String getLogExceptionMsg() {
        return logExceptionMsg;
    }

    public void setLogExceptionMsg(String logExceptionMsg) {
        this.logExceptionMsg = logExceptionMsg;
    }

    public FechaLogDTO getLogDate() {
        return logDate;
    }

    public void setLogDate(FechaLogDTO logDate) {
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "LogDTO{" + "logPackage=" + logPackage 
                + ", logException=" + logException 
                + ", logExceptionMsg=" + logExceptionMsg 
                + ", logDate=" + logDate + '}';
    }
    
    
}

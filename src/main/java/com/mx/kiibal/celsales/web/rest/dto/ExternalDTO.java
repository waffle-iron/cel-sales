package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class ExternalDTO implements Serializable{
    private static final long serialVersionUID = 3867854809621224501L;
    
    private String total;
    private String available;

    public ExternalDTO() {}

    public ExternalDTO(String total, String available) {
        this.total = total;
        this.available = available;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ExternalDTO{" + "total=" + total + ", available=" + available + '}';
    } 
}

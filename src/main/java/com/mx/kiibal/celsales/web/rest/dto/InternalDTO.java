
package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class InternalDTO implements Serializable{
    private static final long serialVersionUID = 1001682303368604472L;
    
    private String total;
    private String available;

    public InternalDTO() {}

    public InternalDTO(String total, String available) {
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
        return "InternalDTO{" + "total=" + total + ", available=" + available + '}';
    }
 
}

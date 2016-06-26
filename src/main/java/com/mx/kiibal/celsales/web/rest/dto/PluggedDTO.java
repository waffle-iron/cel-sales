package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class PluggedDTO implements Serializable{
    private static final long serialVersionUID = -2075886019730386271L;

    private String code;
    private String status;

    public PluggedDTO() {}

    public PluggedDTO(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PluggedDTO{" + "code=" + code + ", status=" + status + '}';
    }
    
    
    
    

}

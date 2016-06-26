
package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class StatusDTO implements Serializable{
    private static final long serialVersionUID = -6109534224964881609L;
    
    private String code;
    private String status;

    public StatusDTO() {}

    public StatusDTO(String code, String status) {
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
        return "StatusDTO{" + "code=" + code + ", status=" + status + '}';
    }
    
}

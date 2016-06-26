package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class WiFiDTO implements Serializable{
    private static final long serialVersionUID = -5514236216192305077L;
    
    private String macAddr;
    private boolean isEnabled;

    public WiFiDTO() {}

    public WiFiDTO(String macAddr, boolean isEnabled) {
        this.macAddr = macAddr;
        this.isEnabled = isEnabled;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "WiFiDTO{" + "macAddr=" + macAddr + ", isEnabled=" + isEnabled + '}';
    }
    
}

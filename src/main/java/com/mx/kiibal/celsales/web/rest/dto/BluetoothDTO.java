package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class BluetoothDTO implements Serializable{
    private static final long serialVersionUID = 8566062138950879799L;
    
    private String name;
    private String macAddr;
    private boolean isEnabled;

    public BluetoothDTO() {}

    public BluetoothDTO(String name, String macAddr, boolean isEnabled) {
        this.name = name;
        this.macAddr = macAddr;
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "BluetoothDTO{" + "name=" + name + ", macAddr=" + macAddr 
                + ", isEnabled=" + isEnabled + '}';
    }
    
    
    
}

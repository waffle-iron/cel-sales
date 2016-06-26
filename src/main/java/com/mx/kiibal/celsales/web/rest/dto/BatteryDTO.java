
package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class BatteryDTO implements Serializable{
    private static final long serialVersionUID = -1987948066836186161L;
    
    private HealthDTO health;
    private String batteryPct;
    private PluggedDTO plugged;
    private StatusDTO status;
    private String technology;
    private String temperature;
    private String voltage;
    private String capacity;

    public BatteryDTO() {}

    public HealthDTO getHealth() {
        return health;
    }

    public void setHealth(HealthDTO health) {
        this.health = health;
    }

    public String getBatteryPct() {
        return batteryPct;
    }

    public void setBatteryPct(String batteryPct) {
        this.batteryPct = batteryPct;
    }

    public PluggedDTO getPlugged() {
        return plugged;
    }

    public void setPlugged(PluggedDTO plugged) {
        this.plugged = plugged;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "BatteryDTO{" + "health=" + health + ", batteryPct=" + batteryPct 
                + ", plugged=" + plugged + ", status=" + status 
                + ", technology=" + technology + ", temperature=" + temperature 
                + ", voltage=" + voltage + ", capacity=" + capacity + '}';
    }
    
    
    
}

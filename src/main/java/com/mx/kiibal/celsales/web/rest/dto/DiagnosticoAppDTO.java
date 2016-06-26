package com.mx.kiibal.celsales.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbautista
 */
public class DiagnosticoAppDTO implements Serializable{
    private static final long serialVersionUID = 3963536361219963830L;
    
    private Long id;
    
    private String imei;
    private String serial;
    private String model;
    private String brand;
    private String manufacturer;
    private String version;
    private String carrier;
    private String email;
    private BatteryDTO battery;
    private StorageDTO storage;
    private BluetoothDTO bluetooth;
    private WiFiDTO wifi;
    private List<AppDataDTO> appsList;

    public DiagnosticoAppDTO() {}

    @JsonCreator
    public DiagnosticoAppDTO(
            @JsonProperty(value = "imei")String imei, 
            @JsonProperty(value = "serial")String serial, 
            @JsonProperty(value = "model")String model, 
            @JsonProperty(value = "brand")String brand, 
            @JsonProperty(value = "manufacturer")String manufacturer, 
            @JsonProperty(value = "version")String version, 
            @JsonProperty(value = "carrier")String carrier, 
            @JsonProperty(value = "email")String email, 
            @JsonProperty(value = "battery")BatteryDTO battery, 
            @JsonProperty(value = "storage")StorageDTO storage, 
            @JsonProperty(value = "bluetooth")BluetoothDTO bluetooth, 
            @JsonProperty(value = "wifi")WiFiDTO wifi, 
            @JsonProperty(value = "appsList")List<AppDataDTO> appsList) {
        this.imei = imei;
        this.serial = serial;
        this.model = model;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.version = version;
        this.carrier = carrier;
        this.email = email;
        this.battery = battery;
        this.storage = storage;
        this.bluetooth = bluetooth;
        this.wifi = wifi;
        this.appsList = appsList;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BatteryDTO getBattery() {
        return battery;
    }

    public void setBattery(BatteryDTO battery) {
        this.battery = battery;
    }

    public StorageDTO getStorage() {
        return storage;
    }

    public void setStorage(StorageDTO storage) {
        this.storage = storage;
    }

    public BluetoothDTO getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(BluetoothDTO bluetooth) {
        this.bluetooth = bluetooth;
    }

    public WiFiDTO getWifi() {
        return wifi;
    }

    public void setWifi(WiFiDTO wifi) {
        this.wifi = wifi;
    }

    public List<AppDataDTO> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<AppDataDTO> appsList) {
        this.appsList = appsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DiagnosticoAppDTO{" + "imei=" + imei + ", serial=" + serial 
                + ", model=" + model + ", brand=" + brand 
                + ", manufacturer=" + manufacturer + ", version=" + version 
                + ", carrier=" + carrier + ", email=" + email + ", battery=" + battery 
                + ", storage=" + storage + ", bluetooth=" + bluetooth + ", wifi=" 
                + wifi + ", appsList=" + appsList + '}';
    }
    
    
    
}

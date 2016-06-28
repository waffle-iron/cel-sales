package com.mx.kiibal.celsales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.mx.kiibal.celsales.domain.enumeration.EstadoBateria;

/**
 * A Diagnostico.
 */
@Entity
@Table(name = "diagnostico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "imei")
    private String imei;

    @Column(name = "serial")
    private String serial;

    @Column(name = "temp_bateria")
    private String tempBateria;

    @Column(name = "tec_bateria")
    private String tecBateria;

    @Column(name = "volt_bateria")
    private String voltBateria;

    @Column(name = "cap_bateria")
    private String capBateria;

    @Column(name = "fuente_energia")
    private String fuenteEnergia;

    @Column(name = "version_so")
    private String versionSO;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_bateria")
    private EstadoBateria estadoBateria;

    @Column(name = "alm_interno_total")
    private String almInternoTotal;

    @Column(name = "alm_interno_disp")
    private String almInternoDisp;

    @Column(name = "alm_externo_total")
    private String almExternoTotal;

    @Column(name = "alm_externo_disp")
    private String almExternoDisp;

    @Column(name = "estado_carga")
    private String estadoCarga;

    @Column(name = "porcentaje_carga")
    private String porcentajeCarga;

    @Column(name = "bluetooth_enabled")
    private Boolean bluetoothEnabled;

    @Column(name = "bluetooth_mac_addr")
    private String bluetoothMacAddr;

    @Column(name = "bluetooth_name")
    private String bluetoothName;

    @Column(name = "wifi_enabled")
    private Boolean wifiEnabled;

    @Column(name = "wifi_mac_addr")
    private String wifiMacAddr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTempBateria() {
        return tempBateria;
    }

    public void setTempBateria(String tempBateria) {
        this.tempBateria = tempBateria;
    }

    public String getTecBateria() {
        return tecBateria;
    }

    public void setTecBateria(String tecBateria) {
        this.tecBateria = tecBateria;
    }

    public String getVoltBateria() {
        return voltBateria;
    }

    public void setVoltBateria(String voltBateria) {
        this.voltBateria = voltBateria;
    }

    public String getCapBateria() {
        return capBateria;
    }

    public void setCapBateria(String capBateria) {
        this.capBateria = capBateria;
    }

    public String getFuenteEnergia() {
        return fuenteEnergia;
    }

    public void setFuenteEnergia(String fuenteEnergia) {
        this.fuenteEnergia = fuenteEnergia;
    }

    public String getVersionSO() {
        return versionSO;
    }

    public void setVersionSO(String versionSO) {
        this.versionSO = versionSO;
    }

    public EstadoBateria getEstadoBateria() {
        return estadoBateria;
    }

    public void setEstadoBateria(EstadoBateria estadoBateria) {
        this.estadoBateria = estadoBateria;
    }

    public String getAlmInternoTotal() {
        return almInternoTotal;
    }

    public void setAlmInternoTotal(String almInternoTotal) {
        this.almInternoTotal = almInternoTotal;
    }

    public String getAlmInternoDisp() {
        return almInternoDisp;
    }

    public void setAlmInternoDisp(String almInternoDisp) {
        this.almInternoDisp = almInternoDisp;
    }

    public String getAlmExternoTotal() {
        return almExternoTotal;
    }

    public void setAlmExternoTotal(String almExternoTotal) {
        this.almExternoTotal = almExternoTotal;
    }

    public String getAlmExternoDisp() {
        return almExternoDisp;
    }

    public void setAlmExternoDisp(String almExternoDisp) {
        this.almExternoDisp = almExternoDisp;
    }

    public String getEstadoCarga() {
        return estadoCarga;
    }

    public void setEstadoCarga(String estadoCarga) {
        this.estadoCarga = estadoCarga;
    }

    public String getPorcentajeCarga() {
        return porcentajeCarga;
    }

    public void setPorcentajeCarga(String porcentajeCarga) {
        this.porcentajeCarga = porcentajeCarga;
    }

    public Boolean isBluetoothEnabled() {
        return bluetoothEnabled;
    }

    public void setBluetoothEnabled(Boolean bluetoothEnabled) {
        this.bluetoothEnabled = bluetoothEnabled;
    }

    public String getBluetoothMacAddr() {
        return bluetoothMacAddr;
    }

    public void setBluetoothMacAddr(String bluetoothMacAddr) {
        this.bluetoothMacAddr = bluetoothMacAddr;
    }

    public String getBluetoothName() {
        return bluetoothName;
    }

    public void setBluetoothName(String bluetoothName) {
        this.bluetoothName = bluetoothName;
    }

    public Boolean isWifiEnabled() {
        return wifiEnabled;
    }

    public void setWifiEnabled(Boolean wifiEnabled) {
        this.wifiEnabled = wifiEnabled;
    }

    public String getWifiMacAddr() {
        return wifiMacAddr;
    }

    public void setWifiMacAddr(String wifiMacAddr) {
        this.wifiMacAddr = wifiMacAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diagnostico diagnostico = (Diagnostico) o;
        if(diagnostico.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, diagnostico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Diagnostico{" +
            "id=" + id +
            ", imei='" + imei + "'" +
            ", serial='" + serial + "'" +
            ", tempBateria='" + tempBateria + "'" +
            ", tecBateria='" + tecBateria + "'" +
            ", voltBateria='" + voltBateria + "'" +
            ", capBateria='" + capBateria + "'" +
            ", fuenteEnergia='" + fuenteEnergia + "'" +
            ", versionSO='" + versionSO + "'" +
            ", estadoBateria='" + estadoBateria + "'" +
            ", almInternoTotal='" + almInternoTotal + "'" +
            ", almInternoDisp='" + almInternoDisp + "'" +
            ", almExternoTotal='" + almExternoTotal + "'" +
            ", almExternoDisp='" + almExternoDisp + "'" +
            ", estadoCarga='" + estadoCarga + "'" +
            ", porcentajeCarga='" + porcentajeCarga + "'" +
            ", bluetoothEnabled='" + bluetoothEnabled + "'" +
            ", bluetoothMacAddr='" + bluetoothMacAddr + "'" +
            ", bluetoothName='" + bluetoothName + "'" +
            ", wifiEnabled='" + wifiEnabled + "'" +
            ", wifiMacAddr='" + wifiMacAddr + "'" +
            '}';
    }
}

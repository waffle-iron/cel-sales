package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;

import com.mx.kiibal.celsales.domain.enumeration.EstadoBateria;

/**
 * A DTO for the Diagnostico entity.
 */
public class DiagnosticoDTO implements Serializable {
    private static final long serialVersionUID = 2154672924538183131L;

    private Long id;

    private String imei;

    private String serial;

    private String tempBateria;

    private String tecBateria;

    private String voltBateria;

    private String capBateria;

    private String fuenteEnergia;

    private String versionSO;

    private EstadoBateria estadoBateria;

    private String almInternoTotal;

    private String almInternoDisp;

    private String almExternoTotal;

    private String almExternoDisp;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiagnosticoDTO diagnosticoDTO = (DiagnosticoDTO) o;

        if ( ! Objects.equals(id, diagnosticoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DiagnosticoDTO{" +
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
            '}';
    }
}

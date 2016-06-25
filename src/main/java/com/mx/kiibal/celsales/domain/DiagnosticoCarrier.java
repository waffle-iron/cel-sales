package com.mx.kiibal.celsales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DiagnosticoCarrier.
 */
@Entity
@Table(name = "diagnostico_carrier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DiagnosticoCarrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "es_origen")
    private Boolean esOrigen;

    @ManyToOne
    private Diagnostico diagnostico;

    @ManyToOne
    private Carrier carrier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isEsOrigen() {
        return esOrigen;
    }

    public void setEsOrigen(Boolean esOrigen) {
        this.esOrigen = esOrigen;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiagnosticoCarrier diagnosticoCarrier = (DiagnosticoCarrier) o;
        if(diagnosticoCarrier.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, diagnosticoCarrier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DiagnosticoCarrier{" +
            "id=" + id +
            ", esOrigen='" + esOrigen + "'" +
            '}';
    }
}

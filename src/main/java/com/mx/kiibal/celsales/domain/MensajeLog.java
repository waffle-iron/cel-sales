package com.mx.kiibal.celsales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MensajeLog.
 */
@Entity
@Table(name = "mensaje_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MensajeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "log_exception")
    private String logException;

    @Column(name = "log_exception_msg")
    private String logExceptionMsg;

    @Column(name = "log_package")
    private String logPackage;

    @ManyToOne
    private Diagnostico diagnostico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLogException() {
        return logException;
    }

    public void setLogException(String logException) {
        this.logException = logException;
    }

    public String getLogExceptionMsg() {
        return logExceptionMsg;
    }

    public void setLogExceptionMsg(String logExceptionMsg) {
        this.logExceptionMsg = logExceptionMsg;
    }

    public String getLogPackage() {
        return logPackage;
    }

    public void setLogPackage(String logPackage) {
        this.logPackage = logPackage;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MensajeLog mensajeLog = (MensajeLog) o;
        if(mensajeLog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, mensajeLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MensajeLog{" +
            "id=" + id +
            ", fecha='" + fecha + "'" +
            ", logException='" + logException + "'" +
            ", logExceptionMsg='" + logExceptionMsg + "'" +
            ", logPackage='" + logPackage + "'" +
            '}';
    }
}

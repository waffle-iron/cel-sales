package com.mx.kiibal.celsales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AppNoOfc.
 */
@Entity
@Table(name = "app_no_ofc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppNoOfc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "icono")
    private byte[] icono;

    @Column(name = "icono_content_type")
    private String iconoContentType;

    @Column(name = "version")
    private String version;

    @Column(name = "empaquetado")
    private String empaquetado;

    @Column(name = "fecha_instalacion")
    private String fechaInstalacion;

    @Column(name = "fecha_modificacion")
    private String fechaModificacion;

    @ManyToOne
    private Diagnostico diagnostico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    public String getIconoContentType() {
        return iconoContentType;
    }

    public void setIconoContentType(String iconoContentType) {
        this.iconoContentType = iconoContentType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEmpaquetado() {
        return empaquetado;
    }

    public void setEmpaquetado(String empaquetado) {
        this.empaquetado = empaquetado;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
        AppNoOfc appNoOfc = (AppNoOfc) o;
        if(appNoOfc.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, appNoOfc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AppNoOfc{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", icono='" + icono + "'" +
            ", iconoContentType='" + iconoContentType + "'" +
            ", version='" + version + "'" +
            ", empaquetado='" + empaquetado + "'" +
            ", fechaInstalacion='" + fechaInstalacion + "'" +
            ", fechaModificacion='" + fechaModificacion + "'" +
            '}';
    }
}

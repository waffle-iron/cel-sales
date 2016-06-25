package com.mx.kiibal.celsales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UsuarioDiagnostico.
 */
@Entity
@Table(name = "usuario_diagnostico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsuarioDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "es_creador")
    private Boolean esCreador;

    @ManyToOne
    private Diagnostico diagnostico;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isEsCreador() {
        return esCreador;
    }

    public void setEsCreador(Boolean esCreador) {
        this.esCreador = esCreador;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioDiagnostico usuarioDiagnostico = (UsuarioDiagnostico) o;
        if(usuarioDiagnostico.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, usuarioDiagnostico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UsuarioDiagnostico{" +
            "id=" + id +
            ", esCreador='" + esCreador + "'" +
            '}';
    }
}

package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;


/**
 * A DTO for the Fabricante entity.
 */
public class FabricanteDTO implements Serializable {

    private Long id;

    private String nombre;

    @Lob
    private byte[] imagen;

    private String imagenContentType;

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
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FabricanteDTO fabricanteDTO = (FabricanteDTO) o;

        if ( ! Objects.equals(id, fabricanteDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FabricanteDTO{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", imagen='" + imagen + "'" +
            '}';
    }
}

package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class StorageDTO implements Serializable{
    private static final long serialVersionUID = -3839065507922955050L;
    
    private InternalDTO internal;
    private ExternalDTO external;

    public StorageDTO() {}

    public StorageDTO(InternalDTO internal, ExternalDTO external) {
        this.internal = internal;
        this.external = external;
    }

    public InternalDTO getInternal() {
        return internal;
    }

    public void setInternal(InternalDTO internal) {
        this.internal = internal;
    }

    public ExternalDTO getExternal() {
        return external;
    }

    public void setExternal(ExternalDTO external) {
        this.external = external;
    }

    @Override
    public String toString() {
        return "StorageDTO{" + "internal=" + internal + ", external=" + external + '}';
    }
    
    
}

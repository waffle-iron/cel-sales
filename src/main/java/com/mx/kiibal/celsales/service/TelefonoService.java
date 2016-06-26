package com.mx.kiibal.celsales.service;

import com.mx.kiibal.celsales.domain.Telefono;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Telefono.
 */
public interface TelefonoService {

    /**
     * Save a telefono.
     * 
     * @param telefonoDTO the entity to save
     * @return the persisted entity
     */
    TelefonoDTO save(TelefonoDTO telefonoDTO);

    /**
     *  Get all the telefonos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Telefono> findAll(Pageable pageable);

    /**
     *  Get the "id" telefono.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    TelefonoDTO findOne(Long id);

    /**
     *  Delete the "id" telefono.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    Optional<Telefono> findByModelo(String modelo);
}

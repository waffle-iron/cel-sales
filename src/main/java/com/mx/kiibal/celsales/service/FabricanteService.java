package com.mx.kiibal.celsales.service;

import com.mx.kiibal.celsales.domain.Fabricante;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Fabricante.
 */
public interface FabricanteService {

    /**
     * Save a fabricante.
     * 
     * @param fabricanteDTO the entity to save
     * @return the persisted entity
     */
    FabricanteDTO save(FabricanteDTO fabricanteDTO);

    /**
     *  Get all the fabricantes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Fabricante> findAll(Pageable pageable);

    /**
     *  Get the "id" fabricante.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    FabricanteDTO findOne(Long id);

    /**
     *  Delete the "id" fabricante.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);
}

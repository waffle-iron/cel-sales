package com.mx.kiibal.celsales.service;

import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Diagnostico.
 */
public interface DiagnosticoService {

    /**
     * Save a diagnostico.
     * 
     * @param diagnosticoDTO the entity to save
     * @return the persisted entity
     */
    DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO);

    /**
     *  Get all the diagnosticos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Diagnostico> findAll(Pageable pageable);

    /**
     *  Get the "id" diagnostico.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    DiagnosticoDTO findOne(Long id);

    /**
     *  Delete the "id" diagnostico.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);
}

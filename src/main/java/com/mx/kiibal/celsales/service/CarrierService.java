package com.mx.kiibal.celsales.service;

import com.mx.kiibal.celsales.domain.Carrier;
import com.mx.kiibal.celsales.web.rest.dto.CarrierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Carrier.
 */
public interface CarrierService {

    /**
     * Save a carrier.
     * 
     * @param carrierDTO the entity to save
     * @return the persisted entity
     */
    CarrierDTO save(CarrierDTO carrierDTO);

    /**
     *  Get all the carriers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Carrier> findAll(Pageable pageable);

    /**
     *  Get the "id" carrier.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    CarrierDTO findOne(Long id);

    /**
     *  Delete the "id" carrier.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package com.mx.kiibal.celsales.service.impl;

import com.mx.kiibal.celsales.service.CarrierService;
import com.mx.kiibal.celsales.domain.Carrier;
import com.mx.kiibal.celsales.repository.CarrierRepository;
import com.mx.kiibal.celsales.web.rest.dto.CarrierDTO;
import com.mx.kiibal.celsales.web.rest.mapper.CarrierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Carrier.
 */
@Service
@Transactional
public class CarrierServiceImpl implements CarrierService{

    private final Logger log = LoggerFactory.getLogger(CarrierServiceImpl.class);
    
    @Inject
    private CarrierRepository carrierRepository;
    
    @Inject
    private CarrierMapper carrierMapper;
    
    /**
     * Save a carrier.
     * 
     * @param carrierDTO the entity to save
     * @return the persisted entity
     */
    public CarrierDTO save(CarrierDTO carrierDTO) {
        log.debug("Request to save Carrier : {}", carrierDTO);
        Carrier carrier = carrierMapper.carrierDTOToCarrier(carrierDTO);
        carrier = carrierRepository.save(carrier);
        CarrierDTO result = carrierMapper.carrierToCarrierDTO(carrier);
        return result;
    }

    /**
     *  Get all the carriers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Carrier> findAll(Pageable pageable) {
        log.debug("Request to get all Carriers");
        Page<Carrier> result = carrierRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one carrier by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CarrierDTO findOne(Long id) {
        log.debug("Request to get Carrier : {}", id);
        Carrier carrier = carrierRepository.findOne(id);
        CarrierDTO carrierDTO = carrierMapper.carrierToCarrierDTO(carrier);
        return carrierDTO;
    }

    /**
     *  Delete the  carrier by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Carrier : {}", id);
        carrierRepository.delete(id);
    }
}

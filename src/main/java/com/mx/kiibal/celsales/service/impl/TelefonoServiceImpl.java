package com.mx.kiibal.celsales.service.impl;

import com.mx.kiibal.celsales.service.TelefonoService;
import com.mx.kiibal.celsales.domain.Telefono;
import com.mx.kiibal.celsales.repository.TelefonoRepository;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.TelefonoMapper;
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
 * Service Implementation for managing Telefono.
 */
@Service
@Transactional
public class TelefonoServiceImpl implements TelefonoService{

    private final Logger log = LoggerFactory.getLogger(TelefonoServiceImpl.class);
    
    @Inject
    private TelefonoRepository telefonoRepository;
    
    @Inject
    private TelefonoMapper telefonoMapper;
    
    /**
     * Save a telefono.
     * 
     * @param telefonoDTO the entity to save
     * @return the persisted entity
     */
    public TelefonoDTO save(TelefonoDTO telefonoDTO) {
        log.debug("Request to save Telefono : {}", telefonoDTO);
        Telefono telefono = telefonoMapper.telefonoDTOToTelefono(telefonoDTO);
        telefono = telefonoRepository.save(telefono);
        TelefonoDTO result = telefonoMapper.telefonoToTelefonoDTO(telefono);
        return result;
    }

    /**
     *  Get all the telefonos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Telefono> findAll(Pageable pageable) {
        log.debug("Request to get all Telefonos");
        Page<Telefono> result = telefonoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one telefono by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public TelefonoDTO findOne(Long id) {
        log.debug("Request to get Telefono : {}", id);
        Telefono telefono = telefonoRepository.findOne(id);
        TelefonoDTO telefonoDTO = telefonoMapper.telefonoToTelefonoDTO(telefono);
        return telefonoDTO;
    }

    /**
     *  Delete the  telefono by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Telefono : {}", id);
        telefonoRepository.delete(id);
    }
}

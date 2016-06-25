package com.mx.kiibal.celsales.service.impl;

import com.mx.kiibal.celsales.service.DiagnosticoService;
import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.repository.DiagnosticoRepository;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.DiagnosticoMapper;
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
 * Service Implementation for managing Diagnostico.
 */
@Service
@Transactional
public class DiagnosticoServiceImpl implements DiagnosticoService{

    private final Logger log = LoggerFactory.getLogger(DiagnosticoServiceImpl.class);
    
    @Inject
    private DiagnosticoRepository diagnosticoRepository;
    
    @Inject
    private DiagnosticoMapper diagnosticoMapper;
    
    /**
     * Save a diagnostico.
     * 
     * @param diagnosticoDTO the entity to save
     * @return the persisted entity
     */
    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
        log.debug("Request to save Diagnostico : {}", diagnosticoDTO);
        Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
        diagnostico = diagnosticoRepository.save(diagnostico);
        DiagnosticoDTO result = diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
        return result;
    }

    /**
     *  Get all the diagnosticos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Diagnostico> findAll(Pageable pageable) {
        log.debug("Request to get all Diagnosticos");
        Page<Diagnostico> result = diagnosticoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one diagnostico by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DiagnosticoDTO findOne(Long id) {
        log.debug("Request to get Diagnostico : {}", id);
        Diagnostico diagnostico = diagnosticoRepository.findOne(id);
        DiagnosticoDTO diagnosticoDTO = diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
        return diagnosticoDTO;
    }

    /**
     *  Delete the  diagnostico by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Diagnostico : {}", id);
        diagnosticoRepository.delete(id);
    }
}

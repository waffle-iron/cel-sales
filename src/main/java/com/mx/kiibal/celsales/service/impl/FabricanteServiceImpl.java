package com.mx.kiibal.celsales.service.impl;

import com.mx.kiibal.celsales.service.FabricanteService;
import com.mx.kiibal.celsales.domain.Fabricante;
import com.mx.kiibal.celsales.repository.FabricanteRepository;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;
import com.mx.kiibal.celsales.web.rest.mapper.FabricanteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Fabricante.
 */
@Service
@Transactional
public class FabricanteServiceImpl implements FabricanteService{

    private final Logger log = LoggerFactory.getLogger(FabricanteServiceImpl.class);
    
    @Inject
    private FabricanteRepository fabricanteRepository;
    
    @Inject
    private FabricanteMapper fabricanteMapper;
    
    /**
     * Save a fabricante.
     * 
     * @param fabricanteDTO the entity to save
     * @return the persisted entity
     */
    public FabricanteDTO save(FabricanteDTO fabricanteDTO) {
        log.debug("Request to save Fabricante : {}", fabricanteDTO);
        Fabricante fabricante = fabricanteMapper.fabricanteDTOToFabricante(fabricanteDTO);
        fabricante.setNombre(fabricanteDTO.getNombre().toUpperCase());
        fabricante = fabricanteRepository.save(fabricante);
        FabricanteDTO result = fabricanteMapper.fabricanteToFabricanteDTO(fabricante);
        return result;
    }

    /**
     *  Get all the fabricantes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Fabricante> findAll(Pageable pageable) {
        log.debug("Request to get all Fabricantes");
        Page<Fabricante> result = fabricanteRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one fabricante by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FabricanteDTO findOne(Long id) {
        log.debug("Request to get Fabricante : {}", id);
        Fabricante fabricante = fabricanteRepository.findOne(id);
        FabricanteDTO fabricanteDTO = fabricanteMapper.fabricanteToFabricanteDTO(fabricante);
        return fabricanteDTO;
    }

    /**
     *  Delete the  fabricante by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Fabricante : {}", id);
        fabricanteRepository.delete(id);
    }

    @Override
    public Optional<Fabricante> findByNombre(String nombre) {
        return fabricanteRepository.findByNombre(nombre.toUpperCase());
    }
}

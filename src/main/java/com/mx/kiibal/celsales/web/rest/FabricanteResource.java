package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.Fabricante;
import com.mx.kiibal.celsales.service.FabricanteService;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import com.mx.kiibal.celsales.web.rest.util.PaginationUtil;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;
import com.mx.kiibal.celsales.web.rest.mapper.FabricanteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Fabricante.
 */
@RestController
@RequestMapping("/api")
public class FabricanteResource {

    private final Logger log = LoggerFactory.getLogger(FabricanteResource.class);
        
    @Inject
    private FabricanteService fabricanteService;
    
    @Inject
    private FabricanteMapper fabricanteMapper;
    
    /**
     * POST  /fabricantes : Create a new fabricante.
     *
     * @param fabricanteDTO the fabricanteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fabricanteDTO, or with status 400 (Bad Request) if the fabricante has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/fabricantes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FabricanteDTO> createFabricante(@RequestBody FabricanteDTO fabricanteDTO) throws URISyntaxException {
        log.debug("REST request to save Fabricante : {}", fabricanteDTO);
        if (fabricanteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("fabricante", "idexists", "A new fabricante cannot already have an ID")).body(null);
        }
        FabricanteDTO result = fabricanteService.save(fabricanteDTO);
        return ResponseEntity.created(new URI("/api/fabricantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("fabricante", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fabricantes : Updates an existing fabricante.
     *
     * @param fabricanteDTO the fabricanteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fabricanteDTO,
     * or with status 400 (Bad Request) if the fabricanteDTO is not valid,
     * or with status 500 (Internal Server Error) if the fabricanteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/fabricantes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FabricanteDTO> updateFabricante(@RequestBody FabricanteDTO fabricanteDTO) throws URISyntaxException {
        log.debug("REST request to update Fabricante : {}", fabricanteDTO);
        if (fabricanteDTO.getId() == null) {
            return createFabricante(fabricanteDTO);
        }
        FabricanteDTO result = fabricanteService.save(fabricanteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("fabricante", fabricanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fabricantes : get all the fabricantes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fabricantes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/fabricantes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<FabricanteDTO>> getAllFabricantes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Fabricantes");
        Page<Fabricante> page = fabricanteService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fabricantes");
        return new ResponseEntity<>(fabricanteMapper.fabricantesToFabricanteDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /fabricantes/:id : get the "id" fabricante.
     *
     * @param id the id of the fabricanteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fabricanteDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/fabricantes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FabricanteDTO> getFabricante(@PathVariable Long id) {
        log.debug("REST request to get Fabricante : {}", id);
        FabricanteDTO fabricanteDTO = fabricanteService.findOne(id);
        return Optional.ofNullable(fabricanteDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fabricantes/:id : delete the "id" fabricante.
     *
     * @param id the id of the fabricanteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/fabricantes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFabricante(@PathVariable Long id) {
        log.debug("REST request to delete Fabricante : {}", id);
        fabricanteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("fabricante", id.toString())).build();
    }

}

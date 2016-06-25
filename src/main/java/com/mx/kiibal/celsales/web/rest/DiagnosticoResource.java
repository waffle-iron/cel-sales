package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.service.DiagnosticoService;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import com.mx.kiibal.celsales.web.rest.util.PaginationUtil;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.DiagnosticoMapper;
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
 * REST controller for managing Diagnostico.
 */
@RestController
@RequestMapping("/api")
public class DiagnosticoResource {

    private final Logger log = LoggerFactory.getLogger(DiagnosticoResource.class);
        
    @Inject
    private DiagnosticoService diagnosticoService;
    
    @Inject
    private DiagnosticoMapper diagnosticoMapper;
    
    /**
     * POST  /diagnosticos : Create a new diagnostico.
     *
     * @param diagnosticoDTO the diagnosticoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diagnosticoDTO, or with status 400 (Bad Request) if the diagnostico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/diagnosticos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoDTO> createDiagnostico(@RequestBody DiagnosticoDTO diagnosticoDTO) throws URISyntaxException {
        log.debug("REST request to save Diagnostico : {}", diagnosticoDTO);
        if (diagnosticoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("diagnostico", "idexists", "A new diagnostico cannot already have an ID")).body(null);
        }
        DiagnosticoDTO result = diagnosticoService.save(diagnosticoDTO);
        return ResponseEntity.created(new URI("/api/diagnosticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("diagnostico", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diagnosticos : Updates an existing diagnostico.
     *
     * @param diagnosticoDTO the diagnosticoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diagnosticoDTO,
     * or with status 400 (Bad Request) if the diagnosticoDTO is not valid,
     * or with status 500 (Internal Server Error) if the diagnosticoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/diagnosticos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoDTO> updateDiagnostico(@RequestBody DiagnosticoDTO diagnosticoDTO) throws URISyntaxException {
        log.debug("REST request to update Diagnostico : {}", diagnosticoDTO);
        if (diagnosticoDTO.getId() == null) {
            return createDiagnostico(diagnosticoDTO);
        }
        DiagnosticoDTO result = diagnosticoService.save(diagnosticoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("diagnostico", diagnosticoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diagnosticos : get all the diagnosticos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diagnosticos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/diagnosticos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DiagnosticoDTO>> getAllDiagnosticos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Diagnosticos");
        Page<Diagnostico> page = diagnosticoService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/diagnosticos");
        return new ResponseEntity<>(diagnosticoMapper.diagnosticosToDiagnosticoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /diagnosticos/:id : get the "id" diagnostico.
     *
     * @param id the id of the diagnosticoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diagnosticoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/diagnosticos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoDTO> getDiagnostico(@PathVariable Long id) {
        log.debug("REST request to get Diagnostico : {}", id);
        DiagnosticoDTO diagnosticoDTO = diagnosticoService.findOne(id);
        return Optional.ofNullable(diagnosticoDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /diagnosticos/:id : delete the "id" diagnostico.
     *
     * @param id the id of the diagnosticoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/diagnosticos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDiagnostico(@PathVariable Long id) {
        log.debug("REST request to delete Diagnostico : {}", id);
        diagnosticoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("diagnostico", id.toString())).build();
    }

}

package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.DiagnosticoCarrier;
import com.mx.kiibal.celsales.repository.DiagnosticoCarrierRepository;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DiagnosticoCarrier.
 */
@RestController
@RequestMapping("/api")
public class DiagnosticoCarrierResource {

    private final Logger log = LoggerFactory.getLogger(DiagnosticoCarrierResource.class);
        
    @Inject
    private DiagnosticoCarrierRepository diagnosticoCarrierRepository;
    
    /**
     * POST  /diagnostico-carriers : Create a new diagnosticoCarrier.
     *
     * @param diagnosticoCarrier the diagnosticoCarrier to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diagnosticoCarrier, or with status 400 (Bad Request) if the diagnosticoCarrier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/diagnostico-carriers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoCarrier> createDiagnosticoCarrier(@RequestBody DiagnosticoCarrier diagnosticoCarrier) throws URISyntaxException {
        log.debug("REST request to save DiagnosticoCarrier : {}", diagnosticoCarrier);
        if (diagnosticoCarrier.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("diagnosticoCarrier", "idexists", "A new diagnosticoCarrier cannot already have an ID")).body(null);
        }
        DiagnosticoCarrier result = diagnosticoCarrierRepository.save(diagnosticoCarrier);
        return ResponseEntity.created(new URI("/api/diagnostico-carriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("diagnosticoCarrier", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diagnostico-carriers : Updates an existing diagnosticoCarrier.
     *
     * @param diagnosticoCarrier the diagnosticoCarrier to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diagnosticoCarrier,
     * or with status 400 (Bad Request) if the diagnosticoCarrier is not valid,
     * or with status 500 (Internal Server Error) if the diagnosticoCarrier couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/diagnostico-carriers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoCarrier> updateDiagnosticoCarrier(@RequestBody DiagnosticoCarrier diagnosticoCarrier) throws URISyntaxException {
        log.debug("REST request to update DiagnosticoCarrier : {}", diagnosticoCarrier);
        if (diagnosticoCarrier.getId() == null) {
            return createDiagnosticoCarrier(diagnosticoCarrier);
        }
        DiagnosticoCarrier result = diagnosticoCarrierRepository.save(diagnosticoCarrier);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("diagnosticoCarrier", diagnosticoCarrier.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diagnostico-carriers : get all the diagnosticoCarriers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of diagnosticoCarriers in body
     */
    @RequestMapping(value = "/diagnostico-carriers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<DiagnosticoCarrier> getAllDiagnosticoCarriers() {
        log.debug("REST request to get all DiagnosticoCarriers");
        List<DiagnosticoCarrier> diagnosticoCarriers = diagnosticoCarrierRepository.findAll();
        return diagnosticoCarriers;
    }

    /**
     * GET  /diagnostico-carriers/:id : get the "id" diagnosticoCarrier.
     *
     * @param id the id of the diagnosticoCarrier to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diagnosticoCarrier, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/diagnostico-carriers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DiagnosticoCarrier> getDiagnosticoCarrier(@PathVariable Long id) {
        log.debug("REST request to get DiagnosticoCarrier : {}", id);
        DiagnosticoCarrier diagnosticoCarrier = diagnosticoCarrierRepository.findOne(id);
        return Optional.ofNullable(diagnosticoCarrier)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /diagnostico-carriers/:id : delete the "id" diagnosticoCarrier.
     *
     * @param id the id of the diagnosticoCarrier to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/diagnostico-carriers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDiagnosticoCarrier(@PathVariable Long id) {
        log.debug("REST request to delete DiagnosticoCarrier : {}", id);
        diagnosticoCarrierRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("diagnosticoCarrier", id.toString())).build();
    }

}

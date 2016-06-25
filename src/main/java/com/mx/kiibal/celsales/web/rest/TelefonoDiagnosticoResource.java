package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.TelefonoDiagnostico;
import com.mx.kiibal.celsales.repository.TelefonoDiagnosticoRepository;
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
 * REST controller for managing TelefonoDiagnostico.
 */
@RestController
@RequestMapping("/api")
public class TelefonoDiagnosticoResource {

    private final Logger log = LoggerFactory.getLogger(TelefonoDiagnosticoResource.class);
        
    @Inject
    private TelefonoDiagnosticoRepository telefonoDiagnosticoRepository;
    
    /**
     * POST  /telefono-diagnosticos : Create a new telefonoDiagnostico.
     *
     * @param telefonoDiagnostico the telefonoDiagnostico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new telefonoDiagnostico, or with status 400 (Bad Request) if the telefonoDiagnostico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/telefono-diagnosticos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDiagnostico> createTelefonoDiagnostico(@RequestBody TelefonoDiagnostico telefonoDiagnostico) throws URISyntaxException {
        log.debug("REST request to save TelefonoDiagnostico : {}", telefonoDiagnostico);
        if (telefonoDiagnostico.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("telefonoDiagnostico", "idexists", "A new telefonoDiagnostico cannot already have an ID")).body(null);
        }
        TelefonoDiagnostico result = telefonoDiagnosticoRepository.save(telefonoDiagnostico);
        return ResponseEntity.created(new URI("/api/telefono-diagnosticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("telefonoDiagnostico", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /telefono-diagnosticos : Updates an existing telefonoDiagnostico.
     *
     * @param telefonoDiagnostico the telefonoDiagnostico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated telefonoDiagnostico,
     * or with status 400 (Bad Request) if the telefonoDiagnostico is not valid,
     * or with status 500 (Internal Server Error) if the telefonoDiagnostico couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/telefono-diagnosticos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDiagnostico> updateTelefonoDiagnostico(@RequestBody TelefonoDiagnostico telefonoDiagnostico) throws URISyntaxException {
        log.debug("REST request to update TelefonoDiagnostico : {}", telefonoDiagnostico);
        if (telefonoDiagnostico.getId() == null) {
            return createTelefonoDiagnostico(telefonoDiagnostico);
        }
        TelefonoDiagnostico result = telefonoDiagnosticoRepository.save(telefonoDiagnostico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("telefonoDiagnostico", telefonoDiagnostico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /telefono-diagnosticos : get all the telefonoDiagnosticos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of telefonoDiagnosticos in body
     */
    @RequestMapping(value = "/telefono-diagnosticos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TelefonoDiagnostico> getAllTelefonoDiagnosticos() {
        log.debug("REST request to get all TelefonoDiagnosticos");
        List<TelefonoDiagnostico> telefonoDiagnosticos = telefonoDiagnosticoRepository.findAll();
        return telefonoDiagnosticos;
    }

    /**
     * GET  /telefono-diagnosticos/:id : get the "id" telefonoDiagnostico.
     *
     * @param id the id of the telefonoDiagnostico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the telefonoDiagnostico, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/telefono-diagnosticos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDiagnostico> getTelefonoDiagnostico(@PathVariable Long id) {
        log.debug("REST request to get TelefonoDiagnostico : {}", id);
        TelefonoDiagnostico telefonoDiagnostico = telefonoDiagnosticoRepository.findOne(id);
        return Optional.ofNullable(telefonoDiagnostico)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /telefono-diagnosticos/:id : delete the "id" telefonoDiagnostico.
     *
     * @param id the id of the telefonoDiagnostico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/telefono-diagnosticos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTelefonoDiagnostico(@PathVariable Long id) {
        log.debug("REST request to delete TelefonoDiagnostico : {}", id);
        telefonoDiagnosticoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("telefonoDiagnostico", id.toString())).build();
    }

}

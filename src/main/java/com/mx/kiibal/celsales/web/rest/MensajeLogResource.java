package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.MensajeLog;
import com.mx.kiibal.celsales.repository.MensajeLogRepository;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import com.mx.kiibal.celsales.web.rest.util.PaginationUtil;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MensajeLog.
 */
@RestController
@RequestMapping("/api")
public class MensajeLogResource {

    private final Logger log = LoggerFactory.getLogger(MensajeLogResource.class);
        
    @Inject
    private MensajeLogRepository mensajeLogRepository;
    
    /**
     * POST  /mensaje-logs : Create a new mensajeLog.
     *
     * @param mensajeLog the mensajeLog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mensajeLog, or with status 400 (Bad Request) if the mensajeLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/mensaje-logs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MensajeLog> createMensajeLog(@RequestBody MensajeLog mensajeLog) throws URISyntaxException {
        log.debug("REST request to save MensajeLog : {}", mensajeLog);
        if (mensajeLog.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("mensajeLog", "idexists", "A new mensajeLog cannot already have an ID")).body(null);
        }
        MensajeLog result = mensajeLogRepository.save(mensajeLog);
        return ResponseEntity.created(new URI("/api/mensaje-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mensajeLog", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mensaje-logs : Updates an existing mensajeLog.
     *
     * @param mensajeLog the mensajeLog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mensajeLog,
     * or with status 400 (Bad Request) if the mensajeLog is not valid,
     * or with status 500 (Internal Server Error) if the mensajeLog couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/mensaje-logs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MensajeLog> updateMensajeLog(@RequestBody MensajeLog mensajeLog) throws URISyntaxException {
        log.debug("REST request to update MensajeLog : {}", mensajeLog);
        if (mensajeLog.getId() == null) {
            return createMensajeLog(mensajeLog);
        }
        MensajeLog result = mensajeLogRepository.save(mensajeLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mensajeLog", mensajeLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mensaje-logs : get all the mensajeLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mensajeLogs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/mensaje-logs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MensajeLog>> getAllMensajeLogs(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MensajeLogs");
        Page<MensajeLog> page = mensajeLogRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mensaje-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mensaje-logs/:id : get the "id" mensajeLog.
     *
     * @param id the id of the mensajeLog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mensajeLog, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/mensaje-logs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MensajeLog> getMensajeLog(@PathVariable Long id) {
        log.debug("REST request to get MensajeLog : {}", id);
        MensajeLog mensajeLog = mensajeLogRepository.findOne(id);
        return Optional.ofNullable(mensajeLog)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /mensaje-logs/:id : delete the "id" mensajeLog.
     *
     * @param id the id of the mensajeLog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/mensaje-logs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMensajeLog(@PathVariable Long id) {
        log.debug("REST request to delete MensajeLog : {}", id);
        mensajeLogRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mensajeLog", id.toString())).build();
    }

}

package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.AppNoOfc;
import com.mx.kiibal.celsales.repository.AppNoOfcRepository;
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
 * REST controller for managing AppNoOfc.
 */
@RestController
@RequestMapping("/api")
public class AppNoOfcResource {

    private final Logger log = LoggerFactory.getLogger(AppNoOfcResource.class);
        
    @Inject
    private AppNoOfcRepository appNoOfcRepository;
    
    /**
     * POST  /app-no-ofcs : Create a new appNoOfc.
     *
     * @param appNoOfc the appNoOfc to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appNoOfc, or with status 400 (Bad Request) if the appNoOfc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/app-no-ofcs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppNoOfc> createAppNoOfc(@RequestBody AppNoOfc appNoOfc) throws URISyntaxException {
        log.debug("REST request to save AppNoOfc : {}", appNoOfc);
        if (appNoOfc.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("appNoOfc", "idexists", "A new appNoOfc cannot already have an ID")).body(null);
        }
        AppNoOfc result = appNoOfcRepository.save(appNoOfc);
        return ResponseEntity.created(new URI("/api/app-no-ofcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("appNoOfc", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-no-ofcs : Updates an existing appNoOfc.
     *
     * @param appNoOfc the appNoOfc to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appNoOfc,
     * or with status 400 (Bad Request) if the appNoOfc is not valid,
     * or with status 500 (Internal Server Error) if the appNoOfc couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/app-no-ofcs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppNoOfc> updateAppNoOfc(@RequestBody AppNoOfc appNoOfc) throws URISyntaxException {
        log.debug("REST request to update AppNoOfc : {}", appNoOfc);
        if (appNoOfc.getId() == null) {
            return createAppNoOfc(appNoOfc);
        }
        AppNoOfc result = appNoOfcRepository.save(appNoOfc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("appNoOfc", appNoOfc.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-no-ofcs : get all the appNoOfcs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of appNoOfcs in body
     */
    @RequestMapping(value = "/app-no-ofcs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<AppNoOfc> getAllAppNoOfcs() {
        log.debug("REST request to get all AppNoOfcs");
        List<AppNoOfc> appNoOfcs = appNoOfcRepository.findAll();
        return appNoOfcs;
    }

    /**
     * GET  /app-no-ofcs/:id : get the "id" appNoOfc.
     *
     * @param id the id of the appNoOfc to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appNoOfc, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/app-no-ofcs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppNoOfc> getAppNoOfc(@PathVariable Long id) {
        log.debug("REST request to get AppNoOfc : {}", id);
        AppNoOfc appNoOfc = appNoOfcRepository.findOne(id);
        return Optional.ofNullable(appNoOfc)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /app-no-ofcs/:id : delete the "id" appNoOfc.
     *
     * @param id the id of the appNoOfc to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/app-no-ofcs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAppNoOfc(@PathVariable Long id) {
        log.debug("REST request to delete AppNoOfc : {}", id);
        appNoOfcRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("appNoOfc", id.toString())).build();
    }

}

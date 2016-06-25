package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.UsuarioDiagnostico;
import com.mx.kiibal.celsales.repository.UsuarioDiagnosticoRepository;
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
 * REST controller for managing UsuarioDiagnostico.
 */
@RestController
@RequestMapping("/api")
public class UsuarioDiagnosticoResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioDiagnosticoResource.class);
        
    @Inject
    private UsuarioDiagnosticoRepository usuarioDiagnosticoRepository;
    
    /**
     * POST  /usuario-diagnosticos : Create a new usuarioDiagnostico.
     *
     * @param usuarioDiagnostico the usuarioDiagnostico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioDiagnostico, or with status 400 (Bad Request) if the usuarioDiagnostico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/usuario-diagnosticos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UsuarioDiagnostico> createUsuarioDiagnostico(@RequestBody UsuarioDiagnostico usuarioDiagnostico) throws URISyntaxException {
        log.debug("REST request to save UsuarioDiagnostico : {}", usuarioDiagnostico);
        if (usuarioDiagnostico.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("usuarioDiagnostico", "idexists", "A new usuarioDiagnostico cannot already have an ID")).body(null);
        }
        UsuarioDiagnostico result = usuarioDiagnosticoRepository.save(usuarioDiagnostico);
        return ResponseEntity.created(new URI("/api/usuario-diagnosticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("usuarioDiagnostico", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuario-diagnosticos : Updates an existing usuarioDiagnostico.
     *
     * @param usuarioDiagnostico the usuarioDiagnostico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioDiagnostico,
     * or with status 400 (Bad Request) if the usuarioDiagnostico is not valid,
     * or with status 500 (Internal Server Error) if the usuarioDiagnostico couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/usuario-diagnosticos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UsuarioDiagnostico> updateUsuarioDiagnostico(@RequestBody UsuarioDiagnostico usuarioDiagnostico) throws URISyntaxException {
        log.debug("REST request to update UsuarioDiagnostico : {}", usuarioDiagnostico);
        if (usuarioDiagnostico.getId() == null) {
            return createUsuarioDiagnostico(usuarioDiagnostico);
        }
        UsuarioDiagnostico result = usuarioDiagnosticoRepository.save(usuarioDiagnostico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("usuarioDiagnostico", usuarioDiagnostico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuario-diagnosticos : get all the usuarioDiagnosticos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of usuarioDiagnosticos in body
     */
    @RequestMapping(value = "/usuario-diagnosticos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UsuarioDiagnostico> getAllUsuarioDiagnosticos() {
        log.debug("REST request to get all UsuarioDiagnosticos");
        List<UsuarioDiagnostico> usuarioDiagnosticos = usuarioDiagnosticoRepository.findAll();
        return usuarioDiagnosticos;
    }

    /**
     * GET  /usuario-diagnosticos/:id : get the "id" usuarioDiagnostico.
     *
     * @param id the id of the usuarioDiagnostico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioDiagnostico, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/usuario-diagnosticos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UsuarioDiagnostico> getUsuarioDiagnostico(@PathVariable Long id) {
        log.debug("REST request to get UsuarioDiagnostico : {}", id);
        UsuarioDiagnostico usuarioDiagnostico = usuarioDiagnosticoRepository.findOne(id);
        return Optional.ofNullable(usuarioDiagnostico)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /usuario-diagnosticos/:id : delete the "id" usuarioDiagnostico.
     *
     * @param id the id of the usuarioDiagnostico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/usuario-diagnosticos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUsuarioDiagnostico(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioDiagnostico : {}", id);
        usuarioDiagnosticoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("usuarioDiagnostico", id.toString())).build();
    }

}

package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.Telefono;
import com.mx.kiibal.celsales.service.TelefonoService;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import com.mx.kiibal.celsales.web.rest.util.PaginationUtil;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.TelefonoMapper;
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
 * REST controller for managing Telefono.
 */
@RestController
@RequestMapping("/api")
public class TelefonoResource {

    private final Logger log = LoggerFactory.getLogger(TelefonoResource.class);
        
    @Inject
    private TelefonoService telefonoService;
    
    @Inject
    private TelefonoMapper telefonoMapper;
    
    /**
     * POST  /telefonos : Create a new telefono.
     *
     * @param telefonoDTO the telefonoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new telefonoDTO, or with status 400 (Bad Request) if the telefono has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/telefonos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDTO> createTelefono(@RequestBody TelefonoDTO telefonoDTO) throws URISyntaxException {
        log.debug("REST request to save Telefono : {}", telefonoDTO);
        if (telefonoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("telefono", "idexists", "A new telefono cannot already have an ID")).body(null);
        }
        TelefonoDTO result = telefonoService.save(telefonoDTO);
        return ResponseEntity.created(new URI("/api/telefonos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("telefono", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /telefonos : Updates an existing telefono.
     *
     * @param telefonoDTO the telefonoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated telefonoDTO,
     * or with status 400 (Bad Request) if the telefonoDTO is not valid,
     * or with status 500 (Internal Server Error) if the telefonoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/telefonos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDTO> updateTelefono(@RequestBody TelefonoDTO telefonoDTO) throws URISyntaxException {
        log.debug("REST request to update Telefono : {}", telefonoDTO);
        if (telefonoDTO.getId() == null) {
            return createTelefono(telefonoDTO);
        }
        TelefonoDTO result = telefonoService.save(telefonoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("telefono", telefonoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /telefonos : get all the telefonos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of telefonos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/telefonos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TelefonoDTO>> getAllTelefonos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Telefonos");
        Page<Telefono> page = telefonoService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/telefonos");
        return new ResponseEntity<>(telefonoMapper.telefonosToTelefonoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /telefonos/:id : get the "id" telefono.
     *
     * @param id the id of the telefonoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the telefonoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/telefonos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TelefonoDTO> getTelefono(@PathVariable Long id) {
        log.debug("REST request to get Telefono : {}", id);
        TelefonoDTO telefonoDTO = telefonoService.findOne(id);
        return Optional.ofNullable(telefonoDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /telefonos/:id : delete the "id" telefono.
     *
     * @param id the id of the telefonoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/telefonos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTelefono(@PathVariable Long id) {
        log.debug("REST request to delete Telefono : {}", id);
        telefonoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("telefono", id.toString())).build();
    }

}

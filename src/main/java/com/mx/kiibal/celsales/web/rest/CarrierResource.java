package com.mx.kiibal.celsales.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mx.kiibal.celsales.domain.Carrier;
import com.mx.kiibal.celsales.service.CarrierService;
import com.mx.kiibal.celsales.web.rest.util.HeaderUtil;
import com.mx.kiibal.celsales.web.rest.util.PaginationUtil;
import com.mx.kiibal.celsales.web.rest.dto.CarrierDTO;
import com.mx.kiibal.celsales.web.rest.mapper.CarrierMapper;
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
 * REST controller for managing Carrier.
 */
@RestController
@RequestMapping("/api")
public class CarrierResource {

    private final Logger log = LoggerFactory.getLogger(CarrierResource.class);
        
    @Inject
    private CarrierService carrierService;
    
    @Inject
    private CarrierMapper carrierMapper;
    
    /**
     * POST  /carriers : Create a new carrier.
     *
     * @param carrierDTO the carrierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carrierDTO, or with status 400 (Bad Request) if the carrier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/carriers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CarrierDTO> createCarrier(@RequestBody CarrierDTO carrierDTO) throws URISyntaxException {
        log.debug("REST request to save Carrier : {}", carrierDTO);
        if (carrierDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("carrier", "idexists", "A new carrier cannot already have an ID")).body(null);
        }
        CarrierDTO result = carrierService.save(carrierDTO);
        return ResponseEntity.created(new URI("/api/carriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("carrier", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /carriers : Updates an existing carrier.
     *
     * @param carrierDTO the carrierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carrierDTO,
     * or with status 400 (Bad Request) if the carrierDTO is not valid,
     * or with status 500 (Internal Server Error) if the carrierDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/carriers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CarrierDTO> updateCarrier(@RequestBody CarrierDTO carrierDTO) throws URISyntaxException {
        log.debug("REST request to update Carrier : {}", carrierDTO);
        if (carrierDTO.getId() == null) {
            return createCarrier(carrierDTO);
        }
        CarrierDTO result = carrierService.save(carrierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("carrier", carrierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /carriers : get all the carriers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of carriers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/carriers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CarrierDTO>> getAllCarriers(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Carriers");
        Page<Carrier> page = carrierService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/carriers");
        return new ResponseEntity<>(carrierMapper.carriersToCarrierDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /carriers/:id : get the "id" carrier.
     *
     * @param id the id of the carrierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carrierDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/carriers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CarrierDTO> getCarrier(@PathVariable Long id) {
        log.debug("REST request to get Carrier : {}", id);
        CarrierDTO carrierDTO = carrierService.findOne(id);
        return Optional.ofNullable(carrierDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /carriers/:id : delete the "id" carrier.
     *
     * @param id the id of the carrierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/carriers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCarrier(@PathVariable Long id) {
        log.debug("REST request to delete Carrier : {}", id);
        carrierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("carrier", id.toString())).build();
    }

}

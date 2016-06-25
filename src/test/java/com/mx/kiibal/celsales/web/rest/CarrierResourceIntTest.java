package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.Carrier;
import com.mx.kiibal.celsales.repository.CarrierRepository;
import com.mx.kiibal.celsales.service.CarrierService;
import com.mx.kiibal.celsales.web.rest.dto.CarrierDTO;
import com.mx.kiibal.celsales.web.rest.mapper.CarrierMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CarrierResource REST controller.
 *
 * @see CarrierResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class CarrierResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    @Inject
    private CarrierRepository carrierRepository;

    @Inject
    private CarrierMapper carrierMapper;

    @Inject
    private CarrierService carrierService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCarrierMockMvc;

    private Carrier carrier;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CarrierResource carrierResource = new CarrierResource();
        ReflectionTestUtils.setField(carrierResource, "carrierService", carrierService);
        ReflectionTestUtils.setField(carrierResource, "carrierMapper", carrierMapper);
        this.restCarrierMockMvc = MockMvcBuilders.standaloneSetup(carrierResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        carrier = new Carrier();
        carrier.setNombre(DEFAULT_NOMBRE);
        carrier.setLogo(DEFAULT_LOGO);
        carrier.setLogoContentType(DEFAULT_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCarrier() throws Exception {
        int databaseSizeBeforeCreate = carrierRepository.findAll().size();

        // Create the Carrier
        CarrierDTO carrierDTO = carrierMapper.carrierToCarrierDTO(carrier);

        restCarrierMockMvc.perform(post("/api/carriers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(carrierDTO)))
                .andExpect(status().isCreated());

        // Validate the Carrier in the database
        List<Carrier> carriers = carrierRepository.findAll();
        assertThat(carriers).hasSize(databaseSizeBeforeCreate + 1);
        Carrier testCarrier = carriers.get(carriers.size() - 1);
        assertThat(testCarrier.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testCarrier.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testCarrier.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllCarriers() throws Exception {
        // Initialize the database
        carrierRepository.saveAndFlush(carrier);

        // Get all the carriers
        restCarrierMockMvc.perform(get("/api/carriers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(carrier.getId().intValue())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
                .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))));
    }

    @Test
    @Transactional
    public void getCarrier() throws Exception {
        // Initialize the database
        carrierRepository.saveAndFlush(carrier);

        // Get the carrier
        restCarrierMockMvc.perform(get("/api/carriers/{id}", carrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(carrier.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)));
    }

    @Test
    @Transactional
    public void getNonExistingCarrier() throws Exception {
        // Get the carrier
        restCarrierMockMvc.perform(get("/api/carriers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarrier() throws Exception {
        // Initialize the database
        carrierRepository.saveAndFlush(carrier);
        int databaseSizeBeforeUpdate = carrierRepository.findAll().size();

        // Update the carrier
        Carrier updatedCarrier = new Carrier();
        updatedCarrier.setId(carrier.getId());
        updatedCarrier.setNombre(UPDATED_NOMBRE);
        updatedCarrier.setLogo(UPDATED_LOGO);
        updatedCarrier.setLogoContentType(UPDATED_LOGO_CONTENT_TYPE);
        CarrierDTO carrierDTO = carrierMapper.carrierToCarrierDTO(updatedCarrier);

        restCarrierMockMvc.perform(put("/api/carriers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(carrierDTO)))
                .andExpect(status().isOk());

        // Validate the Carrier in the database
        List<Carrier> carriers = carrierRepository.findAll();
        assertThat(carriers).hasSize(databaseSizeBeforeUpdate);
        Carrier testCarrier = carriers.get(carriers.size() - 1);
        assertThat(testCarrier.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testCarrier.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testCarrier.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void deleteCarrier() throws Exception {
        // Initialize the database
        carrierRepository.saveAndFlush(carrier);
        int databaseSizeBeforeDelete = carrierRepository.findAll().size();

        // Get the carrier
        restCarrierMockMvc.perform(delete("/api/carriers/{id}", carrier.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Carrier> carriers = carrierRepository.findAll();
        assertThat(carriers).hasSize(databaseSizeBeforeDelete - 1);
    }
}

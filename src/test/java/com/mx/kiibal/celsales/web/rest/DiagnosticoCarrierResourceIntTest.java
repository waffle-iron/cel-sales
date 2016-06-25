package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.DiagnosticoCarrier;
import com.mx.kiibal.celsales.repository.DiagnosticoCarrierRepository;

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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DiagnosticoCarrierResource REST controller.
 *
 * @see DiagnosticoCarrierResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class DiagnosticoCarrierResourceIntTest {


    private static final Boolean DEFAULT_ES_ORIGEN = false;
    private static final Boolean UPDATED_ES_ORIGEN = true;

    @Inject
    private DiagnosticoCarrierRepository diagnosticoCarrierRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDiagnosticoCarrierMockMvc;

    private DiagnosticoCarrier diagnosticoCarrier;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DiagnosticoCarrierResource diagnosticoCarrierResource = new DiagnosticoCarrierResource();
        ReflectionTestUtils.setField(diagnosticoCarrierResource, "diagnosticoCarrierRepository", diagnosticoCarrierRepository);
        this.restDiagnosticoCarrierMockMvc = MockMvcBuilders.standaloneSetup(diagnosticoCarrierResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        diagnosticoCarrier = new DiagnosticoCarrier();
        diagnosticoCarrier.setEsOrigen(DEFAULT_ES_ORIGEN);
    }

    @Test
    @Transactional
    public void createDiagnosticoCarrier() throws Exception {
        int databaseSizeBeforeCreate = diagnosticoCarrierRepository.findAll().size();

        // Create the DiagnosticoCarrier

        restDiagnosticoCarrierMockMvc.perform(post("/api/diagnostico-carriers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(diagnosticoCarrier)))
                .andExpect(status().isCreated());

        // Validate the DiagnosticoCarrier in the database
        List<DiagnosticoCarrier> diagnosticoCarriers = diagnosticoCarrierRepository.findAll();
        assertThat(diagnosticoCarriers).hasSize(databaseSizeBeforeCreate + 1);
        DiagnosticoCarrier testDiagnosticoCarrier = diagnosticoCarriers.get(diagnosticoCarriers.size() - 1);
        assertThat(testDiagnosticoCarrier.isEsOrigen()).isEqualTo(DEFAULT_ES_ORIGEN);
    }

    @Test
    @Transactional
    public void getAllDiagnosticoCarriers() throws Exception {
        // Initialize the database
        diagnosticoCarrierRepository.saveAndFlush(diagnosticoCarrier);

        // Get all the diagnosticoCarriers
        restDiagnosticoCarrierMockMvc.perform(get("/api/diagnostico-carriers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(diagnosticoCarrier.getId().intValue())))
                .andExpect(jsonPath("$.[*].esOrigen").value(hasItem(DEFAULT_ES_ORIGEN.booleanValue())));
    }

    @Test
    @Transactional
    public void getDiagnosticoCarrier() throws Exception {
        // Initialize the database
        diagnosticoCarrierRepository.saveAndFlush(diagnosticoCarrier);

        // Get the diagnosticoCarrier
        restDiagnosticoCarrierMockMvc.perform(get("/api/diagnostico-carriers/{id}", diagnosticoCarrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(diagnosticoCarrier.getId().intValue()))
            .andExpect(jsonPath("$.esOrigen").value(DEFAULT_ES_ORIGEN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDiagnosticoCarrier() throws Exception {
        // Get the diagnosticoCarrier
        restDiagnosticoCarrierMockMvc.perform(get("/api/diagnostico-carriers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiagnosticoCarrier() throws Exception {
        // Initialize the database
        diagnosticoCarrierRepository.saveAndFlush(diagnosticoCarrier);
        int databaseSizeBeforeUpdate = diagnosticoCarrierRepository.findAll().size();

        // Update the diagnosticoCarrier
        DiagnosticoCarrier updatedDiagnosticoCarrier = new DiagnosticoCarrier();
        updatedDiagnosticoCarrier.setId(diagnosticoCarrier.getId());
        updatedDiagnosticoCarrier.setEsOrigen(UPDATED_ES_ORIGEN);

        restDiagnosticoCarrierMockMvc.perform(put("/api/diagnostico-carriers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedDiagnosticoCarrier)))
                .andExpect(status().isOk());

        // Validate the DiagnosticoCarrier in the database
        List<DiagnosticoCarrier> diagnosticoCarriers = diagnosticoCarrierRepository.findAll();
        assertThat(diagnosticoCarriers).hasSize(databaseSizeBeforeUpdate);
        DiagnosticoCarrier testDiagnosticoCarrier = diagnosticoCarriers.get(diagnosticoCarriers.size() - 1);
        assertThat(testDiagnosticoCarrier.isEsOrigen()).isEqualTo(UPDATED_ES_ORIGEN);
    }

    @Test
    @Transactional
    public void deleteDiagnosticoCarrier() throws Exception {
        // Initialize the database
        diagnosticoCarrierRepository.saveAndFlush(diagnosticoCarrier);
        int databaseSizeBeforeDelete = diagnosticoCarrierRepository.findAll().size();

        // Get the diagnosticoCarrier
        restDiagnosticoCarrierMockMvc.perform(delete("/api/diagnostico-carriers/{id}", diagnosticoCarrier.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DiagnosticoCarrier> diagnosticoCarriers = diagnosticoCarrierRepository.findAll();
        assertThat(diagnosticoCarriers).hasSize(databaseSizeBeforeDelete - 1);
    }
}

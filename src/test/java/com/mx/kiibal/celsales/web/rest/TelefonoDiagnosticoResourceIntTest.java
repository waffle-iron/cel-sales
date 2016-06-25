package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.TelefonoDiagnostico;
import com.mx.kiibal.celsales.repository.TelefonoDiagnosticoRepository;

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
 * Test class for the TelefonoDiagnosticoResource REST controller.
 *
 * @see TelefonoDiagnosticoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class TelefonoDiagnosticoResourceIntTest {

    private static final String DEFAULT_FECHA_CREACION = "AAAAA";
    private static final String UPDATED_FECHA_CREACION = "BBBBB";

    @Inject
    private TelefonoDiagnosticoRepository telefonoDiagnosticoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTelefonoDiagnosticoMockMvc;

    private TelefonoDiagnostico telefonoDiagnostico;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TelefonoDiagnosticoResource telefonoDiagnosticoResource = new TelefonoDiagnosticoResource();
        ReflectionTestUtils.setField(telefonoDiagnosticoResource, "telefonoDiagnosticoRepository", telefonoDiagnosticoRepository);
        this.restTelefonoDiagnosticoMockMvc = MockMvcBuilders.standaloneSetup(telefonoDiagnosticoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        telefonoDiagnostico = new TelefonoDiagnostico();
        telefonoDiagnostico.setFechaCreacion(DEFAULT_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void createTelefonoDiagnostico() throws Exception {
        int databaseSizeBeforeCreate = telefonoDiagnosticoRepository.findAll().size();

        // Create the TelefonoDiagnostico

        restTelefonoDiagnosticoMockMvc.perform(post("/api/telefono-diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(telefonoDiagnostico)))
                .andExpect(status().isCreated());

        // Validate the TelefonoDiagnostico in the database
        List<TelefonoDiagnostico> telefonoDiagnosticos = telefonoDiagnosticoRepository.findAll();
        assertThat(telefonoDiagnosticos).hasSize(databaseSizeBeforeCreate + 1);
        TelefonoDiagnostico testTelefonoDiagnostico = telefonoDiagnosticos.get(telefonoDiagnosticos.size() - 1);
        assertThat(testTelefonoDiagnostico.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllTelefonoDiagnosticos() throws Exception {
        // Initialize the database
        telefonoDiagnosticoRepository.saveAndFlush(telefonoDiagnostico);

        // Get all the telefonoDiagnosticos
        restTelefonoDiagnosticoMockMvc.perform(get("/api/telefono-diagnosticos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(telefonoDiagnostico.getId().intValue())))
                .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())));
    }

    @Test
    @Transactional
    public void getTelefonoDiagnostico() throws Exception {
        // Initialize the database
        telefonoDiagnosticoRepository.saveAndFlush(telefonoDiagnostico);

        // Get the telefonoDiagnostico
        restTelefonoDiagnosticoMockMvc.perform(get("/api/telefono-diagnosticos/{id}", telefonoDiagnostico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(telefonoDiagnostico.getId().intValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTelefonoDiagnostico() throws Exception {
        // Get the telefonoDiagnostico
        restTelefonoDiagnosticoMockMvc.perform(get("/api/telefono-diagnosticos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTelefonoDiagnostico() throws Exception {
        // Initialize the database
        telefonoDiagnosticoRepository.saveAndFlush(telefonoDiagnostico);
        int databaseSizeBeforeUpdate = telefonoDiagnosticoRepository.findAll().size();

        // Update the telefonoDiagnostico
        TelefonoDiagnostico updatedTelefonoDiagnostico = new TelefonoDiagnostico();
        updatedTelefonoDiagnostico.setId(telefonoDiagnostico.getId());
        updatedTelefonoDiagnostico.setFechaCreacion(UPDATED_FECHA_CREACION);

        restTelefonoDiagnosticoMockMvc.perform(put("/api/telefono-diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedTelefonoDiagnostico)))
                .andExpect(status().isOk());

        // Validate the TelefonoDiagnostico in the database
        List<TelefonoDiagnostico> telefonoDiagnosticos = telefonoDiagnosticoRepository.findAll();
        assertThat(telefonoDiagnosticos).hasSize(databaseSizeBeforeUpdate);
        TelefonoDiagnostico testTelefonoDiagnostico = telefonoDiagnosticos.get(telefonoDiagnosticos.size() - 1);
        assertThat(testTelefonoDiagnostico.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void deleteTelefonoDiagnostico() throws Exception {
        // Initialize the database
        telefonoDiagnosticoRepository.saveAndFlush(telefonoDiagnostico);
        int databaseSizeBeforeDelete = telefonoDiagnosticoRepository.findAll().size();

        // Get the telefonoDiagnostico
        restTelefonoDiagnosticoMockMvc.perform(delete("/api/telefono-diagnosticos/{id}", telefonoDiagnostico.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TelefonoDiagnostico> telefonoDiagnosticos = telefonoDiagnosticoRepository.findAll();
        assertThat(telefonoDiagnosticos).hasSize(databaseSizeBeforeDelete - 1);
    }
}

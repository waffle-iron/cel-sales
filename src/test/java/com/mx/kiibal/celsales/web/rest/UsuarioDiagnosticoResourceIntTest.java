package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.UsuarioDiagnostico;
import com.mx.kiibal.celsales.repository.UsuarioDiagnosticoRepository;

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
 * Test class for the UsuarioDiagnosticoResource REST controller.
 *
 * @see UsuarioDiagnosticoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class UsuarioDiagnosticoResourceIntTest {


    private static final Boolean DEFAULT_ES_CREADOR = false;
    private static final Boolean UPDATED_ES_CREADOR = true;

    @Inject
    private UsuarioDiagnosticoRepository usuarioDiagnosticoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUsuarioDiagnosticoMockMvc;

    private UsuarioDiagnostico usuarioDiagnostico;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UsuarioDiagnosticoResource usuarioDiagnosticoResource = new UsuarioDiagnosticoResource();
        ReflectionTestUtils.setField(usuarioDiagnosticoResource, "usuarioDiagnosticoRepository", usuarioDiagnosticoRepository);
        this.restUsuarioDiagnosticoMockMvc = MockMvcBuilders.standaloneSetup(usuarioDiagnosticoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        usuarioDiagnostico = new UsuarioDiagnostico();
        usuarioDiagnostico.setEsCreador(DEFAULT_ES_CREADOR);
    }

    @Test
    @Transactional
    public void createUsuarioDiagnostico() throws Exception {
        int databaseSizeBeforeCreate = usuarioDiagnosticoRepository.findAll().size();

        // Create the UsuarioDiagnostico

        restUsuarioDiagnosticoMockMvc.perform(post("/api/usuario-diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDiagnostico)))
                .andExpect(status().isCreated());

        // Validate the UsuarioDiagnostico in the database
        List<UsuarioDiagnostico> usuarioDiagnosticos = usuarioDiagnosticoRepository.findAll();
        assertThat(usuarioDiagnosticos).hasSize(databaseSizeBeforeCreate + 1);
        UsuarioDiagnostico testUsuarioDiagnostico = usuarioDiagnosticos.get(usuarioDiagnosticos.size() - 1);
        assertThat(testUsuarioDiagnostico.isEsCreador()).isEqualTo(DEFAULT_ES_CREADOR);
    }

    @Test
    @Transactional
    public void getAllUsuarioDiagnosticos() throws Exception {
        // Initialize the database
        usuarioDiagnosticoRepository.saveAndFlush(usuarioDiagnostico);

        // Get all the usuarioDiagnosticos
        restUsuarioDiagnosticoMockMvc.perform(get("/api/usuario-diagnosticos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(usuarioDiagnostico.getId().intValue())))
                .andExpect(jsonPath("$.[*].esCreador").value(hasItem(DEFAULT_ES_CREADOR.booleanValue())));
    }

    @Test
    @Transactional
    public void getUsuarioDiagnostico() throws Exception {
        // Initialize the database
        usuarioDiagnosticoRepository.saveAndFlush(usuarioDiagnostico);

        // Get the usuarioDiagnostico
        restUsuarioDiagnosticoMockMvc.perform(get("/api/usuario-diagnosticos/{id}", usuarioDiagnostico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(usuarioDiagnostico.getId().intValue()))
            .andExpect(jsonPath("$.esCreador").value(DEFAULT_ES_CREADOR.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUsuarioDiagnostico() throws Exception {
        // Get the usuarioDiagnostico
        restUsuarioDiagnosticoMockMvc.perform(get("/api/usuario-diagnosticos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarioDiagnostico() throws Exception {
        // Initialize the database
        usuarioDiagnosticoRepository.saveAndFlush(usuarioDiagnostico);
        int databaseSizeBeforeUpdate = usuarioDiagnosticoRepository.findAll().size();

        // Update the usuarioDiagnostico
        UsuarioDiagnostico updatedUsuarioDiagnostico = new UsuarioDiagnostico();
        updatedUsuarioDiagnostico.setId(usuarioDiagnostico.getId());
        updatedUsuarioDiagnostico.setEsCreador(UPDATED_ES_CREADOR);

        restUsuarioDiagnosticoMockMvc.perform(put("/api/usuario-diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedUsuarioDiagnostico)))
                .andExpect(status().isOk());

        // Validate the UsuarioDiagnostico in the database
        List<UsuarioDiagnostico> usuarioDiagnosticos = usuarioDiagnosticoRepository.findAll();
        assertThat(usuarioDiagnosticos).hasSize(databaseSizeBeforeUpdate);
        UsuarioDiagnostico testUsuarioDiagnostico = usuarioDiagnosticos.get(usuarioDiagnosticos.size() - 1);
        assertThat(testUsuarioDiagnostico.isEsCreador()).isEqualTo(UPDATED_ES_CREADOR);
    }

    @Test
    @Transactional
    public void deleteUsuarioDiagnostico() throws Exception {
        // Initialize the database
        usuarioDiagnosticoRepository.saveAndFlush(usuarioDiagnostico);
        int databaseSizeBeforeDelete = usuarioDiagnosticoRepository.findAll().size();

        // Get the usuarioDiagnostico
        restUsuarioDiagnosticoMockMvc.perform(delete("/api/usuario-diagnosticos/{id}", usuarioDiagnostico.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UsuarioDiagnostico> usuarioDiagnosticos = usuarioDiagnosticoRepository.findAll();
        assertThat(usuarioDiagnosticos).hasSize(databaseSizeBeforeDelete - 1);
    }
}

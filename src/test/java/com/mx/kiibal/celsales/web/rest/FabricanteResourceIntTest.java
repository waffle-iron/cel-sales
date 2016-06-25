package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.Fabricante;
import com.mx.kiibal.celsales.repository.FabricanteRepository;
import com.mx.kiibal.celsales.service.FabricanteService;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;
import com.mx.kiibal.celsales.web.rest.mapper.FabricanteMapper;

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
 * Test class for the FabricanteResource REST controller.
 *
 * @see FabricanteResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class FabricanteResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    @Inject
    private FabricanteRepository fabricanteRepository;

    @Inject
    private FabricanteMapper fabricanteMapper;

    @Inject
    private FabricanteService fabricanteService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFabricanteMockMvc;

    private Fabricante fabricante;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FabricanteResource fabricanteResource = new FabricanteResource();
        ReflectionTestUtils.setField(fabricanteResource, "fabricanteService", fabricanteService);
        ReflectionTestUtils.setField(fabricanteResource, "fabricanteMapper", fabricanteMapper);
        this.restFabricanteMockMvc = MockMvcBuilders.standaloneSetup(fabricanteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        fabricante = new Fabricante();
        fabricante.setNombre(DEFAULT_NOMBRE);
        fabricante.setImagen(DEFAULT_IMAGEN);
        fabricante.setImagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFabricante() throws Exception {
        int databaseSizeBeforeCreate = fabricanteRepository.findAll().size();

        // Create the Fabricante
        FabricanteDTO fabricanteDTO = fabricanteMapper.fabricanteToFabricanteDTO(fabricante);

        restFabricanteMockMvc.perform(post("/api/fabricantes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fabricanteDTO)))
                .andExpect(status().isCreated());

        // Validate the Fabricante in the database
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        assertThat(fabricantes).hasSize(databaseSizeBeforeCreate + 1);
        Fabricante testFabricante = fabricantes.get(fabricantes.size() - 1);
        assertThat(testFabricante.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testFabricante.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testFabricante.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllFabricantes() throws Exception {
        // Initialize the database
        fabricanteRepository.saveAndFlush(fabricante);

        // Get all the fabricantes
        restFabricanteMockMvc.perform(get("/api/fabricantes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(fabricante.getId().intValue())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
                .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))));
    }

    @Test
    @Transactional
    public void getFabricante() throws Exception {
        // Initialize the database
        fabricanteRepository.saveAndFlush(fabricante);

        // Get the fabricante
        restFabricanteMockMvc.perform(get("/api/fabricantes/{id}", fabricante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(fabricante.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)));
    }

    @Test
    @Transactional
    public void getNonExistingFabricante() throws Exception {
        // Get the fabricante
        restFabricanteMockMvc.perform(get("/api/fabricantes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFabricante() throws Exception {
        // Initialize the database
        fabricanteRepository.saveAndFlush(fabricante);
        int databaseSizeBeforeUpdate = fabricanteRepository.findAll().size();

        // Update the fabricante
        Fabricante updatedFabricante = new Fabricante();
        updatedFabricante.setId(fabricante.getId());
        updatedFabricante.setNombre(UPDATED_NOMBRE);
        updatedFabricante.setImagen(UPDATED_IMAGEN);
        updatedFabricante.setImagenContentType(UPDATED_IMAGEN_CONTENT_TYPE);
        FabricanteDTO fabricanteDTO = fabricanteMapper.fabricanteToFabricanteDTO(updatedFabricante);

        restFabricanteMockMvc.perform(put("/api/fabricantes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fabricanteDTO)))
                .andExpect(status().isOk());

        // Validate the Fabricante in the database
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        assertThat(fabricantes).hasSize(databaseSizeBeforeUpdate);
        Fabricante testFabricante = fabricantes.get(fabricantes.size() - 1);
        assertThat(testFabricante.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testFabricante.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testFabricante.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void deleteFabricante() throws Exception {
        // Initialize the database
        fabricanteRepository.saveAndFlush(fabricante);
        int databaseSizeBeforeDelete = fabricanteRepository.findAll().size();

        // Get the fabricante
        restFabricanteMockMvc.perform(delete("/api/fabricantes/{id}", fabricante.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        assertThat(fabricantes).hasSize(databaseSizeBeforeDelete - 1);
    }
}

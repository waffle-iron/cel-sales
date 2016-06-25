package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.Telefono;
import com.mx.kiibal.celsales.repository.TelefonoRepository;
import com.mx.kiibal.celsales.service.TelefonoService;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.TelefonoMapper;

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
 * Test class for the TelefonoResource REST controller.
 *
 * @see TelefonoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class TelefonoResourceIntTest {

    private static final String DEFAULT_MARCA = "AAAAA";
    private static final String UPDATED_MARCA = "BBBBB";
    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";
    private static final String DEFAULT_MODELO = "AAAAA";
    private static final String UPDATED_MODELO = "BBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    @Inject
    private TelefonoRepository telefonoRepository;

    @Inject
    private TelefonoMapper telefonoMapper;

    @Inject
    private TelefonoService telefonoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTelefonoMockMvc;

    private Telefono telefono;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TelefonoResource telefonoResource = new TelefonoResource();
        ReflectionTestUtils.setField(telefonoResource, "telefonoService", telefonoService);
        ReflectionTestUtils.setField(telefonoResource, "telefonoMapper", telefonoMapper);
        this.restTelefonoMockMvc = MockMvcBuilders.standaloneSetup(telefonoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        telefono = new Telefono();
        telefono.setMarca(DEFAULT_MARCA);
        telefono.setNombre(DEFAULT_NOMBRE);
        telefono.setModelo(DEFAULT_MODELO);
        telefono.setImagen(DEFAULT_IMAGEN);
        telefono.setImagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createTelefono() throws Exception {
        int databaseSizeBeforeCreate = telefonoRepository.findAll().size();

        // Create the Telefono
        TelefonoDTO telefonoDTO = telefonoMapper.telefonoToTelefonoDTO(telefono);

        restTelefonoMockMvc.perform(post("/api/telefonos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(telefonoDTO)))
                .andExpect(status().isCreated());

        // Validate the Telefono in the database
        List<Telefono> telefonos = telefonoRepository.findAll();
        assertThat(telefonos).hasSize(databaseSizeBeforeCreate + 1);
        Telefono testTelefono = telefonos.get(telefonos.size() - 1);
        assertThat(testTelefono.getMarca()).isEqualTo(DEFAULT_MARCA);
        assertThat(testTelefono.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTelefono.getModelo()).isEqualTo(DEFAULT_MODELO);
        assertThat(testTelefono.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testTelefono.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllTelefonos() throws Exception {
        // Initialize the database
        telefonoRepository.saveAndFlush(telefono);

        // Get all the telefonos
        restTelefonoMockMvc.perform(get("/api/telefonos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(telefono.getId().intValue())))
                .andExpect(jsonPath("$.[*].marca").value(hasItem(DEFAULT_MARCA.toString())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
                .andExpect(jsonPath("$.[*].modelo").value(hasItem(DEFAULT_MODELO.toString())))
                .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))));
    }

    @Test
    @Transactional
    public void getTelefono() throws Exception {
        // Initialize the database
        telefonoRepository.saveAndFlush(telefono);

        // Get the telefono
        restTelefonoMockMvc.perform(get("/api/telefonos/{id}", telefono.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(telefono.getId().intValue()))
            .andExpect(jsonPath("$.marca").value(DEFAULT_MARCA.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.modelo").value(DEFAULT_MODELO.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)));
    }

    @Test
    @Transactional
    public void getNonExistingTelefono() throws Exception {
        // Get the telefono
        restTelefonoMockMvc.perform(get("/api/telefonos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTelefono() throws Exception {
        // Initialize the database
        telefonoRepository.saveAndFlush(telefono);
        int databaseSizeBeforeUpdate = telefonoRepository.findAll().size();

        // Update the telefono
        Telefono updatedTelefono = new Telefono();
        updatedTelefono.setId(telefono.getId());
        updatedTelefono.setMarca(UPDATED_MARCA);
        updatedTelefono.setNombre(UPDATED_NOMBRE);
        updatedTelefono.setModelo(UPDATED_MODELO);
        updatedTelefono.setImagen(UPDATED_IMAGEN);
        updatedTelefono.setImagenContentType(UPDATED_IMAGEN_CONTENT_TYPE);
        TelefonoDTO telefonoDTO = telefonoMapper.telefonoToTelefonoDTO(updatedTelefono);

        restTelefonoMockMvc.perform(put("/api/telefonos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(telefonoDTO)))
                .andExpect(status().isOk());

        // Validate the Telefono in the database
        List<Telefono> telefonos = telefonoRepository.findAll();
        assertThat(telefonos).hasSize(databaseSizeBeforeUpdate);
        Telefono testTelefono = telefonos.get(telefonos.size() - 1);
        assertThat(testTelefono.getMarca()).isEqualTo(UPDATED_MARCA);
        assertThat(testTelefono.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTelefono.getModelo()).isEqualTo(UPDATED_MODELO);
        assertThat(testTelefono.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testTelefono.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void deleteTelefono() throws Exception {
        // Initialize the database
        telefonoRepository.saveAndFlush(telefono);
        int databaseSizeBeforeDelete = telefonoRepository.findAll().size();

        // Get the telefono
        restTelefonoMockMvc.perform(delete("/api/telefonos/{id}", telefono.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Telefono> telefonos = telefonoRepository.findAll();
        assertThat(telefonos).hasSize(databaseSizeBeforeDelete - 1);
    }
}

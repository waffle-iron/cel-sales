package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.AppNoOfc;
import com.mx.kiibal.celsales.repository.AppNoOfcRepository;

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
 * Test class for the AppNoOfcResource REST controller.
 *
 * @see AppNoOfcResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class AppNoOfcResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";

    private static final byte[] DEFAULT_ICONO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ICONO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ICONO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ICONO_CONTENT_TYPE = "image/png";
    private static final String DEFAULT_VERSION = "AAAAA";
    private static final String UPDATED_VERSION = "BBBBB";
    private static final String DEFAULT_EMPAQUETADO = "AAAAA";
    private static final String UPDATED_EMPAQUETADO = "BBBBB";
    private static final String DEFAULT_FECHA_INSTALACION = "AAAAA";
    private static final String UPDATED_FECHA_INSTALACION = "BBBBB";
    private static final String DEFAULT_FECHA_MODIFICACION = "AAAAA";
    private static final String UPDATED_FECHA_MODIFICACION = "BBBBB";

    @Inject
    private AppNoOfcRepository appNoOfcRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAppNoOfcMockMvc;

    private AppNoOfc appNoOfc;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AppNoOfcResource appNoOfcResource = new AppNoOfcResource();
        ReflectionTestUtils.setField(appNoOfcResource, "appNoOfcRepository", appNoOfcRepository);
        this.restAppNoOfcMockMvc = MockMvcBuilders.standaloneSetup(appNoOfcResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        appNoOfc = new AppNoOfc();
        appNoOfc.setNombre(DEFAULT_NOMBRE);
        appNoOfc.setIcono(DEFAULT_ICONO);
        appNoOfc.setIconoContentType(DEFAULT_ICONO_CONTENT_TYPE);
        appNoOfc.setVersion(DEFAULT_VERSION);
        appNoOfc.setEmpaquetado(DEFAULT_EMPAQUETADO);
        appNoOfc.setFechaInstalacion(DEFAULT_FECHA_INSTALACION);
        appNoOfc.setFechaModificacion(DEFAULT_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void createAppNoOfc() throws Exception {
        int databaseSizeBeforeCreate = appNoOfcRepository.findAll().size();

        // Create the AppNoOfc

        restAppNoOfcMockMvc.perform(post("/api/app-no-ofcs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(appNoOfc)))
                .andExpect(status().isCreated());

        // Validate the AppNoOfc in the database
        List<AppNoOfc> appNoOfcs = appNoOfcRepository.findAll();
        assertThat(appNoOfcs).hasSize(databaseSizeBeforeCreate + 1);
        AppNoOfc testAppNoOfc = appNoOfcs.get(appNoOfcs.size() - 1);
        assertThat(testAppNoOfc.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAppNoOfc.getIcono()).isEqualTo(DEFAULT_ICONO);
        assertThat(testAppNoOfc.getIconoContentType()).isEqualTo(DEFAULT_ICONO_CONTENT_TYPE);
        assertThat(testAppNoOfc.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAppNoOfc.getEmpaquetado()).isEqualTo(DEFAULT_EMPAQUETADO);
        assertThat(testAppNoOfc.getFechaInstalacion()).isEqualTo(DEFAULT_FECHA_INSTALACION);
        assertThat(testAppNoOfc.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllAppNoOfcs() throws Exception {
        // Initialize the database
        appNoOfcRepository.saveAndFlush(appNoOfc);

        // Get all the appNoOfcs
        restAppNoOfcMockMvc.perform(get("/api/app-no-ofcs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(appNoOfc.getId().intValue())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
                .andExpect(jsonPath("$.[*].iconoContentType").value(hasItem(DEFAULT_ICONO_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].icono").value(hasItem(Base64Utils.encodeToString(DEFAULT_ICONO))))
                .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
                .andExpect(jsonPath("$.[*].empaquetado").value(hasItem(DEFAULT_EMPAQUETADO.toString())))
                .andExpect(jsonPath("$.[*].fechaInstalacion").value(hasItem(DEFAULT_FECHA_INSTALACION.toString())))
                .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())));
    }

    @Test
    @Transactional
    public void getAppNoOfc() throws Exception {
        // Initialize the database
        appNoOfcRepository.saveAndFlush(appNoOfc);

        // Get the appNoOfc
        restAppNoOfcMockMvc.perform(get("/api/app-no-ofcs/{id}", appNoOfc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(appNoOfc.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.iconoContentType").value(DEFAULT_ICONO_CONTENT_TYPE))
            .andExpect(jsonPath("$.icono").value(Base64Utils.encodeToString(DEFAULT_ICONO)))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.empaquetado").value(DEFAULT_EMPAQUETADO.toString()))
            .andExpect(jsonPath("$.fechaInstalacion").value(DEFAULT_FECHA_INSTALACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppNoOfc() throws Exception {
        // Get the appNoOfc
        restAppNoOfcMockMvc.perform(get("/api/app-no-ofcs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppNoOfc() throws Exception {
        // Initialize the database
        appNoOfcRepository.saveAndFlush(appNoOfc);
        int databaseSizeBeforeUpdate = appNoOfcRepository.findAll().size();

        // Update the appNoOfc
        AppNoOfc updatedAppNoOfc = new AppNoOfc();
        updatedAppNoOfc.setId(appNoOfc.getId());
        updatedAppNoOfc.setNombre(UPDATED_NOMBRE);
        updatedAppNoOfc.setIcono(UPDATED_ICONO);
        updatedAppNoOfc.setIconoContentType(UPDATED_ICONO_CONTENT_TYPE);
        updatedAppNoOfc.setVersion(UPDATED_VERSION);
        updatedAppNoOfc.setEmpaquetado(UPDATED_EMPAQUETADO);
        updatedAppNoOfc.setFechaInstalacion(UPDATED_FECHA_INSTALACION);
        updatedAppNoOfc.setFechaModificacion(UPDATED_FECHA_MODIFICACION);

        restAppNoOfcMockMvc.perform(put("/api/app-no-ofcs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedAppNoOfc)))
                .andExpect(status().isOk());

        // Validate the AppNoOfc in the database
        List<AppNoOfc> appNoOfcs = appNoOfcRepository.findAll();
        assertThat(appNoOfcs).hasSize(databaseSizeBeforeUpdate);
        AppNoOfc testAppNoOfc = appNoOfcs.get(appNoOfcs.size() - 1);
        assertThat(testAppNoOfc.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAppNoOfc.getIcono()).isEqualTo(UPDATED_ICONO);
        assertThat(testAppNoOfc.getIconoContentType()).isEqualTo(UPDATED_ICONO_CONTENT_TYPE);
        assertThat(testAppNoOfc.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAppNoOfc.getEmpaquetado()).isEqualTo(UPDATED_EMPAQUETADO);
        assertThat(testAppNoOfc.getFechaInstalacion()).isEqualTo(UPDATED_FECHA_INSTALACION);
        assertThat(testAppNoOfc.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void deleteAppNoOfc() throws Exception {
        // Initialize the database
        appNoOfcRepository.saveAndFlush(appNoOfc);
        int databaseSizeBeforeDelete = appNoOfcRepository.findAll().size();

        // Get the appNoOfc
        restAppNoOfcMockMvc.perform(delete("/api/app-no-ofcs/{id}", appNoOfc.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<AppNoOfc> appNoOfcs = appNoOfcRepository.findAll();
        assertThat(appNoOfcs).hasSize(databaseSizeBeforeDelete - 1);
    }
}

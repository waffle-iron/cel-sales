package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.MensajeLog;
import com.mx.kiibal.celsales.repository.MensajeLogRepository;

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
 * Test class for the MensajeLogResource REST controller.
 *
 * @see MensajeLogResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class MensajeLogResourceIntTest {

    private static final String DEFAULT_FECHA = "AAAAA";
    private static final String UPDATED_FECHA = "BBBBB";
    private static final String DEFAULT_LOG_EXCEPTION = "AAAAA";
    private static final String UPDATED_LOG_EXCEPTION = "BBBBB";
    private static final String DEFAULT_LOG_EXCEPTION_MSG = "AAAAA";
    private static final String UPDATED_LOG_EXCEPTION_MSG = "BBBBB";
    private static final String DEFAULT_LOG_PACKAGE = "AAAAA";
    private static final String UPDATED_LOG_PACKAGE = "BBBBB";

    @Inject
    private MensajeLogRepository mensajeLogRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMensajeLogMockMvc;

    private MensajeLog mensajeLog;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MensajeLogResource mensajeLogResource = new MensajeLogResource();
        ReflectionTestUtils.setField(mensajeLogResource, "mensajeLogRepository", mensajeLogRepository);
        this.restMensajeLogMockMvc = MockMvcBuilders.standaloneSetup(mensajeLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        mensajeLog = new MensajeLog();
        mensajeLog.setFecha(DEFAULT_FECHA);
        mensajeLog.setLogException(DEFAULT_LOG_EXCEPTION);
        mensajeLog.setLogExceptionMsg(DEFAULT_LOG_EXCEPTION_MSG);
        mensajeLog.setLogPackage(DEFAULT_LOG_PACKAGE);
    }

    @Test
    @Transactional
    public void createMensajeLog() throws Exception {
        int databaseSizeBeforeCreate = mensajeLogRepository.findAll().size();

        // Create the MensajeLog

        restMensajeLogMockMvc.perform(post("/api/mensaje-logs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mensajeLog)))
                .andExpect(status().isCreated());

        // Validate the MensajeLog in the database
        List<MensajeLog> mensajeLogs = mensajeLogRepository.findAll();
        assertThat(mensajeLogs).hasSize(databaseSizeBeforeCreate + 1);
        MensajeLog testMensajeLog = mensajeLogs.get(mensajeLogs.size() - 1);
        assertThat(testMensajeLog.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testMensajeLog.getLogException()).isEqualTo(DEFAULT_LOG_EXCEPTION);
        assertThat(testMensajeLog.getLogExceptionMsg()).isEqualTo(DEFAULT_LOG_EXCEPTION_MSG);
        assertThat(testMensajeLog.getLogPackage()).isEqualTo(DEFAULT_LOG_PACKAGE);
    }

    @Test
    @Transactional
    public void getAllMensajeLogs() throws Exception {
        // Initialize the database
        mensajeLogRepository.saveAndFlush(mensajeLog);

        // Get all the mensajeLogs
        restMensajeLogMockMvc.perform(get("/api/mensaje-logs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(mensajeLog.getId().intValue())))
                .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
                .andExpect(jsonPath("$.[*].logException").value(hasItem(DEFAULT_LOG_EXCEPTION.toString())))
                .andExpect(jsonPath("$.[*].logExceptionMsg").value(hasItem(DEFAULT_LOG_EXCEPTION_MSG.toString())))
                .andExpect(jsonPath("$.[*].logPackage").value(hasItem(DEFAULT_LOG_PACKAGE.toString())));
    }

    @Test
    @Transactional
    public void getMensajeLog() throws Exception {
        // Initialize the database
        mensajeLogRepository.saveAndFlush(mensajeLog);

        // Get the mensajeLog
        restMensajeLogMockMvc.perform(get("/api/mensaje-logs/{id}", mensajeLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(mensajeLog.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.logException").value(DEFAULT_LOG_EXCEPTION.toString()))
            .andExpect(jsonPath("$.logExceptionMsg").value(DEFAULT_LOG_EXCEPTION_MSG.toString()))
            .andExpect(jsonPath("$.logPackage").value(DEFAULT_LOG_PACKAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMensajeLog() throws Exception {
        // Get the mensajeLog
        restMensajeLogMockMvc.perform(get("/api/mensaje-logs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMensajeLog() throws Exception {
        // Initialize the database
        mensajeLogRepository.saveAndFlush(mensajeLog);
        int databaseSizeBeforeUpdate = mensajeLogRepository.findAll().size();

        // Update the mensajeLog
        MensajeLog updatedMensajeLog = new MensajeLog();
        updatedMensajeLog.setId(mensajeLog.getId());
        updatedMensajeLog.setFecha(UPDATED_FECHA);
        updatedMensajeLog.setLogException(UPDATED_LOG_EXCEPTION);
        updatedMensajeLog.setLogExceptionMsg(UPDATED_LOG_EXCEPTION_MSG);
        updatedMensajeLog.setLogPackage(UPDATED_LOG_PACKAGE);

        restMensajeLogMockMvc.perform(put("/api/mensaje-logs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedMensajeLog)))
                .andExpect(status().isOk());

        // Validate the MensajeLog in the database
        List<MensajeLog> mensajeLogs = mensajeLogRepository.findAll();
        assertThat(mensajeLogs).hasSize(databaseSizeBeforeUpdate);
        MensajeLog testMensajeLog = mensajeLogs.get(mensajeLogs.size() - 1);
        assertThat(testMensajeLog.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testMensajeLog.getLogException()).isEqualTo(UPDATED_LOG_EXCEPTION);
        assertThat(testMensajeLog.getLogExceptionMsg()).isEqualTo(UPDATED_LOG_EXCEPTION_MSG);
        assertThat(testMensajeLog.getLogPackage()).isEqualTo(UPDATED_LOG_PACKAGE);
    }

    @Test
    @Transactional
    public void deleteMensajeLog() throws Exception {
        // Initialize the database
        mensajeLogRepository.saveAndFlush(mensajeLog);
        int databaseSizeBeforeDelete = mensajeLogRepository.findAll().size();

        // Get the mensajeLog
        restMensajeLogMockMvc.perform(delete("/api/mensaje-logs/{id}", mensajeLog.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MensajeLog> mensajeLogs = mensajeLogRepository.findAll();
        assertThat(mensajeLogs).hasSize(databaseSizeBeforeDelete - 1);
    }
}

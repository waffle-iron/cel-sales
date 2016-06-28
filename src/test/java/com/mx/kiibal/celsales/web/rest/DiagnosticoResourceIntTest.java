package com.mx.kiibal.celsales.web.rest;

import com.mx.kiibal.celsales.CelSalesApp;
import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.repository.DiagnosticoRepository;
import com.mx.kiibal.celsales.service.DiagnosticoService;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.DiagnosticoMapper;

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

import com.mx.kiibal.celsales.domain.enumeration.EstadoBateria;

/**
 * Test class for the DiagnosticoResource REST controller.
 *
 * @see DiagnosticoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CelSalesApp.class)
@WebAppConfiguration
@IntegrationTest
public class DiagnosticoResourceIntTest {

    private static final String DEFAULT_IMEI = "AAAAA";
    private static final String UPDATED_IMEI = "BBBBB";
    private static final String DEFAULT_SERIAL = "AAAAA";
    private static final String UPDATED_SERIAL = "BBBBB";
    private static final String DEFAULT_TEMP_BATERIA = "AAAAA";
    private static final String UPDATED_TEMP_BATERIA = "BBBBB";
    private static final String DEFAULT_TEC_BATERIA = "AAAAA";
    private static final String UPDATED_TEC_BATERIA = "BBBBB";
    private static final String DEFAULT_VOLT_BATERIA = "AAAAA";
    private static final String UPDATED_VOLT_BATERIA = "BBBBB";
    private static final String DEFAULT_CAP_BATERIA = "AAAAA";
    private static final String UPDATED_CAP_BATERIA = "BBBBB";
    private static final String DEFAULT_FUENTE_ENERGIA = "AAAAA";
    private static final String UPDATED_FUENTE_ENERGIA = "BBBBB";
    private static final String DEFAULT_VERSION_SO = "AAAAA";
    private static final String UPDATED_VERSION_SO = "BBBBB";

    private static final EstadoBateria DEFAULT_ESTADO_BATERIA = EstadoBateria.Cold;
    private static final EstadoBateria UPDATED_ESTADO_BATERIA = EstadoBateria.Good;
    private static final String DEFAULT_ALM_INTERNO_TOTAL = "AAAAA";
    private static final String UPDATED_ALM_INTERNO_TOTAL = "BBBBB";
    private static final String DEFAULT_ALM_INTERNO_DISP = "AAAAA";
    private static final String UPDATED_ALM_INTERNO_DISP = "BBBBB";
    private static final String DEFAULT_ALM_EXTERNO_TOTAL = "AAAAA";
    private static final String UPDATED_ALM_EXTERNO_TOTAL = "BBBBB";
    private static final String DEFAULT_ALM_EXTERNO_DISP = "AAAAA";
    private static final String UPDATED_ALM_EXTERNO_DISP = "BBBBB";
    private static final String DEFAULT_ESTADO_CARGA = "AAAAA";
    private static final String UPDATED_ESTADO_CARGA = "BBBBB";
    private static final String DEFAULT_PORCENTAJE_CARGA = "AAAAA";
    private static final String UPDATED_PORCENTAJE_CARGA = "BBBBB";

    private static final Boolean DEFAULT_BLUETOOTH_ENABLED = false;
    private static final Boolean UPDATED_BLUETOOTH_ENABLED = true;
    private static final String DEFAULT_BLUETOOTH_MAC_ADDR = "AAAAA";
    private static final String UPDATED_BLUETOOTH_MAC_ADDR = "BBBBB";
    private static final String DEFAULT_BLUETOOTH_NAME = "AAAAA";
    private static final String UPDATED_BLUETOOTH_NAME = "BBBBB";

    private static final Boolean DEFAULT_WIFI_ENABLED = false;
    private static final Boolean UPDATED_WIFI_ENABLED = true;
    private static final String DEFAULT_WIFI_MAC_ADDR = "AAAAA";
    private static final String UPDATED_WIFI_MAC_ADDR = "BBBBB";

    @Inject
    private DiagnosticoRepository diagnosticoRepository;

    @Inject
    private DiagnosticoMapper diagnosticoMapper;

    @Inject
    private DiagnosticoService diagnosticoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDiagnosticoMockMvc;

    private Diagnostico diagnostico;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DiagnosticoResource diagnosticoResource = new DiagnosticoResource();
        ReflectionTestUtils.setField(diagnosticoResource, "diagnosticoService", diagnosticoService);
        ReflectionTestUtils.setField(diagnosticoResource, "diagnosticoMapper", diagnosticoMapper);
        this.restDiagnosticoMockMvc = MockMvcBuilders.standaloneSetup(diagnosticoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        diagnostico = new Diagnostico();
        diagnostico.setImei(DEFAULT_IMEI);
        diagnostico.setSerial(DEFAULT_SERIAL);
        diagnostico.setTempBateria(DEFAULT_TEMP_BATERIA);
        diagnostico.setTecBateria(DEFAULT_TEC_BATERIA);
        diagnostico.setVoltBateria(DEFAULT_VOLT_BATERIA);
        diagnostico.setCapBateria(DEFAULT_CAP_BATERIA);
        diagnostico.setFuenteEnergia(DEFAULT_FUENTE_ENERGIA);
        diagnostico.setVersionSO(DEFAULT_VERSION_SO);
        diagnostico.setEstadoBateria(DEFAULT_ESTADO_BATERIA);
        diagnostico.setAlmInternoTotal(DEFAULT_ALM_INTERNO_TOTAL);
        diagnostico.setAlmInternoDisp(DEFAULT_ALM_INTERNO_DISP);
        diagnostico.setAlmExternoTotal(DEFAULT_ALM_EXTERNO_TOTAL);
        diagnostico.setAlmExternoDisp(DEFAULT_ALM_EXTERNO_DISP);
        diagnostico.setEstadoCarga(DEFAULT_ESTADO_CARGA);
        diagnostico.setPorcentajeCarga(DEFAULT_PORCENTAJE_CARGA);
        diagnostico.setBluetoothEnabled(DEFAULT_BLUETOOTH_ENABLED);
        diagnostico.setBluetoothMacAddr(DEFAULT_BLUETOOTH_MAC_ADDR);
        diagnostico.setBluetoothName(DEFAULT_BLUETOOTH_NAME);
        diagnostico.setWifiEnabled(DEFAULT_WIFI_ENABLED);
        diagnostico.setWifiMacAddr(DEFAULT_WIFI_MAC_ADDR);
    }

    @Test
    @Transactional
    public void createDiagnostico() throws Exception {
        int databaseSizeBeforeCreate = diagnosticoRepository.findAll().size();

        // Create the Diagnostico
        DiagnosticoDTO diagnosticoDTO = diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);

        restDiagnosticoMockMvc.perform(post("/api/diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(diagnosticoDTO)))
                .andExpect(status().isCreated());

        // Validate the Diagnostico in the database
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        assertThat(diagnosticos).hasSize(databaseSizeBeforeCreate + 1);
        Diagnostico testDiagnostico = diagnosticos.get(diagnosticos.size() - 1);
        assertThat(testDiagnostico.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testDiagnostico.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testDiagnostico.getTempBateria()).isEqualTo(DEFAULT_TEMP_BATERIA);
        assertThat(testDiagnostico.getTecBateria()).isEqualTo(DEFAULT_TEC_BATERIA);
        assertThat(testDiagnostico.getVoltBateria()).isEqualTo(DEFAULT_VOLT_BATERIA);
        assertThat(testDiagnostico.getCapBateria()).isEqualTo(DEFAULT_CAP_BATERIA);
        assertThat(testDiagnostico.getFuenteEnergia()).isEqualTo(DEFAULT_FUENTE_ENERGIA);
        assertThat(testDiagnostico.getVersionSO()).isEqualTo(DEFAULT_VERSION_SO);
        assertThat(testDiagnostico.getEstadoBateria()).isEqualTo(DEFAULT_ESTADO_BATERIA);
        assertThat(testDiagnostico.getAlmInternoTotal()).isEqualTo(DEFAULT_ALM_INTERNO_TOTAL);
        assertThat(testDiagnostico.getAlmInternoDisp()).isEqualTo(DEFAULT_ALM_INTERNO_DISP);
        assertThat(testDiagnostico.getAlmExternoTotal()).isEqualTo(DEFAULT_ALM_EXTERNO_TOTAL);
        assertThat(testDiagnostico.getAlmExternoDisp()).isEqualTo(DEFAULT_ALM_EXTERNO_DISP);
        assertThat(testDiagnostico.getEstadoCarga()).isEqualTo(DEFAULT_ESTADO_CARGA);
        assertThat(testDiagnostico.getPorcentajeCarga()).isEqualTo(DEFAULT_PORCENTAJE_CARGA);
        assertThat(testDiagnostico.isBluetoothEnabled()).isEqualTo(DEFAULT_BLUETOOTH_ENABLED);
        assertThat(testDiagnostico.getBluetoothMacAddr()).isEqualTo(DEFAULT_BLUETOOTH_MAC_ADDR);
        assertThat(testDiagnostico.getBluetoothName()).isEqualTo(DEFAULT_BLUETOOTH_NAME);
        assertThat(testDiagnostico.isWifiEnabled()).isEqualTo(DEFAULT_WIFI_ENABLED);
        assertThat(testDiagnostico.getWifiMacAddr()).isEqualTo(DEFAULT_WIFI_MAC_ADDR);
    }

    @Test
    @Transactional
    public void getAllDiagnosticos() throws Exception {
        // Initialize the database
        diagnosticoRepository.saveAndFlush(diagnostico);

        // Get all the diagnosticos
        restDiagnosticoMockMvc.perform(get("/api/diagnosticos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(diagnostico.getId().intValue())))
                .andExpect(jsonPath("$.[*].imei").value(hasItem(DEFAULT_IMEI.toString())))
                .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL.toString())))
                .andExpect(jsonPath("$.[*].tempBateria").value(hasItem(DEFAULT_TEMP_BATERIA.toString())))
                .andExpect(jsonPath("$.[*].tecBateria").value(hasItem(DEFAULT_TEC_BATERIA.toString())))
                .andExpect(jsonPath("$.[*].voltBateria").value(hasItem(DEFAULT_VOLT_BATERIA.toString())))
                .andExpect(jsonPath("$.[*].capBateria").value(hasItem(DEFAULT_CAP_BATERIA.toString())))
                .andExpect(jsonPath("$.[*].fuenteEnergia").value(hasItem(DEFAULT_FUENTE_ENERGIA.toString())))
                .andExpect(jsonPath("$.[*].versionSO").value(hasItem(DEFAULT_VERSION_SO.toString())))
                .andExpect(jsonPath("$.[*].estadoBateria").value(hasItem(DEFAULT_ESTADO_BATERIA.toString())))
                .andExpect(jsonPath("$.[*].almInternoTotal").value(hasItem(DEFAULT_ALM_INTERNO_TOTAL.toString())))
                .andExpect(jsonPath("$.[*].almInternoDisp").value(hasItem(DEFAULT_ALM_INTERNO_DISP.toString())))
                .andExpect(jsonPath("$.[*].almExternoTotal").value(hasItem(DEFAULT_ALM_EXTERNO_TOTAL.toString())))
                .andExpect(jsonPath("$.[*].almExternoDisp").value(hasItem(DEFAULT_ALM_EXTERNO_DISP.toString())))
                .andExpect(jsonPath("$.[*].estadoCarga").value(hasItem(DEFAULT_ESTADO_CARGA.toString())))
                .andExpect(jsonPath("$.[*].porcentajeCarga").value(hasItem(DEFAULT_PORCENTAJE_CARGA.toString())))
                .andExpect(jsonPath("$.[*].bluetoothEnabled").value(hasItem(DEFAULT_BLUETOOTH_ENABLED.booleanValue())))
                .andExpect(jsonPath("$.[*].bluetoothMacAddr").value(hasItem(DEFAULT_BLUETOOTH_MAC_ADDR.toString())))
                .andExpect(jsonPath("$.[*].bluetoothName").value(hasItem(DEFAULT_BLUETOOTH_NAME.toString())))
                .andExpect(jsonPath("$.[*].wifiEnabled").value(hasItem(DEFAULT_WIFI_ENABLED.booleanValue())))
                .andExpect(jsonPath("$.[*].wifiMacAddr").value(hasItem(DEFAULT_WIFI_MAC_ADDR.toString())));
    }

    @Test
    @Transactional
    public void getDiagnostico() throws Exception {
        // Initialize the database
        diagnosticoRepository.saveAndFlush(diagnostico);

        // Get the diagnostico
        restDiagnosticoMockMvc.perform(get("/api/diagnosticos/{id}", diagnostico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(diagnostico.getId().intValue()))
            .andExpect(jsonPath("$.imei").value(DEFAULT_IMEI.toString()))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL.toString()))
            .andExpect(jsonPath("$.tempBateria").value(DEFAULT_TEMP_BATERIA.toString()))
            .andExpect(jsonPath("$.tecBateria").value(DEFAULT_TEC_BATERIA.toString()))
            .andExpect(jsonPath("$.voltBateria").value(DEFAULT_VOLT_BATERIA.toString()))
            .andExpect(jsonPath("$.capBateria").value(DEFAULT_CAP_BATERIA.toString()))
            .andExpect(jsonPath("$.fuenteEnergia").value(DEFAULT_FUENTE_ENERGIA.toString()))
            .andExpect(jsonPath("$.versionSO").value(DEFAULT_VERSION_SO.toString()))
            .andExpect(jsonPath("$.estadoBateria").value(DEFAULT_ESTADO_BATERIA.toString()))
            .andExpect(jsonPath("$.almInternoTotal").value(DEFAULT_ALM_INTERNO_TOTAL.toString()))
            .andExpect(jsonPath("$.almInternoDisp").value(DEFAULT_ALM_INTERNO_DISP.toString()))
            .andExpect(jsonPath("$.almExternoTotal").value(DEFAULT_ALM_EXTERNO_TOTAL.toString()))
            .andExpect(jsonPath("$.almExternoDisp").value(DEFAULT_ALM_EXTERNO_DISP.toString()))
            .andExpect(jsonPath("$.estadoCarga").value(DEFAULT_ESTADO_CARGA.toString()))
            .andExpect(jsonPath("$.porcentajeCarga").value(DEFAULT_PORCENTAJE_CARGA.toString()))
            .andExpect(jsonPath("$.bluetoothEnabled").value(DEFAULT_BLUETOOTH_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.bluetoothMacAddr").value(DEFAULT_BLUETOOTH_MAC_ADDR.toString()))
            .andExpect(jsonPath("$.bluetoothName").value(DEFAULT_BLUETOOTH_NAME.toString()))
            .andExpect(jsonPath("$.wifiEnabled").value(DEFAULT_WIFI_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.wifiMacAddr").value(DEFAULT_WIFI_MAC_ADDR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDiagnostico() throws Exception {
        // Get the diagnostico
        restDiagnosticoMockMvc.perform(get("/api/diagnosticos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiagnostico() throws Exception {
        // Initialize the database
        diagnosticoRepository.saveAndFlush(diagnostico);
        int databaseSizeBeforeUpdate = diagnosticoRepository.findAll().size();

        // Update the diagnostico
        Diagnostico updatedDiagnostico = new Diagnostico();
        updatedDiagnostico.setId(diagnostico.getId());
        updatedDiagnostico.setImei(UPDATED_IMEI);
        updatedDiagnostico.setSerial(UPDATED_SERIAL);
        updatedDiagnostico.setTempBateria(UPDATED_TEMP_BATERIA);
        updatedDiagnostico.setTecBateria(UPDATED_TEC_BATERIA);
        updatedDiagnostico.setVoltBateria(UPDATED_VOLT_BATERIA);
        updatedDiagnostico.setCapBateria(UPDATED_CAP_BATERIA);
        updatedDiagnostico.setFuenteEnergia(UPDATED_FUENTE_ENERGIA);
        updatedDiagnostico.setVersionSO(UPDATED_VERSION_SO);
        updatedDiagnostico.setEstadoBateria(UPDATED_ESTADO_BATERIA);
        updatedDiagnostico.setAlmInternoTotal(UPDATED_ALM_INTERNO_TOTAL);
        updatedDiagnostico.setAlmInternoDisp(UPDATED_ALM_INTERNO_DISP);
        updatedDiagnostico.setAlmExternoTotal(UPDATED_ALM_EXTERNO_TOTAL);
        updatedDiagnostico.setAlmExternoDisp(UPDATED_ALM_EXTERNO_DISP);
        updatedDiagnostico.setEstadoCarga(UPDATED_ESTADO_CARGA);
        updatedDiagnostico.setPorcentajeCarga(UPDATED_PORCENTAJE_CARGA);
        updatedDiagnostico.setBluetoothEnabled(UPDATED_BLUETOOTH_ENABLED);
        updatedDiagnostico.setBluetoothMacAddr(UPDATED_BLUETOOTH_MAC_ADDR);
        updatedDiagnostico.setBluetoothName(UPDATED_BLUETOOTH_NAME);
        updatedDiagnostico.setWifiEnabled(UPDATED_WIFI_ENABLED);
        updatedDiagnostico.setWifiMacAddr(UPDATED_WIFI_MAC_ADDR);
        DiagnosticoDTO diagnosticoDTO = diagnosticoMapper.diagnosticoToDiagnosticoDTO(updatedDiagnostico);

        restDiagnosticoMockMvc.perform(put("/api/diagnosticos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(diagnosticoDTO)))
                .andExpect(status().isOk());

        // Validate the Diagnostico in the database
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        assertThat(diagnosticos).hasSize(databaseSizeBeforeUpdate);
        Diagnostico testDiagnostico = diagnosticos.get(diagnosticos.size() - 1);
        assertThat(testDiagnostico.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testDiagnostico.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testDiagnostico.getTempBateria()).isEqualTo(UPDATED_TEMP_BATERIA);
        assertThat(testDiagnostico.getTecBateria()).isEqualTo(UPDATED_TEC_BATERIA);
        assertThat(testDiagnostico.getVoltBateria()).isEqualTo(UPDATED_VOLT_BATERIA);
        assertThat(testDiagnostico.getCapBateria()).isEqualTo(UPDATED_CAP_BATERIA);
        assertThat(testDiagnostico.getFuenteEnergia()).isEqualTo(UPDATED_FUENTE_ENERGIA);
        assertThat(testDiagnostico.getVersionSO()).isEqualTo(UPDATED_VERSION_SO);
        assertThat(testDiagnostico.getEstadoBateria()).isEqualTo(UPDATED_ESTADO_BATERIA);
        assertThat(testDiagnostico.getAlmInternoTotal()).isEqualTo(UPDATED_ALM_INTERNO_TOTAL);
        assertThat(testDiagnostico.getAlmInternoDisp()).isEqualTo(UPDATED_ALM_INTERNO_DISP);
        assertThat(testDiagnostico.getAlmExternoTotal()).isEqualTo(UPDATED_ALM_EXTERNO_TOTAL);
        assertThat(testDiagnostico.getAlmExternoDisp()).isEqualTo(UPDATED_ALM_EXTERNO_DISP);
        assertThat(testDiagnostico.getEstadoCarga()).isEqualTo(UPDATED_ESTADO_CARGA);
        assertThat(testDiagnostico.getPorcentajeCarga()).isEqualTo(UPDATED_PORCENTAJE_CARGA);
        assertThat(testDiagnostico.isBluetoothEnabled()).isEqualTo(UPDATED_BLUETOOTH_ENABLED);
        assertThat(testDiagnostico.getBluetoothMacAddr()).isEqualTo(UPDATED_BLUETOOTH_MAC_ADDR);
        assertThat(testDiagnostico.getBluetoothName()).isEqualTo(UPDATED_BLUETOOTH_NAME);
        assertThat(testDiagnostico.isWifiEnabled()).isEqualTo(UPDATED_WIFI_ENABLED);
        assertThat(testDiagnostico.getWifiMacAddr()).isEqualTo(UPDATED_WIFI_MAC_ADDR);
    }

    @Test
    @Transactional
    public void deleteDiagnostico() throws Exception {
        // Initialize the database
        diagnosticoRepository.saveAndFlush(diagnostico);
        int databaseSizeBeforeDelete = diagnosticoRepository.findAll().size();

        // Get the diagnostico
        restDiagnosticoMockMvc.perform(delete("/api/diagnosticos/{id}", diagnostico.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        assertThat(diagnosticos).hasSize(databaseSizeBeforeDelete - 1);
    }
}

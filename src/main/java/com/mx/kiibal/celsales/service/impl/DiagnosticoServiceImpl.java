package com.mx.kiibal.celsales.service.impl;

import com.mx.kiibal.celsales.domain.AppNoOfc;
import com.mx.kiibal.celsales.domain.Carrier;
import com.mx.kiibal.celsales.service.DiagnosticoService;
import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.domain.DiagnosticoCarrier;
import com.mx.kiibal.celsales.domain.Fabricante;
import com.mx.kiibal.celsales.domain.Telefono;
import com.mx.kiibal.celsales.domain.TelefonoDiagnostico;
import com.mx.kiibal.celsales.domain.User;
import com.mx.kiibal.celsales.domain.UsuarioDiagnostico;
import com.mx.kiibal.celsales.repository.AppNoOfcRepository;
import com.mx.kiibal.celsales.repository.CarrierRepository;
import com.mx.kiibal.celsales.repository.DiagnosticoCarrierRepository;
import com.mx.kiibal.celsales.repository.DiagnosticoRepository;
import com.mx.kiibal.celsales.repository.FabricanteRepository;
import com.mx.kiibal.celsales.repository.TelefonoDiagnosticoRepository;
import com.mx.kiibal.celsales.repository.TelefonoRepository;
import com.mx.kiibal.celsales.repository.UserRepository;
import com.mx.kiibal.celsales.repository.UsuarioDiagnosticoRepository;
import com.mx.kiibal.celsales.service.FabricanteService;
import com.mx.kiibal.celsales.service.TelefonoService;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoAppDTO;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;
import com.mx.kiibal.celsales.web.rest.mapper.DiagnosticoMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Diagnostico.
 */
@Service
@Transactional
public class DiagnosticoServiceImpl implements DiagnosticoService{

    private final Logger log = LoggerFactory.getLogger(DiagnosticoServiceImpl.class);
    
    @Inject
    private DiagnosticoRepository diagnosticoRepository;
    
    @Inject
    private DiagnosticoMapper diagnosticoMapper;
    
    @Inject
    private AppNoOfcRepository appNoOfcRepository;
    
    @Inject
    private CarrierRepository carrierRepository;
    
    @Inject
    private DiagnosticoCarrierRepository diagnosticoCarrierRepository;
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private UsuarioDiagnosticoRepository usuarioDiagnosticoRepository;
    
    @Inject
    private FabricanteService fabricanteService;
    
    @Inject
    private FabricanteRepository fabricanteRepository;
    
    @Inject
    private TelefonoService telefonoService;
    
    @Inject
    private TelefonoRepository telefonoRepository;
    
    @Inject
    private TelefonoDiagnosticoRepository telefonoDiagnosticoRepository;
    
    /**
     * Save a diagnostico.
     * 
     * @param diagnosticoDTO the entity to save
     * @return the persisted entity
     */
    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
        log.debug("Request to save Diagnostico : {}", diagnosticoDTO);
        Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
        diagnostico = diagnosticoRepository.save(diagnostico);
        DiagnosticoDTO result = diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
        return result;
    }

    /**
     *  Get all the diagnosticos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Diagnostico> findAll(Pageable pageable) {
        log.debug("Request to get all Diagnosticos");
        Page<Diagnostico> result = diagnosticoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one diagnostico by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DiagnosticoDTO findOne(Long id) {
        log.debug("Request to get Diagnostico : {}", id);
        Diagnostico diagnostico = diagnosticoRepository.findOne(id);
        DiagnosticoDTO diagnosticoDTO = diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
        return diagnosticoDTO;
    }

    /**
     *  Delete the  diagnostico by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Diagnostico : {}", id);
        diagnosticoRepository.delete(id);
    }

    @Override
    public DiagnosticoAppDTO saveDiagnosticoApp(DiagnosticoAppDTO appDTO) {
        Diagnostico d = diagnosticoMapper.diagnosticoAppDTOToDiagnostico(appDTO);
        
        d = diagnosticoRepository.save(d);
        
        saveAppNoOfc(d, appDTO);
        
        saveCarrierDiagnostico(d, appDTO);
        
        saveUsuarioDiagnostico(d, appDTO);
        
        saveTelefonoDiagnostico(d, appDTO);
        
        appDTO.setId(d.getId());
        
        return appDTO;
    }
    
    /**
     * Método para guardar las apps no oficiales
     * @param diagnostico
     * @param appDTO 
     */
    private void saveAppNoOfc(Diagnostico diagnostico,DiagnosticoAppDTO appDTO){
        List<AppNoOfc> appNoOfc = new ArrayList<>();
        
        appDTO
                .getAppsList()
                .stream()
                .forEach(a -> {
                    AppNoOfc app = new AppNoOfc();
                    app.setDiagnostico(diagnostico);
                    app.setEmpaquetado(a.getAppPackage());
                    app.setFechaInstalacion(a.getAppInstalled());
                    app.setFechaModificacion(a.getAppLastModify());
                    app.setIcono(null);
                    app.setIconoContentType(null);
                    app.setNombre(a.getAppName());
                    app.setVersion(a.getAppVersion());
                    
                    appNoOfc.add(app);
                });
        
        appNoOfcRepository.save(appNoOfc);
    }
    
    /**
     * Método para guardar la tabla de relación carrierDiagnostico
     * @param diagnostico
     * @param appDTO 
     */
    private void saveCarrierDiagnostico(Diagnostico diagnostico,DiagnosticoAppDTO appDTO){
        DiagnosticoCarrier dc = new DiagnosticoCarrier();
        Carrier c = carrierRepository.findByNombreLike(appDTO.getCarrier());
        
        if (Objects.isNull(c)) {
            c = new Carrier();
            c.setNombre(appDTO.getCarrier());
            c = carrierRepository.save(c);
        }
        
        dc.setCarrier(c);
        dc.setDiagnostico(diagnostico);
        dc.setEsOrigen(Boolean.TRUE);
        
        diagnosticoCarrierRepository.save(dc);
    }
    
    private void saveUsuarioDiagnostico(Diagnostico diagnostico,DiagnosticoAppDTO appDTO){
        Optional<User> findOneByEmail = 
                userRepository.findOneByEmail(appDTO.getEmail());
        UsuarioDiagnostico usuarioDiagnostico = new UsuarioDiagnostico();
        
        if (!findOneByEmail.isPresent()) {
            //se debería crear usuario?
            log.debug("USUARIO NO DADO DE ALTA");
        }
        
        usuarioDiagnostico.setDiagnostico(diagnostico);
        usuarioDiagnostico.setEsCreador(Boolean.TRUE);
        usuarioDiagnostico.setUser(findOneByEmail.get());
        
        usuarioDiagnosticoRepository.save(usuarioDiagnostico);
        
    }
    
    private void saveTelefonoDiagnostico(Diagnostico diagnostico,DiagnosticoAppDTO appDTO){
        Optional<Fabricante> findByNombre 
                = fabricanteService.findByNombre(appDTO.getManufacturer());
        FabricanteDTO f = new FabricanteDTO();
        Fabricante fabricante;
        Telefono telefono;
        TelefonoDiagnostico telefonoDiagnostico = new TelefonoDiagnostico();
        
        if (!findByNombre.isPresent()) {
            f.setNombre(appDTO.getManufacturer());
            f = fabricanteService.save(f);
            fabricante = fabricanteRepository.findOne(f.getId());
        }else{
            fabricante = findByNombre.get();
        }
        
        Optional<Telefono> findByModelo = 
                telefonoService.findByModelo(appDTO.getModel());
        
        if (!findByModelo.isPresent()) {
            TelefonoDTO telefonoDTO = new TelefonoDTO();
            telefonoDTO.setFabricanteId(fabricante.getId());
            telefonoDTO.setMarca(fabricante.getNombre());
            telefonoDTO.setModelo(appDTO.getModel());
            telefonoDTO.setNombre(appDTO.getBrand());
            telefonoDTO = telefonoService.save(telefonoDTO);
            telefono = telefonoRepository.findOne(telefonoDTO.getId());
        }else{
            telefono = findByModelo.get();
        }
        
        telefonoDiagnostico.setDiagnostico(diagnostico);
        telefonoDiagnostico.setFechaCreacion(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        telefonoDiagnostico.setTelefono(telefono);
        
        telefonoDiagnosticoRepository.save(telefonoDiagnostico);
        
    }
}

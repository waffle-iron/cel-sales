package com.mx.kiibal.celsales.web.rest.mapper;

import com.mx.kiibal.celsales.domain.*;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoAppDTO;
import com.mx.kiibal.celsales.web.rest.dto.DiagnosticoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Diagnostico and its DTO DiagnosticoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiagnosticoMapper {

    DiagnosticoDTO diagnosticoToDiagnosticoDTO(Diagnostico diagnostico);

    List<DiagnosticoDTO> diagnosticosToDiagnosticoDTOs(List<Diagnostico> diagnosticos);

    Diagnostico diagnosticoDTOToDiagnostico(DiagnosticoDTO diagnosticoDTO);

    List<Diagnostico> diagnosticoDTOsToDiagnosticos(List<DiagnosticoDTO> diagnosticoDTOs);
    
    @Mapping(source = "imei",target = "imei")
    @Mapping(source = "serial",target = "serial")
    @Mapping(source = "battery.temperature",target = "tempBateria")
    @Mapping(source = "battery.technology",target = "tecBateria")
    @Mapping(source = "battery.voltage",target = "voltBateria")
    @Mapping(source = "battery.capacity",target = "capBateria")
    @Mapping(source = "battery.plugged.status",target = "fuenteEnergia")
    @Mapping(source = "version",target = "versionSO")
    @Mapping(source = "battery.health.status",target = "estadoBateria")
    @Mapping(source = "storage.internal.total",target = "almInternoTotal")
    @Mapping(source = "storage.internal.available",target = "almInternoDisp")
    @Mapping(source = "storage.external.total",target = "almExternoTotal")
    @Mapping(source = "storage.external.available",target = "almExternoDisp")
    @Mapping(source = "battery.status.status",target = "estadoCarga")
    @Mapping(source = "battery.batteryPct",target = "porcentajeCarga")
    @Mapping(source = "bluetooth.name",target = "bluetoothName")
    @Mapping(source = "bluetooth.macAddr",target = "bluetoothMacAddr")
    @Mapping(source = "bluetooth.isEnabled",target = "bluetoothEnabled")
    @Mapping(source = "wifi.macAddr",target = "wifiMacAddr")
    @Mapping(source = "wifi.isEnabled",target = "wifiEnabled")
    Diagnostico diagnosticoAppDTOToDiagnostico(DiagnosticoAppDTO diagnosticoAppDTO);
}

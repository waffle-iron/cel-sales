package com.mx.kiibal.celsales.web.rest.mapper;

import com.mx.kiibal.celsales.domain.*;
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
}

package com.mx.kiibal.celsales.web.rest.mapper;

import com.mx.kiibal.celsales.domain.*;
import com.mx.kiibal.celsales.web.rest.dto.FabricanteDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Fabricante and its DTO FabricanteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FabricanteMapper {

    FabricanteDTO fabricanteToFabricanteDTO(Fabricante fabricante);

    List<FabricanteDTO> fabricantesToFabricanteDTOs(List<Fabricante> fabricantes);

    Fabricante fabricanteDTOToFabricante(FabricanteDTO fabricanteDTO);

    List<Fabricante> fabricanteDTOsToFabricantes(List<FabricanteDTO> fabricanteDTOs);
}

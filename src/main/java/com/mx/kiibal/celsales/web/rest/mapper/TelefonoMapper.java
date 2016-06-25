package com.mx.kiibal.celsales.web.rest.mapper;

import com.mx.kiibal.celsales.domain.*;
import com.mx.kiibal.celsales.web.rest.dto.TelefonoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Telefono and its DTO TelefonoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TelefonoMapper {

    @Mapping(source = "fabricante.id", target = "fabricanteId")
    TelefonoDTO telefonoToTelefonoDTO(Telefono telefono);

    List<TelefonoDTO> telefonosToTelefonoDTOs(List<Telefono> telefonos);

    @Mapping(source = "fabricanteId", target = "fabricante")
    Telefono telefonoDTOToTelefono(TelefonoDTO telefonoDTO);

    List<Telefono> telefonoDTOsToTelefonos(List<TelefonoDTO> telefonoDTOs);

    default Fabricante fabricanteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Fabricante fabricante = new Fabricante();
        fabricante.setId(id);
        return fabricante;
    }
}

package com.mx.kiibal.celsales.web.rest.mapper;

import com.mx.kiibal.celsales.domain.*;
import com.mx.kiibal.celsales.web.rest.dto.CarrierDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Carrier and its DTO CarrierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarrierMapper {

    CarrierDTO carrierToCarrierDTO(Carrier carrier);

    List<CarrierDTO> carriersToCarrierDTOs(List<Carrier> carriers);

    Carrier carrierDTOToCarrier(CarrierDTO carrierDTO);

    List<Carrier> carrierDTOsToCarriers(List<CarrierDTO> carrierDTOs);
}

package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.DiagnosticoCarrier;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DiagnosticoCarrier entity.
 */
@SuppressWarnings("unused")
public interface DiagnosticoCarrierRepository extends JpaRepository<DiagnosticoCarrier,Long> {

}

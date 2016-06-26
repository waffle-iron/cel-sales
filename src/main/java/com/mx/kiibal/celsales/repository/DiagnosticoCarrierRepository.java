package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.domain.DiagnosticoCarrier;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the DiagnosticoCarrier entity.
 */
@SuppressWarnings("unused")
public interface DiagnosticoCarrierRepository extends JpaRepository<DiagnosticoCarrier,Long> {

    Optional<DiagnosticoCarrier> findByDiagnostico(Diagnostico diagnostico);
}

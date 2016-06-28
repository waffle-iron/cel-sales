package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.domain.MensajeLog;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MensajeLog entity.
 */
@SuppressWarnings("unused")
public interface MensajeLogRepository extends JpaRepository<MensajeLog,Long> {

    List<MensajeLog> findByDiagnostico(Diagnostico diagnostico);
}

package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.AppNoOfc;
import com.mx.kiibal.celsales.domain.Diagnostico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AppNoOfc entity.
 */
@SuppressWarnings("unused")
public interface AppNoOfcRepository extends JpaRepository<AppNoOfc,Long> {
    
    List<AppNoOfc> findByDiagnostico(Diagnostico diagnostico);

}

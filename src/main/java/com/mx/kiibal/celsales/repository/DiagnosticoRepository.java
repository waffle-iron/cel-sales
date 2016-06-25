package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Diagnostico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Diagnostico entity.
 */
@SuppressWarnings("unused")
public interface DiagnosticoRepository extends JpaRepository<Diagnostico,Long> {

}

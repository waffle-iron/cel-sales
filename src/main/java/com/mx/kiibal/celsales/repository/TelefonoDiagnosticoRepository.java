package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.TelefonoDiagnostico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TelefonoDiagnostico entity.
 */
@SuppressWarnings("unused")
public interface TelefonoDiagnosticoRepository extends JpaRepository<TelefonoDiagnostico,Long> {

}

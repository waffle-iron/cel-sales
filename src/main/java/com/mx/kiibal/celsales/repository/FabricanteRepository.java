package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Fabricante;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fabricante entity.
 */
@SuppressWarnings("unused")
public interface FabricanteRepository extends JpaRepository<Fabricante,Long> {

}

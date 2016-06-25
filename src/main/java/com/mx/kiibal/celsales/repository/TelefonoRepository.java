package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Telefono;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Telefono entity.
 */
@SuppressWarnings("unused")
public interface TelefonoRepository extends JpaRepository<Telefono,Long> {

}

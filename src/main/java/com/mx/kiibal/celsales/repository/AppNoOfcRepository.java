package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.AppNoOfc;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AppNoOfc entity.
 */
@SuppressWarnings("unused")
public interface AppNoOfcRepository extends JpaRepository<AppNoOfc,Long> {

}

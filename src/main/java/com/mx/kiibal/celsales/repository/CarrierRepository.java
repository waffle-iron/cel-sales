package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Carrier;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Carrier entity.
 */
@SuppressWarnings("unused")
public interface CarrierRepository extends JpaRepository<Carrier,Long> {

}

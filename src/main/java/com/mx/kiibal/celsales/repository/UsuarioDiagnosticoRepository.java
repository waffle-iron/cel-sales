package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.UsuarioDiagnostico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UsuarioDiagnostico entity.
 */
@SuppressWarnings("unused")
public interface UsuarioDiagnosticoRepository extends JpaRepository<UsuarioDiagnostico,Long> {

    @Query("select usuarioDiagnostico from UsuarioDiagnostico usuarioDiagnostico where usuarioDiagnostico.user.login = ?#{principal.username}")
    List<UsuarioDiagnostico> findByUserIsCurrentUser();

}

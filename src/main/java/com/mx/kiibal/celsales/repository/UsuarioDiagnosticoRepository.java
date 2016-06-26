package com.mx.kiibal.celsales.repository;

import com.mx.kiibal.celsales.domain.Diagnostico;
import com.mx.kiibal.celsales.domain.UsuarioDiagnostico;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the UsuarioDiagnostico entity.
 */
@SuppressWarnings("unused")
public interface UsuarioDiagnosticoRepository extends JpaRepository<UsuarioDiagnostico,Long> {

    @Query("select usuarioDiagnostico from UsuarioDiagnostico usuarioDiagnostico where usuarioDiagnostico.user.login = ?#{principal.username}")
    List<UsuarioDiagnostico> findByUserIsCurrentUser();
    
    Optional<UsuarioDiagnostico> findByDiagnostico(Diagnostico diagnostico);

}

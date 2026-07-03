package com.clouddev.bibliotecaspringboot.features.utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long> {
    boolean existsByEmail(String email);
}
package com.clouddev.bibliotecaspringboot.features.prestito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestitoRepository extends JpaRepository<PrestitoEntity, Long> {
}
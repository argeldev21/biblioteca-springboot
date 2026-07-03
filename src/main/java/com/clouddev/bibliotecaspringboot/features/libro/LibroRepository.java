package com.clouddev.bibliotecaspringboot.features.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    List<LibroEntity> findByTitoloContainingIgnoreCase(String titolo);

    List<LibroEntity> findByAutoreContainingIgnoreCase(String autore);
}
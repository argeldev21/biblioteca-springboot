package com.clouddev.bibliotecaspringboot.features.libro;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;

    public LibroService(LibroRepository libroRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    @Transactional(readOnly = true)
    public List<LibroDto> getAllLibri() {
        return libroRepository.findAll().stream()
                .map(libroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LibroDto getLibroById(Long id) {
        LibroEntity libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + id));
        return libroMapper.toDto(libro);
    }

    @Transactional(readOnly = true)
    public List<LibroDto> cercaPerTitolo(String titolo) {
        return libroRepository.findByTitoloContainingIgnoreCase(titolo).stream()
                .map(libroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LibroDto> cercaPerAutore(String autore) {
        return libroRepository.findByAutoreContainingIgnoreCase(autore).stream()
                .map(libroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public LibroDto createLibro(LibroDto dto) {
        LibroEntity libro = libroMapper.toEntity(dto);
        LibroEntity libroSalvato = libroRepository.save(libro);
        return libroMapper.toDto(libroSalvato);
    }

    @Transactional
    public void deleteLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: libro non trovato");
        }
        libroRepository.deleteById(id);
    }
}
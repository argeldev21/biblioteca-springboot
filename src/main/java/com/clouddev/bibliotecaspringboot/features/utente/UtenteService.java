package com.clouddev.bibliotecaspringboot.features.utente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    public UtenteService(UtenteRepository utenteRepository, UtenteMapper utenteMapper) {
        this.utenteRepository = utenteRepository;
        this.utenteMapper = utenteMapper;
    }

    @Transactional(readOnly = true)
    public List<UtenteDto> getAllUtenti() {
        return utenteRepository.findAll().stream()
                .map(utenteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UtenteDto getUtenteById(Long id) {
        UtenteEntity utente = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
        return utenteMapper.toDto(utente);
    }

    @Transactional
    public UtenteDto createUtente(UtenteDto dto) {
        if (utenteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("L'email è già registrata nel sistema");
        }

        UtenteEntity utente = utenteMapper.toEntity(dto);
        UtenteEntity utenteSalvato = utenteRepository.save(utente);

        return utenteMapper.toDto(utenteSalvato);
    }

    @Transactional
    public void deleteUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: utente non trovato");
        }
        utenteRepository.deleteById(id);
    }
}
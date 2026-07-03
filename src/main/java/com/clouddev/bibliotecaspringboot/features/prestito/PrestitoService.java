package com.clouddev.bibliotecaspringboot.features.prestito;

import com.clouddev.bibliotecaspringboot.features.libro.LibroEntity;
import com.clouddev.bibliotecaspringboot.features.libro.LibroRepository;
import com.clouddev.bibliotecaspringboot.features.utente.UtenteEntity;
import com.clouddev.bibliotecaspringboot.features.utente.UtenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestitoService {

    private final PrestitoRepository prestitoRepository;
    private final UtenteRepository utenteRepository;
    private final LibroRepository libroRepository;
    private final PrestitoMapper prestitoMapper;

    public PrestitoService(PrestitoRepository prestitoRepository, UtenteRepository utenteRepository,
                           LibroRepository libroRepository, PrestitoMapper prestitoMapper) {
        this.prestitoRepository = prestitoRepository;
        this.utenteRepository = utenteRepository;
        this.libroRepository = libroRepository;
        this.prestitoMapper = prestitoMapper;
    }

    @Transactional(readOnly = true)
    public List<PrestitoDto> getAllPrestiti() {
        return prestitoRepository.findAll().stream()
                .map(prestitoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrestitoDto avviaPrestito(PrestitoDto dto) {
        UtenteEntity utente = utenteRepository.findById(dto.getUtenteId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + dto.getUtenteId()));

        LibroEntity libro = libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + dto.getLibroId()));

        // Controllo se il libro ha copie disponibili
        if (!libro.isDisponibilita() || libro.getCopie() <= 0) {
            throw new IllegalArgumentException("Il libro richiesto non è al momento disponibile per il prestito");
        }

        // Decrementa le copie del libro
        libro.setCopie(libro.getCopie() - 1);
        if (libro.getCopie() == 0) {
            libro.setDisponibilita(false);
        }
        libroRepository.save(libro);

        // Crea il prestito (durata standard 30 giorni)
        PrestitoEntity prestito = PrestitoEntity.builder()
                .utente(utente)
                .libro(libro)
                .dataInizio(LocalDate.now())
                .dataScadenza(LocalDate.now().plusDays(30))
                .stato(StatoPrestito.ATTIVO)
                .build();

        PrestitoEntity prestitoSalvato = prestitoRepository.save(prestito);
        return prestitoMapper.toDto(prestitoSalvato);
    }

    @Transactional
    public PrestitoDto restituisciLibro(Long prestitoId) {
        PrestitoEntity prestito = prestitoRepository.findById(prestitoId)
                .orElseThrow(() -> new RuntimeException("Prestito non trovato con id: " + prestitoId));

        if (prestito.getStato() == StatoPrestito.RESTITUITO) {
            throw new IllegalArgumentException("Questo prestito è già stato chiuso");
        }

        // Aggiorna lo stato del prestito
        prestito.setStato(StatoPrestito.RESTITUITO);
        prestito.setDataRestituzioneEffettiva(LocalDate.now());

        // Incrementa le copie del libro restituito
        LibroEntity libro = prestito.getLibro();
        libro.setCopie(libro.getCopie() + 1);
        libro.setDisponibilita(true); // Se torna una copia, il libro è sicuramente disponibile
        libroRepository.save(libro);

        PrestitoEntity prestitoAggiornato = prestitoRepository.save(prestito);
        return prestitoMapper.toDto(prestitoAggiornato);
    }
}
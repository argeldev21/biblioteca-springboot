package com.clouddev.bibliotecaspringboot.features.utente;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "http://localhost:5173")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public ResponseEntity<List<UtenteDto>> getAll() {
        return ResponseEntity.ok(utenteService.getAllUtenti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @PostMapping
    public ResponseEntity<UtenteDto> create(@Valid @RequestBody UtenteDto utenteDto) {
        UtenteDto nuovoUtente = utenteService.createUtente(utenteDto);
        return new ResponseEntity<>(nuovoUtente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utenteService.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}
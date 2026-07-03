package com.clouddev.bibliotecaspringboot.features.prestito;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestiti")
@CrossOrigin(origins = "http://localhost:5173")
public class PrestitoController {

    private final PrestitoService prestitoService;

    public PrestitoController(PrestitoService prestitoService) {
        this.prestitoService = prestitoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestitoDto>> getAll() {
        return ResponseEntity.ok(prestitoService.getAllPrestiti());
    }

    @PostMapping
    public ResponseEntity<PrestitoDto> creaPrestito(@Valid @RequestBody PrestitoDto prestitoDto) {
        PrestitoDto nuovoPrestito = prestitoService.avviaPrestito(prestitoDto);
        return new ResponseEntity<>(nuovoPrestito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/restituzione")
    public ResponseEntity<PrestitoDto> restituzioneLibro(@PathVariable Long id) {
        PrestitoDto prestitoChiuso = prestitoService.restituisciLibro(id);
        return ResponseEntity.ok(prestitoChiuso);
    }
}
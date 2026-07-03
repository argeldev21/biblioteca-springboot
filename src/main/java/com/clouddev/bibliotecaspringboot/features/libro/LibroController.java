package com.clouddev.bibliotecaspringboot.features.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/libri")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/categorie")
    public ResponseEntity<CategoriaLibro[]> getAllCategorie() {
        return ResponseEntity.ok(CategoriaLibro.values());
    }

    @GetMapping
    public ResponseEntity<List<LibroDto>> getAll() {
        return ResponseEntity.ok(libroService.getAllLibri());
    }

    @PostMapping
    public ResponseEntity<LibroDto> create(@Valid @RequestBody LibroDto libro) {
        return ResponseEntity.ok(libroService.createLibro(libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cerca/titolo")
    public ResponseEntity<List<LibroDto>> cercaPerTitolo(@RequestParam String query) {
        return ResponseEntity.ok(libroService.cercaPerTitolo(query));
    }

    @GetMapping("/cerca/autore")
    public ResponseEntity<List<LibroDto>> cercaPerAutore(@RequestParam String query) {
        return ResponseEntity.ok(libroService.cercaPerAutore(query));
    }
}
package com.clouddev.bibliotecaspringboot.features.libro;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroDto {

    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(max = 150, message = "Il titolo non può superare i 150 caratteri")
    private String titolo;

    @NotBlank(message = "L'autore è obbligatorio")
    @Size(max = 100, message = "L'autore non può superare i 100 caratteri")
    private String autore;

    @Min(value = 1000, message = "L'anno di pubblicazione non è valido")
    @Max(value = 2026, message = "L'anno di pubblicazione non può essere nel futuro")
    private int annoPubblicazione;

    @Min(value = 0, message = "Le copie non possono essere negative")
    private int copie;

    private boolean disponibilita;

    @NotNull(message = "La categoria è obbligatoria")
    private CategoriaLibro categoria;
}
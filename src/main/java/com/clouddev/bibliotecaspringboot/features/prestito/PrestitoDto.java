package com.clouddev.bibliotecaspringboot.features.prestito;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestitoDto {

    private Long id;

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long utenteId;

    @NotNull(message = "L'ID libro è obbligatorio")
    private Long libroId;

    private LocalDate dataInizio;
    private LocalDate dataScadenza;
    private LocalDate dataRestituzioneEffettiva;
    private StatoPrestito stato;
}
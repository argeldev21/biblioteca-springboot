package com.clouddev.bibliotecaspringboot.features.utente;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteDto {

    private Long id; // Sarà null quando ricevi la richiesta di creazione, ma valorizzato nella risposta

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    private String email;
}
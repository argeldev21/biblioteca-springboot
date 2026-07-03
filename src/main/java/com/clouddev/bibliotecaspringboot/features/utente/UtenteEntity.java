package com.clouddev.bibliotecaspringboot.features.utente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "utenti_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UtenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Column(nullable = false, length = 50)
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Column(nullable = false, length = 100, unique = true)
    private String email;
}


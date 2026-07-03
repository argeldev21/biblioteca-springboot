package com.clouddev.bibliotecaspringboot.features.prestito;

import com.clouddev.bibliotecaspringboot.features.libro.LibroEntity;
import com.clouddev.bibliotecaspringboot.features.utente.UtenteEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"utente", "libro"}) // Evita cicli infiniti nel toString
public class PrestitoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private UtenteEntity utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private LibroEntity libro;

    @Column(nullable = false)
    private LocalDate dataInizio;

    @Column(nullable = false)
    private LocalDate dataScadenza;

    private LocalDate dataRestituzioneEffettiva; // Sarà null finché il libro non viene restituito

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatoPrestito stato;
}
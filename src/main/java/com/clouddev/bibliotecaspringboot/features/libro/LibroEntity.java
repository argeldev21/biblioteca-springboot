package com.clouddev.bibliotecaspringboot.features.libro;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libri_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titolo;

    @Column(nullable = false, length = 100)
    private String autore;

    @Column(nullable = false)
    private int annoPubblicazione;

    @Column(nullable = false)
    private int copie;

    @Column(nullable = false)
    private boolean disponibilita;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategoriaLibro categoria;
}
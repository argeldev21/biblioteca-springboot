package com.clouddev.bibliotecaspringboot.features.libro;

import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    public LibroEntity toEntity(LibroDto dto) {
        if (dto == null) return null;

        return LibroEntity.builder()
                .titolo(dto.getTitolo())
                .autore(dto.getAutore())
                .annoPubblicazione(dto.getAnnoPubblicazione())
                .copie(dto.getCopie())
                .disponibilita(dto.getCopie() > 0)
                .categoria(dto.getCategoria()) // <--- Mappatura Enum
                .build();
    }

    public LibroDto toDto(LibroEntity entity) {
        if (entity == null) return null;

        LibroDto dto = new LibroDto();
        dto.setId(entity.getId());
        dto.setTitolo(entity.getTitolo());
        dto.setAutore(entity.getAutore());
        dto.setAnnoPubblicazione(entity.getAnnoPubblicazione());
        dto.setCopie(entity.getCopie());
        dto.setDisponibilita(entity.isDisponibilita());
        dto.setCategoria(entity.getCategoria()); // <--- Mappatura Enum
        return dto;
    }
}